package com.example.simplechat

import android.app.Application
import com.example.simplechat.di.dataModule
import com.example.simplechat.di.repositoryModule
import com.example.simplechat.di.storageModule
import com.example.simplechat.di.viewModelModule
import com.example.simplechat.utils.RemoteConfigUtils
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        RemoteConfigUtils.init()
        initKoin()
    }

    private fun initKoin() {
        startKoin {

            androidLogger()

            androidContext(this@App)

            modules(
                listOf(viewModelModule, dataModule, repositoryModule, storageModule)
            )
        }
    }
}