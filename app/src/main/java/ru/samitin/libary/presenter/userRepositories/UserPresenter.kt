package ru.samitin.libary.presenter.userRepositories

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.samitin.libary.model.userRepositories.GithubRepo
import ru.samitin.libary.model.userRepositories.IGithubUserRepos
import ru.samitin.libary.model.userRepositories.RepoItemView
import ru.samitin.libary.view.cicirone.IScreens
import ru.samitin.libary.view.userRepositories.UserView

class UserPresenter(val uiScheduler: Scheduler, val usersRepo: IGithubUserRepos, val router: Router, val screens: IScreens,val repoUrl:String): MvpPresenter<UserView>() {

    class UserListRepositoriesPresenter : IUserListRepositoriesPresenter {
        val repos = mutableListOf<GithubRepo>()
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
         // val repo = userListRepositoriesPresenter.repos[itemView.pos]
         // router.navigateTo(screens.user(repo))
      }
  }

  fun loadData() {
      usersRepo.getRepos(repoUrl)
          .observeOn(uiScheduler)
          .subscribe({ repos ->
              userListRepositoriesPresenter.repos.clear()
              userListRepositoriesPresenter.repos.addAll(repos)
              viewState.updateList()
          }, { println("Error ${it.message}") })

  }

  fun backPressed(): Boolean {
      router.exit()
      return true
  }
}