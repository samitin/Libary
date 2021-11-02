package ru.samitin.libary.model


import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubUsersRepo(val api : IDataSource): IGithubUsersRepo {
    override fun getUsers()=api.getUsers().subscribeOn(Schedulers.io())
}