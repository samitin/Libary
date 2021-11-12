package ru.samitin.libary.model.dagger2

import dagger.Component
import ru.samitin.libary.presenter.MainPresenter
import ru.samitin.libary.presenter.cicerone.UsersPresenter
import ru.samitin.libary.presenter.userRepositories.UserPresenter
import ru.samitin.libary.view.MainActivity
import ru.samitin.libary.view.cicirone.UsersFragment
import ru.samitin.libary.view.userRepositories.UserFragment
import ru.samitin.libary.view.userRepositories.forks.FragmentForks
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)

    fun inject(userPresenter: UserPresenter)
    fun inject(forksFragment: FragmentForks)
}