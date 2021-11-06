package ru.samitin.libary.model.userRepositories

import ru.samitin.libary.model.ItemView

interface RepoOfUserItemView : ItemView {
    fun setRepoName(repoName:String)
}