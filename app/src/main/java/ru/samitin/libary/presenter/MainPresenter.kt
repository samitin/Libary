package ru.samitin.libary.presenter

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.samitin.libary.model.GithubUser
import ru.samitin.libary.model.GithubUsersRepo
import ru.samitin.libary.model.UserItemView
import ru.samitin.libary.view.MainView
import ru.samitin.libary.view.cicirone.IScreens

class MainPresenter(val router: Router, val screens: IScreens) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(screens.users())
    }

    fun backClicked() {
        router.exit()
    }
}