package ru.samitin.libary.view

import ru.samitin.libary.presenter.ButtonType

interface MainView {
    fun setButtonText(index: ButtonType, text: String)
}