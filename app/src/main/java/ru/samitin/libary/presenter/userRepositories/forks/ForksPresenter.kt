package ru.samitin.libary.presenter.userRepositories.forks

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.samitin.libary.view.userRepositories.forks.ForksView
import ru.samitin.libary.view.userRepositories.forks.FragmentForks

class ForksPresenter (val router: Router): MvpPresenter<ForksView>() {

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}