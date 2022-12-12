package com.ilaydabykakova.foodrecipes

import android.app.Application
import com.ilaydabykakova.foodrecipes.data.di.appmodule
import com.ilaydabykakova.foodrecipes.data.di.networkModule
import com.ilaydabykakova.foodrecipes.data.di.roomModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level


class RecipesApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.ERROR)  // Koin Logger
            androidContext(this@RecipesApplication)
                modules(listOf(appmodule, networkModule, roomModule))
        }
    }


}