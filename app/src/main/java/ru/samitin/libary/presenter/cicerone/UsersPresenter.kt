package ru.samitin.libary.presenter.cicerone

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.samitin.libary.model.GithubUser
import ru.samitin.libary.model.IGithubUsersRepo
import ru.samitin.libary.model.UserItemView
import ru.samitin.libary.presenter.IUserListPresenter
import ru.samitin.libary.view.cicirone.IScreens
import ru.samitin.libary.view.cicirone.UsersView

class UsersPresenter(val uiScheduler: Scheduler, val usersRepo: IGithubUsersRepo, val router: Router, val screens: IScreens): MvpPresenter<UsersView>() {

    class UsersListPresenter: IUserListPresenter{
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)?=null
        override fun getCount(): Int =users.size
        override fun bindView(view: UserItemView) {
            val user=users[view.pos]
            user.login?.let { view.setLogin(it) }
            user.avatarUrl?.let {view.loadAvatar(it)}
        }
    }

    val usersListPresenter=UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = {itemView ->
            //TODO: переход на экран пользователя c помощью router.navigateTo
            val user=usersListPresenter.users[itemView.pos]
            router.navigateTo(screens.user(user))
        }
    }
    fun loadData(){
        usersRepo.getUsers()
            .observeOn(uiScheduler)
            .subscribe({repos ->
                usersListPresenter.users.clear()
                usersListPresenter.users.addAll(repos)
                viewState.updateList()
            },{ println("Error ${it.message}")})

    }
    fun backPressed():Boolean{
        router.exit()
        return true
    }
}