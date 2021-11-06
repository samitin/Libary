package ru.samitin.libary.view.cicirone

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.samitin.libary.databinding.FragmentUsersBinding
import ru.samitin.libary.model.ApiHolder
import ru.samitin.libary.model.GlideImageLoader
import ru.samitin.libary.model.RetrofitGithubUsersRepo
import ru.samitin.libary.presenter.cicerone.UsersPresenter
import ru.samitin.libary.view.UsersRVAdapter

class UsersFragment : MvpAppCompatFragment(), UsersView,BackButtonListener {
    companion object {
        fun newInstance() = UsersFragment()
    }


    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter(
        AndroidSchedulers.mainThread(),
        RetrofitGithubUsersRepo(ApiHolder.api),
        App.instance.router, AndroidScreens(),
    ) }
    var adapter: UsersRVAdapter?=null
    private var vb:FragmentUsersBinding?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    =FragmentUsersBinding.inflate(inflater,container,false).also { vb=it }.root

    override fun init() {
        vb?.rvUsers?.layoutManager=LinearLayoutManager(context)
        adapter= UsersRVAdapter(presenter.usersListPresenter,GlideImageLoader())
        vb?.rvUsers?.adapter=adapter
    }

    override fun updateList() {
       adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean =presenter.backPressed()

    override fun onDestroy() {
        super.onDestroy()
        vb=null
    }
}