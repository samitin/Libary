package ru.samitin.libary

import androidx.appcompat.app.AppCompatActivity
import com.github.terrakok.cicerone.Router
import moxy.ktx.moxyPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.samitin.libary.presenter.MainPresenter
import ru.samitin.libary.view.MainActivity
import ru.samitin.libary.view.MainView
import ru.samitin.libary.view.cicirone.AndroidScreens
import ru.samitin.libary.view.cicirone.App
import ru.samitin.libary.view.cicirone.IScreens

class MainPresenterTest {

    private lateinit var presenter: MainPresenter

    @Mock
    private lateinit var router : Router
    @Mock
    private lateinit var screens : IScreens

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter =MainPresenter(router, screens )
    }
    @Test
    fun backClicked_test(){
        presenter.backClicked()
        Mockito.verify(router,Mockito.times(1)).exit()
    }

}

