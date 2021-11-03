package ru.samitin.libary.view.userRepositories.forks

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ForksView: MvpView {
    fun setForks(forks:Int)
}