package ru.samitin.libary.presenter

import ru.samitin.libary.model.ItemView
import ru.samitin.libary.model.UserItemView

interface IListPresenter<V:ItemView> {
    var itemClickListener: ((V)->Unit)?
    fun bindView(view:V)
    fun getCount():Int
}

interface IUserListPresenter : IListPresenter<UserItemView>