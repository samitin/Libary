package ru.samitin.libary.view.cicirone


import android.app.Application
import ru.samitin.libary.model.dagger2.AppComponent
import ru.samitin.libary.model.dagger2.AppModule
import ru.samitin.libary.model.dagger2.DaggerAppComponent


class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}