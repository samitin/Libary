package ru.samitin.libary.model.userRepositories

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface IDataSourceRepos {
    @GET
    fun getRepos(@Url reposUrl: String): Single<List<GithubRepo>>
}