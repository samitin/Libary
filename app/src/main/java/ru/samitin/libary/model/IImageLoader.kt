package ru.samitin.libary.model

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}