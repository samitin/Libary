package ru.samitin.libary.model.userRepositories

import ru.samitin.libary.model.ItemView

interface RepoItemView:ItemView {
    fun setRepoName(repoName : String)
}