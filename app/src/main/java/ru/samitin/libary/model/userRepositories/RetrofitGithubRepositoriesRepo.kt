package ru.samitin.libary.model.userRepositories

import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.samitin.libary.model.GithubUser
import ru.samitin.libary.model.IDataSource
import ru.samitin.libary.model.room.Database
import ru.samitin.libary.model.room.INetworkStatus
import ru.samitin.libary.model.room.RoomGithubRepository


//Практическое задание 1 - вытащить кэширование в отдельный класс RoomRepositoriesCache и внедрить его сюда через интерфейс IRepositoriesCache
class RetrofitGithubRepositoriesRepo(val api: IDataSource, val networkStatus: INetworkStatus, val db: Database) : IGithubRepositoriesRepo {
    override fun getRepositories(user: GithubUser) = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            user.reposUrl?.let { url ->
                api.getRepositories(url)
                    .flatMap { repositories ->
                        Single.fromCallable {
                            val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
                            val roomRepos = repositories.map { RoomGithubRepository(it.id ?: "", it.name ?: "", it.forks ?: 0, roomUser.id) }
                            db.repositoryDao.insert(roomRepos)
                            repositories
                        }
                    }
            } ?: Single.error<List<GithubRepository>>(RuntimeException("User has no repos url")).subscribeOn(Schedulers.io())
        } else {
            Single.fromCallable {
                val roomUser = user.login?.let { db.userDao.findByLogin(it) } ?: throw RuntimeException("No such user in cache")
                db.repositoryDao.findForUser(roomUser.id).map { GithubRepository(it.id, it.name, it.forksCount) }
            }

        }
    }.subscribeOn(Schedulers.io())
}