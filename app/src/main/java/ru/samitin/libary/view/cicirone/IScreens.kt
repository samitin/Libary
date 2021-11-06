package ru.samitin.libary.view.cicirone

import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.samitin.libary.model.GithubUser
import ru.samitin.libary.view.userRepositories.UserFragment
import ru.samitin.libary.view.userRepositories.forks.FragmentForks

interface IScreens {
    fun users(): Screen
    fun user(user:GithubUser):Screen
    fun fork(fork:Int):Screen
}
class AndroidScreens : IScreens {
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun user(user:GithubUser)=FragmentScreen(){UserFragment.newInstance(user)}
    override fun fork(fork: Int)=FragmentScreen(){FragmentForks.newInstance(fork)}

}