package ru.samitin.libary.model.dagger2

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.samitin.libary.model.IGithubUsersCache
import ru.samitin.libary.model.RoomGithubUsersCache
import ru.samitin.libary.model.room.Database
import ru.samitin.libary.model.userRepositories.IRepositoriesCache
import ru.samitin.libary.model.userRepositories.RoomRepositoriesCache
import ru.samitin.libary.view.cicirone.App
import javax.inject.Singleton

@Module
class CacheModule {

    @Provides
    @Singleton
    fun database(app:App): Database = Room.databaseBuilder(app, Database::class.java, Database.DB_NAME)
        .build()


    @Singleton
    @Provides
    fun usersCache(database: Database): IGithubUsersCache = RoomGithubUsersCache(database)
    @Singleton
    @Provides
    fun repositoriesCache(database: Database): IRepositoriesCache = RoomRepositoriesCache(database)
}