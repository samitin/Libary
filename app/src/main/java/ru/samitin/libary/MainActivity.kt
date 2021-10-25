package ru.samitin.libary


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.samitin.libary.databinding.ActivityMainBinding
import ru.samitin.libary.model.CountersModel
import ru.samitin.libary.model.GithubUsersRepo
import ru.samitin.libary.presenter.MainPresenter
import ru.samitin.libary.view.MainView
import ru.samitin.libary.view.UsersRVAdapter

class MainActivity : MvpAppCompatActivity (), MainView {

    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter { MainPresenter(GithubUsersRepo()) }
    private var adapter: UsersRVAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun init() {
        vb?.rvUsers?.layoutManager=LinearLayoutManager(this)
        adapter= UsersRVAdapter(presenter.usersListPresenter)
        vb?.rvUsers?.adapter=adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

}