package ru.samitin.libary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.samitin.libary.databinding.ActivityMainBinding
import ru.samitin.libary.presenter.ButtonType
import ru.samitin.libary.presenter.MainPresenter
import ru.samitin.libary.view.MainView

class MainActivity : AppCompatActivity() , MainView {

    private var vb: ActivityMainBinding? = null
    val presenter = MainPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)

        vb?.btnCounter1?.setOnClickListener { presenter.counterClick(ButtonType.SSECOND) }
        vb?.btnCounter2?.setOnClickListener { presenter.counterClick(ButtonType.FIRST) }
        vb?.btnCounter3?.setOnClickListener{presenter.counterClick(ButtonType.THIRD)}
    }

    //Подсказка к ПЗ: поделить на 3 отдельные функции и избавиться от index
    override fun setButtonText(type: ButtonType, text: String) {
        when(type){
           ButtonType.SSECOND -> vb?.btnCounter1?.text = text
            ButtonType.FIRST -> vb?.btnCounter2?.text = text
            ButtonType.THIRD -> vb?.btnCounter3?.text = text
        }
    }
}