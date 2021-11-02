package ru.samitin.libary.view.cicirone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.samitin.libary.databinding.FragmentUserBinding
import ru.samitin.libary.model.GithubUser
import ru.samitin.libary.model.GithubUsersRepo
import ru.samitin.libary.model.UserItemView
import ru.samitin.libary.presenter.cicerone.UserPresenter
import ru.samitin.libary.presenter.cicerone.UsersPresenter

const val USER_KEY="USER_KEY";
class UserFragment: MvpAppCompatFragment(),UserView,BackButtonListener {
    companion object{
        fun newInstance(user : GithubUser):UserFragment{
            val bundle=Bundle()
            bundle.putParcelable(USER_KEY,user)
            val userFragment= UserFragment()
            userFragment.arguments=bundle
            return userFragment
        }
    }
    val presenter: UserPresenter by moxyPresenter {
        val user=arguments?.getParcelable<GithubUser>(USER_KEY)
        UserPresenter( App.instance.router,user!!)

    }
    var _binding: FragmentUserBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        _binding=FragmentUserBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.userLogin.text=presenter.user.login

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun backPressed() = presenter.backPressed()
    override fun setLogin(login: String) {
        binding.userLogin.text=login
    }

}