package ru.samitin.libary.view.cicirone

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.samitin.libary.model.GithubUser
import ru.samitin.libary.view.userRepositories.UserFragment

interface IScreens {
    fun users(): Screen
    fun user(user:GithubUser):Screen
}
class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(user:GithubUser)=FragmentScreen(){UserFragment.newInstance(user)}
}