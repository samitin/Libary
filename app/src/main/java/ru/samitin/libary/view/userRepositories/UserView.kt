package ru.samitin.libary.view.userRepositories

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserView : MvpView {
    fun setLogin(login:String)
    fun init()
    fun updateList()
}
