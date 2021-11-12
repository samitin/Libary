package ru.samitin.libary.view.userRepositories.forks

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.terrakok.cicerone.Router
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.samitin.libary.databinding.FragmentForksBinding
import ru.samitin.libary.presenter.userRepositories.forks.ForksPresenter
import ru.samitin.libary.view.cicirone.App
import ru.samitin.libary.view.cicirone.BackButtonListener
import javax.inject.Inject

const val FORKS_KEY="FORKS_KEY"
class FragmentForks:  MvpAppCompatFragment(),ForksView, BackButtonListener {

    companion object{
        fun newInstance(forks : Int): FragmentForks {
            val bundle= Bundle()
            bundle.putInt(FORKS_KEY,forks)
            val fragmentForks= FragmentForks()
            fragmentForks.arguments=bundle
            App.instance.appComponent.inject(fragmentForks)
            return fragmentForks
        }
    }
    @Inject
    lateinit var router: Router
    val presenter: ForksPresenter by moxyPresenter {
        ForksPresenter(router)
    }

    var _binding: FragmentForksBinding?=null
    private val binding get() = _binding!!

    var fork:Int?=null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?{
        _binding= FragmentForksBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fork=arguments?.getInt(FORKS_KEY)
        binding.tvForks.text="Количество форков ${fork}"
    }
    override fun setForks(forks: Int) {
        fork=forks
    }

    override fun backPressed() = presenter.backPressed()

}