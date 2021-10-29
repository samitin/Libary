package ru.samitin.libary.view


import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.samitin.libary.R
import ru.samitin.libary.databinding.ActivityMainBinding
import ru.samitin.libary.presenter.MainPresenter
import ru.samitin.libary.view.cicirone.AndroidScreens
import ru.samitin.libary.view.cicirone.App
import ru.samitin.libary.view.cicirone.BackButtonListener

class MainActivity : MvpAppCompatActivity (), MainView {

    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter { MainPresenter(App.instance.router,AndroidScreens()) }
    private val navigator=AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.instance.navigatorHolder.removeNavigator()
    }
    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if(it is BackButtonListener && it.backPressed()){
                return
            }
        }
        presenter.backClicked()
    }
}