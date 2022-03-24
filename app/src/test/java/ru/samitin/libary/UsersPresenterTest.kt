package ru.samitin.libary

import com.github.terrakok.cicerone.Router
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.samitin.libary.model.GithubUsersRepo
import ru.samitin.libary.presenter.cicerone.UsersPresenter
import org.junit.Assert.*

class UsersPresenterTest {
    private lateinit var presenter : UsersPresenter

    @Mock
    private lateinit var router : Router
    @Mock
    private lateinit var githubUsersRepo: GithubUsersRepo

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = UsersPresenter(githubUsersRepo,router)
    }
    @Test
    fun backClicked_test(){
        presenter.backPressed()
        Mockito.verify(router,Mockito.times(1)).exit()
    }
    @Test
    fun loadData_test(){
        presenter.loadData()
        assertEquals(githubUsersRepo.getUsers(),presenter.usersListPresenter.users)
    }
    @Test
    fun usersListPresenter_getCount_test(){
        assertEquals(githubUsersRepo.getUsers().size,presenter.usersListPresenter.getCount())
    }

}