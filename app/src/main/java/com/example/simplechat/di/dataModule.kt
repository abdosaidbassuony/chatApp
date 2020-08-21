package com.example.simplechat.di

import com.example.simplechat.data.firebase.authfirebase.AuthFirebase
import com.example.simplechat.data.firebase.authfirebase.AuthFirebaseImp
import org.koin.dsl.bind
import org.koin.dsl.module

val dataModule = module {

    single {
        AuthFirebaseImp()
    } bind (AuthFirebase::class)
}