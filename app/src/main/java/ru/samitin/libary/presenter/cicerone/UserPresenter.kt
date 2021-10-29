package ru.samitin.libary.presenter.cicerone

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.samitin.libary.model.GithubUser
import ru.samitin.libary.view.cicirone.UserView

class UserPresenter(val user:GithubUser,val router:Router): MvpPresenter<UserView>() {

    fun backPressed():Boolean{
        router.exit()
        return true
    }
}