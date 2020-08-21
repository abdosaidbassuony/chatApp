package com.example.simplechat.di

import com.example.simplechat.ui.authentication.AuthenticationViewModel
import com.example.simplechat.ui.chat.ChatSharedViewModel
import com.example.simplechat.ui.chat.chats.ChatsFragment
import com.example.simplechat.ui.chat.chats.ChatsViewModel
import com.example.simplechat.ui.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        ChatSharedViewModel()
    }

    viewModel {
        AuthenticationViewModel(get())
    }

    viewModel {
        SplashViewModel()
    }

    viewModel {
        ChatsViewModel()
    }
}