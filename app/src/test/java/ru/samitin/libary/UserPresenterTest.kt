package ru.samitin.libary

import com.github.terrakok.cicerone.Router
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.samitin.libary.model.GithubUser
import ru.samitin.libary.presenter.MainPresenter
import ru.samitin.libary.presenter.cicerone.UserPresenter
import ru.samitin.libary.view.cicirone.IScreens

class UserPresenterTest {
    private lateinit var presenter : UserPresenter

    @Mock
    private lateinit var router : Router
    @Mock
    private lateinit var githubUser: GithubUser

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = UserPresenter(githubUser,router)
    }
    @Test
    fun backClicked_test(){
        presenter.backPressed()
        Mockito.verify(router,Mockito.times(1)).exit()
    }
}