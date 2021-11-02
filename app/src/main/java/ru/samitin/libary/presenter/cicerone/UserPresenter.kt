package ru.samitin.libary.presenter.cicerone

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.samitin.libary.model.GithubUser
import ru.samitin.libary.view.cicirone.UserView

class UserPresenter(val router: Router, val user: GithubUser) : MvpPresenter<UserView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        user.login?.let { viewState.setLogin(it) }

    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}