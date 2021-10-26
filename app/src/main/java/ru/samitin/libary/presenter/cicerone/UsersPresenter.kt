package ru.samitin.libary.presenter.cicerone

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.samitin.libary.model.GithubUser
import ru.samitin.libary.model.GithubUsersRepo
import ru.samitin.libary.model.UserItemView
import ru.samitin.libary.presenter.IUserListPresenter
import ru.samitin.libary.view.cicirone.UsersView

class UsersPresenter(val usersRepo: GithubUsersRepo,val router: Router): MvpPresenter<UsersView>() {
    class UsersListPresenter: IUserListPresenter{
        val users = mutableListOf<GithubUser>()
        override var itemClickListener: ((UserItemView) -> Unit)?=null
        override fun getCount(): Int =users.size
        override fun bindView(view: UserItemView) = view.setLogin(users[view.pos].login)
    }

    val usersListPresenter=UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = {itemView ->
            //TODO: переход на экран пользователя c помощью router.navigateTo
        }
    }
    fun loadData(){
        val users=usersRepo.getUsers()
        usersListPresenter.users.addAll(users)
        viewState.updateList()
    }
    fun backPressed():Boolean{
        router.exit()
        return true
    }
}