package ru.samitin.libary.model.userRepositories

import io.reactivex.rxjava3.schedulers.Schedulers


class RetrofitGithubUserRepos(val apiRepos : IDataSourceRepos): IGithubUserRepos {
    override fun getRepos(repoUrl:String)=apiRepos.getRepos(repoUrl).subscribeOn(Schedulers.io())
}