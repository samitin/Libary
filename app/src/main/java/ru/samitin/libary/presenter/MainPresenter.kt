package ru.samitin.libary.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.samitin.libary.view.MainView
import ru.samitin.libary.view.cicirone.IScreens
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {

    @Inject lateinit var router: Router
    @Inject
    lateinit var screens: IScreens

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}