package ru.samitin.libary.presenter

import ru.samitin.libary.R
import ru.samitin.libary.model.CountersModel
import ru.samitin.libary.view.MainView

class MainPresenter(val view: MainView) {
    val model = CountersModel()

    //Архитектурная ошибка. В качестве практического задания -- исправить
    fun counterClick(type: ButtonType){
        when(type){
            ButtonType.SSECOND -> {
                val nextValue = model.next(0)
                view.setButtonText(0, nextValue.toString())
            }
            ButtonType.FIRST -> {
                val nextValue = model.next(1)
                view.setButtonText(1, nextValue.toString())
            }
            ButtonType.THIRD -> {
                val nextValue = model.next(2)
                view.setButtonText(2, nextValue.toString())
            }
        }
    }
    enum class ButtonType{
        SSECOND,
        FIRST ,
        THIRD
    }
}
