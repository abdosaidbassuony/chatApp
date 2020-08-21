package com.example.simplechat

import android.app.Application
import com.example.simplechat.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {

            androidLogger()

            androidContext(this@App)

            modules(
                listOf(viewModelModule)
            )
        }
    }
}