package ru.samitin.libary.model

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.samitin.libary.model.userRepositories.IDataSourceRepos

object ApiHolder {
    private val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)    //Установить политику именования полей(Политика именования полей.Нижний регистр с подчеркиванием)
        .excludeFieldsWithoutExposeAnnotation()                                 //Исключить поля без экстракции аннотации
        .create()
    private val retrofit=  Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())             //Добавить адаптер вызова фабрики(RxJava3 Вызов адаптера фабрики.Создайте())
        .addConverterFactory(GsonConverterFactory.create(gson))                //Добавить конвертер фабрики(Gson конвертер фабрики.create(gson))
        .build()                                                               //строить

    val api: IDataSource by lazy {
     retrofit.create(IDataSource::class.java)
    }
    val apiRepos: IDataSourceRepos by lazy {
        retrofit.create(IDataSourceRepos::class.java)
    }
}