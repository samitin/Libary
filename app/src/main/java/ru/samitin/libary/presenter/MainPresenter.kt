package ru.samitin.libary.presenter

import moxy.MvpPresenter
import ru.samitin.libary.model.CountersModel
import ru.samitin.libary.model.GithubUser
import ru.samitin.libary.model.GithubUsersRepo
import ru.samitin.libary.model.UserItemView
import ru.samitin.libary.view.MainView

class MainPresenter(val userRepo: GithubUsersRepo): MvpPresenter<MainView>() {

class UsersListPresenter : IUserListPresenter {
    val users= mutableListOf<GithubUser>()
    override var itemClickListener: ((UserItemView) -> Unit)?=null
    override fun getCount(): Int =users.size
    override fun bindView(view: UserItemView) {
        val user=users[view.pos]
        view.setLogin(user.login)
    }
}
    var usersListPresenter=UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        usersListPresenter.itemClickListener = { itemView ->
            //TODO: переход на экран пользователя
        }
    }
        fun loadData(){
            val users=userRepo.getUsers()
            usersListPresenter.users.addAll(users)
            viewState.updateList()
        }
}