package com.my_project.myapplication

import android.app.Application
import com.my_project.myapplication.koin.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initModules()
    }

    private fun initModules() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    localHolderModule,
                    routerModule,
                    networkModule,
                    dbModule,
                    repositoryModule,
                    middlewareModule,
                    reducerModule,
                    componentModule
                )
            )
        }
    }
}