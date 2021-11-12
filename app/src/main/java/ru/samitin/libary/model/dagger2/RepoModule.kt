package ru.samitin.libary.model.dagger2

import dagger.Module
import dagger.Provides
import ru.samitin.libary.model.IDataSource
import ru.samitin.libary.model.IGithubUsersCache
import ru.samitin.libary.model.IGithubUsersRepo
import ru.samitin.libary.model.RetrofitGithubUsersRepo
import ru.samitin.libary.model.room.INetworkStatus
import ru.samitin.libary.model.userRepositories.IGithubRepositoriesRepo
import ru.samitin.libary.model.userRepositories.IRepositoriesCache
import ru.samitin.libary.model.userRepositories.RetrofitGithubRepositoriesRepo
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun usersRepo(api: IDataSource, networkStatus: INetworkStatus, cache: IGithubUsersCache): IGithubUsersRepo = RetrofitGithubUsersRepo(api, networkStatus, cache)

    @Singleton
    @Provides
    fun repositoriesRepo(api: IDataSource,networkStatus: INetworkStatus,repositoriesCache: IRepositoriesCache):IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(api,networkStatus,repositoriesCache)

}