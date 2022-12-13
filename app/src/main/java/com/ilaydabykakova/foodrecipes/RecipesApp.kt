package com.ilaydabykakova.foodrecipes

import android.app.Application
import com.ilaydabykakova.foodrecipes.di.appmodule
import com.ilaydabykakova.foodrecipes.di.networkModule
import com.ilaydabykakova.foodrecipes.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level


class RecipesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)  // Koin Logger
            androidContext(this@RecipesApp)
                modules(listOf(appmodule, networkModule, roomModule))
        }
    }


}