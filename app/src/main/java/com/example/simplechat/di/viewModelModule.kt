package com.example.simplechat.di

import com.example.simplechat.ui.chat.ChatSharedViewModel
import com.example.simplechat.ui.chat.chats.ChatsViewModel
import com.example.simplechat.ui.chatroom.ChatRoomSharedViewModel
import com.example.simplechat.ui.chatroom.chatroomfragment.ChatRoomViewModel
import com.example.simplechat.ui.splash.SplashViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        ChatSharedViewModel()
    }

    viewModel {
        SplashViewModel(get())
    }

    viewModel {
        ChatsViewModel(get())
    }
    viewModel {
        ChatRoomSharedViewModel()
    }
    viewModel {
        ChatRoomViewModel(get())
    }
}