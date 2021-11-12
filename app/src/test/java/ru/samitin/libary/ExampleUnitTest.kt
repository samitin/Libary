package ru.samitin.libary

import io.reactivex.rxjava3.core.Observable


import org.junit.Assert.*
import org.junit.Test
import ru.samitin.libary.model.userRepositories.IDataSourceRepos
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun `проверяем observable just` (){   //just=просто
        Observable.just("Hello","jast","msk").subscribe { println("element=$it") }
    }

    @Test
    fun `проверяем observable iterable` (){//Работает с массивом
        Observable.fromIterable(listOf("Hello","jast","msk",null,"!").map { Test1(it) }).subscribe { println("element=${it.element}") }
    }
    @Test
    fun `проверяем observable interval` (){//задаёт интервал между выводами эллементов. максимальное время Thread.sleep(10000)
        Observable.interval(2,TimeUnit.SECONDS)
            .subscribe({ println("element=$it") },
            { println(it.message)})
        Thread.sleep(10000)
    }
    @Test
    fun `проверяем observable range` (){//renge=диапазон аналогично 1..100
        Observable.range(1,100)
            .subscribe({ println("element=$it") },
                { println(it.message)})
        Thread.sleep(10000)
    }
    @Test
    fun `проверяем observable create` (){//создание Observable
        Observable.create<String> {emitter ->
            var min=1
            val max=10
            while (min<max){
                emitter.onNext("element $min")
                min++
                Thread.sleep(2000)
            }
            emitter.onComplete()
        }
            .subscribe({ println("element=$it") },
                { println(it.message)},
                { println("onComplate()")})

    }
    @Test
    fun `проверяем observable take` (){
        Observable.range(1,100)//renge=диапазон аналогично 1..100
            .take(20)          //Пропустит только первые 20 эллиментов
            .subscribe({ println("element=$it") },
                { println(it.message)})
    }
    @Test
    fun `проверяем observable skip` (){
        Observable.range(1,100)//renge=диапазон аналогично 1..100
            .skip(20)          //Пропустит все эллименты кроме первых 20
            .subscribe({ println("element=$it") },
                { println(it.message)})
    }
    @Test
    fun `проверяем observable combineLatest` (){//обьединяет Observable
        val interval=Observable.interval(1,TimeUnit.SECONDS)
        val just=Observable.just("Hello","jast","msk")
          Observable.combineLatest(interval,just,{t1,t2 ->
              "$t1 $t2"
          }).subscribe ({ println("element=  $it") },
              { println(it.message)})
        Thread.sleep(10000)
    }
    @Test
    fun `проверяем observable zip` (){//обьединяет Observable когда пришлиданные с обоих потоков
        val interval=Observable.interval(1,TimeUnit.SECONDS)
        val just=Observable.just("Hello","jast","msk")
        Observable.zip(interval,just,{t1,t2 ->
            "$t1 $t2"
        }).subscribe ({ println("element=  $it") },
            { println(it.message)})
        Thread.sleep(10000)
    }
    @Test
    fun `проверяем observable merge` (){//обьединяет Observable
        val just1=Observable.just("One","Two","Three")
        val just2=Observable.just("Hello","jast","msk")
        Observable.merge(just1,just2)
            .subscribe ({ println("element=  $it") },
            { println(it.message)})
       // Thread.sleep(10000)
    }
    @Test
    fun repos(){
        val data:IDataSourceRepos=ApiHolder.apiRepos
        data.getRepos("https://api.github.com/users/mojombo/repos").subscribe({ s ->
             s.map { println("onSuccess: $it") }
        }, {
            println("onError: ${it.message}")
        })
        Thread.sleep(5000)
    }
}
class Test1(val element:String?=null)