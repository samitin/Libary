package ru.samitin.libary.presenter

import moxy.MvpPresenter
import ru.samitin.libary.model.CountersModel
import ru.samitin.libary.view.MainView

class MainPresenter(val model: CountersModel): MvpPresenter<MainView>() {

    fun counterOneClick() {
        val nextValue = model.next(0)
        viewState.setButtonOneText(nextValue.toString())
    }

    fun counterTwoClick() {
        val nextValue = model.next(1)
        viewState.setButtonTwoText(nextValue.toString())
    }

    fun counterThreeClick() {
        val nextValue = model.next(2)
        viewState.setButtonThreeText(nextValue.toString())
    }
}