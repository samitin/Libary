package ru.samitin.libary.presenter.userRepositories

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.samitin.libary.model.GithubUser
import ru.samitin.libary.model.userRepositories.GithubRepository
import ru.samitin.libary.model.userRepositories.IGithubRepositoriesRepo
import ru.samitin.libary.model.userRepositories.RepoItemView
import ru.samitin.libary.view.cicirone.IScreens
import ru.samitin.libary.view.userRepositories.UserView
import javax.inject.Inject

class UserPresenter(val uiScheduler: Scheduler, val user: GithubUser?): MvpPresenter<UserView>() {
    @Inject lateinit var repositoriesRepo: IGithubRepositoriesRepo
    @Inject lateinit var router: Router
    @Inject lateinit var screens: IScreens

    class UserListRepositoriesPresenter : IUserListRepositoriesPresenter {
        val repos = mutableListOf<GithubRepository>()
        override var itemClickListener: ((RepoItemView) -> Unit)? = null
        override fun getCount(): Int = repos.size
        override fun bindView(view: RepoItemView) {
            val repo = repos[view.pos]
            repo.name?.let { view.setRepoName(it) }
        }
    }

    val userListRepositoriesPresenter = UserListRepositoriesPresenter()

  override fun onFirstViewAttach() {
      super.onFirstViewAttach()
      viewState.init()
      loadData()
      userListRepositoriesPresenter.itemClickListener = { itemView ->
          //TODO: переход на экран пользователя c помощью router.navigateTo
          val fork = userListRepositoriesPresenter.repos[itemView.pos].forks

          router.navigateTo(screens.fork(fork!!))
      }
  }

  fun loadData() {
      if (user != null) {
          repositoriesRepo.getRepositories(user)
              .observeOn(uiScheduler)
              .subscribe({ repos ->
                  userListRepositoriesPresenter.repos.clear()
                  userListRepositoriesPresenter.repos.addAll(repos)
                  viewState.updateList()
              }, { println("Error ${it.message}") })
      }

  }

  fun backPressed(): Boolean {
      router.exit()
      return true
  }
}