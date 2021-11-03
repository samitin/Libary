package ru.samitin.libary.model

interface ItemView{var pos:Int}

interface UserItemView : ItemView {
    fun setLogin(text:String)
    fun loadAvatar(url: String)
}
