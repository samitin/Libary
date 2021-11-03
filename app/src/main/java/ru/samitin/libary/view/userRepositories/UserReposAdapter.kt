package ru.samitin.libary.view.userRepositories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.samitin.libary.databinding.ItemRepoBinding
import ru.samitin.libary.model.UserItemView
import ru.samitin.libary.model.userRepositories.RepoItemView
import ru.samitin.libary.presenter.userRepositories.IUserListRepositoriesPresenter
import ru.samitin.libary.view.UsersRVAdapter

class UserReposAdapter (val presenter: IUserListRepositoriesPresenter) : RecyclerView.Adapter<UserReposAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            itemView.setOnClickListener { presenter.itemClickListener?.invoke(this) }
        }

    override fun getItemCount() = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = presenter.bindView(holder.apply { pos = position })

    inner class ViewHolder(val vb: ItemRepoBinding) : RecyclerView.ViewHolder(vb.root),
        RepoItemView {
        override var pos = -1

        override fun setRepoName(repoName: String) = with(vb) {
            tvLoginRepo.text = repoName
        }

    }
}