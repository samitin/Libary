package ru.samitin.libary.view.userRepositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.samitin.libary.databinding.FragmentUserBinding
import ru.samitin.libary.model.ApiHolder
import ru.samitin.libary.model.GithubUser
import ru.samitin.libary.model.room.AndroidNetworkStatus
import ru.samitin.libary.model.room.Database
import ru.samitin.libary.model.userRepositories.RetrofitGithubRepositoriesRepo
import ru.samitin.libary.model.userRepositories.RoomRepositoriesCache
import ru.samitin.libary.presenter.userRepositories.UserPresenter
import ru.samitin.libary.view.cicirone.AndroidScreens
import ru.samitin.libary.view.cicirone.App
import ru.samitin.libary.view.cicirone.BackButtonListener
import javax.inject.Inject

const val USER_KEY="USER_KEY";
class UserFragment: MvpAppCompatFragment(),UserView, BackButtonListener {
    private var login=""
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

        UserPresenter(AndroidSchedulers.mainThread(),user).apply {
            App.instance.appComponent.inject(this)
        }

    }
    var adapterUserReposAdapter:UserReposAdapter?=null
    var _binding: FragmentUserBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        _binding=FragmentUserBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun backPressed() = presenter.backPressed()
    override fun setLogin(login: String) { this.login=login }

    override fun init() {
        binding.rvRepos.layoutManager= LinearLayoutManager(context)
        adapterUserReposAdapter=UserReposAdapter(presenter.userListRepositoriesPresenter)
        binding.rvRepos.adapter=adapterUserReposAdapter
    }

    override fun updateList() {
        adapterUserReposAdapter?.notifyDataSetChanged()
    }

}