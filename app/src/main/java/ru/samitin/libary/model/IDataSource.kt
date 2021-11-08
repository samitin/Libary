package ru.samitin.libary.model

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.samitin.libary.model.userRepositories.GithubRepository


interface IDataSource {
    @GET("/users")
    fun getUsers(): Single<List<GithubUser>>

    @GET
    fun getRepositories(@Url reposUrl: String): Single<List<GithubRepository>>
}