package ru.samitin.libary.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.samitin.libary.model.userRepositories.GithubRepo


interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>
}