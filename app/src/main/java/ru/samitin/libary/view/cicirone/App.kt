package ru.samitin.libary.view.cicirone

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.samitin.libary.model.room.AndroidNetworkStatus
import ru.samitin.libary.model.room.Database
import ru.samitin.libary.model.room.INetworkStatus

class App : Application() {

    companion object{
        lateinit var instance: App
    }

    private val cicerone : Cicerone<Router> by lazy { Cicerone.create() }

    val navigatorHolder get() = cicerone.getNavigatorHolder()

    val router get() = cicerone.router


    override fun onCreate() {
        super.onCreate()
        instance =this
        Database.create(this)
    }
}