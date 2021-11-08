package ru.samitin.libary.model.userRepositories

import io.reactivex.rxjava3.core.Single
import ru.samitin.libary.model.GithubUser

interface IGithubRepositoriesRepo {
   fun getRepositories(user: GithubUser): Single<List<GithubRepository>>
}