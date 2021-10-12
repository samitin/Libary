package ru.samitin.libary.presenter

import ru.samitin.libary.model.CountersModel
import ru.samitin.libary.view.MainView

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    //Архитектурная ошибка. В качестве практического задания -- исправить
    fun counterClick(type: ButtonType){
        when(type){
            ButtonType.SSECOND -> {
                val nextValue = model.next(0)
                view.setButtonText(ButtonType.SSECOND, nextValue.toString())
            }
            ButtonType.FIRST -> {
                val nextValue = model.next(1)
                view.setButtonText(ButtonType.FIRST, nextValue.toString())
            }
            ButtonType.THIRD -> {
                val nextValue = model.next(2)
                view.setButtonText(ButtonType.THIRD, nextValue.toString())
            }
        }
    }

}
enum class ButtonType{
    SSECOND,
    FIRST ,
    THIRD
}