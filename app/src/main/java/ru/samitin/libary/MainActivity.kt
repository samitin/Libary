package ru.samitin.libary


import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.samitin.libary.databinding.ActivityMainBinding
import ru.samitin.libary.model.CountersModel
import ru.samitin.libary.presenter.MainPresenter
import ru.samitin.libary.view.MainView

class MainActivity : MvpAppCompatActivity (), MainView {

    private var vb: ActivityMainBinding? = null
    private val presenter by moxyPresenter { MainPresenter(CountersModel()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btnCounter1?.setOnClickListener { presenter.counterOneClick() }
        vb?.btnCounter2?.setOnClickListener { presenter.counterTwoClick() }
        vb?.btnCounter3?.setOnClickListener { presenter.counterThreeClick() }
    }
    //Подсказка к ПЗ: поделить на 3 отдельные функции и избавиться от index
    override fun setButtonOneText(text: String) { vb?.btnCounter1?.text = text }
    override fun setButtonTwoText(text: String) { vb?.btnCounter2?.text = text }
    override fun setButtonThreeText(text: String) { vb?.btnCounter3?.text = text }
}