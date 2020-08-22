package com.example.simplechat.ui.chat

import com.example.cleanarchproject.ui.base.BaseViewModel
import com.example.simplechat.utils.SingleLiveEvent

class ChatSharedViewModel:BaseViewModel() {

    val openChats = SingleLiveEvent<Boolean>()

    init {
        openChats()
    }

    private fun openChats() {
        openChats.value = true
    }

}