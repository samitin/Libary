package ru.samitin.libary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import ru.samitin.libary.databinding.ActivityMainBinding
import ru.samitin.libary.presenter.MainPresenter
import ru.samitin.libary.view.MainView

class MainActivity : AppCompatActivity() , MainView {

    private var vb: ActivityMainBinding? = null
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        val listener = View.OnClickListener {
            presenter.counterClick(it.id)
        }

        vb?.btnCounter1?.setOnClickListener(listener)
        vb?.btnCounter2?.setOnClickListener(listener)
        vb?.btnCounter3?.setOnClickListener(listener)
    }

    //Подсказка к ПЗ: поделить на 3 отдельные функции и избавиться от index
    override fun setButtonText(index: Int, text: String) {
        when(index){
            0 -> vb?.btnCounter1?.text = text
            1 -> vb?.btnCounter2?.text = text
            2 -> vb?.btnCounter3?.text = text
        }
    }
}