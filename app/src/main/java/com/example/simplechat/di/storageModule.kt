package com.example.simplechat.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.simplechat.data.prefs.Prefs
import com.example.simplechat.data.prefs.PrefsImp
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val storageModule = module {
    single<Prefs> { PrefsImp(get()) }
    single { getSharedPreferences(androidApplication()) }
}

fun getSharedPreferences(context: Application): SharedPreferences {
    return context.getSharedPreferences("chatApp", Context.MODE_PRIVATE)
}