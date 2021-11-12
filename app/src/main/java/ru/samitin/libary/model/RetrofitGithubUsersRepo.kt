package ru.samitin.libary.model



import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.samitin.libary.model.room.Database
import ru.samitin.libary.model.room.INetworkStatus
import ru.samitin.libary.model.room.RoomGithubUser

//Практическое задание 1 - вытащить кэширование в отдельный класс RoomUserCache и внедрить его сюда через интерфейс IUserCache
class RetrofitGithubUsersRepo(val api: IDataSource, val networkStatus: INetworkStatus, val userCach:IGithubUsersCache) : IGithubUsersRepo {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getUsers()
                .flatMap { users ->
                    Single.fromCallable {
                        userCach.saveUsersCach(users)
                        users
                    }
                }
        } else {
            Single.fromCallable {
               userCach.getUsersCach()
            }
        }
    }.subscribeOn(Schedulers.io())
}
interface IGithubUsersCache{
    fun saveUsersCach(userList:List<GithubUser>)
    fun getUsersCach():List<GithubUser>
}
class RoomGithubUsersCache(private val db:Database):IGithubUsersCache{
    override fun saveUsersCach(users:List<GithubUser>)  {
        val roomUsers = users.map { user -> RoomGithubUser(user.id ?: "", user.login ?: "", user.avatarUrl ?: "", user.reposUrl ?: "") }
        db.userDao.insert(roomUsers)
    }

    override fun getUsersCach():List<GithubUser> =
        db.userDao.getAll().map { roomUser -> GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)

    }
}