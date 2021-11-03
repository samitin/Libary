package ru.samitin.libary.model.userRepositories

import io.reactivex.rxjava3.core.Single
import ru.samitin.libary.model.GithubUser

interface IGithubUserRepos {
    fun getRepos(login:String): Single<List<GithubRepo>>
}