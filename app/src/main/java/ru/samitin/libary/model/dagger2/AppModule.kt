package ru.samitin.libary.model.dagger2

import dagger.Module
import dagger.Provides
import ru.samitin.libary.view.cicirone.App

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

}