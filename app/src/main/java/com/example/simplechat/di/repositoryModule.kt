package com.example.simplechat.di

import com.example.simplechat.data.repository.auth.AuthRepository
import com.example.simplechat.data.repository.auth.AuthRepositoryImp
import com.example.simplechat.data.repository.chat.ChatRepository
import com.example.simplechat.data.repository.chat.ChatRepositoryImp
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    single {
        AuthRepositoryImp(get())
    } bind (AuthRepository::class)

    single {
        ChatRepositoryImp(get())
    } bind (ChatRepository::class)

}