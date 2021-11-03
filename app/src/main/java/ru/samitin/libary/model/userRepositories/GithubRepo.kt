package ru.samitin.libary.model.userRepositories

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize


@Parcelize
data class GithubRepo(
    @Expose val name:String?=null,
    @Expose val reposUrl:String?=null
): Parcelable
