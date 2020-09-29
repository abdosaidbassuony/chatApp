package com.example.simplechat.di

import com.example.simplechat.ui.chat.ChatSharedViewModel
import com.example.simplechat.ui.chat.chats.ChatsViewModel
import com.example.simplechat.ui.chatroom.ChatRoomSharedViewModel
import com.example.simplechat.ui.chatroom.chatroomfragment.ChatRoomViewModel
import com.example.simplechat.ui.chooseuser.ChooseUserViewModel
import com.example.simplechat.ui.lastchat.LastChatSharedViewModel
import com.example.simplechat.ui.lastchat.lastchatFragment.LastChatViewModel
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
        ChatsViewModel(get(), get())
    }
    viewModel {
        ChatRoomSharedViewModel()
    }
    viewModel {
        ChatRoomViewModel(get())
    }
    viewModel {
        ChooseUserViewModel(get(), get())
    }
    viewModel {
        LastChatSharedViewModel()
    }
    viewModel {
        LastChatViewModel(get(), get())
    }
}