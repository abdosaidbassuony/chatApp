package com.example.simplechat.ui.lastchat

import com.example.cleanarchproject.ui.base.BaseViewModel
import com.example.simplechat.utils.SingleLiveEvent

class LastChatSharedViewModel:BaseViewModel() {
    val openLastChat = SingleLiveEvent<Boolean>()

    init {
        openLastChat()
    }

    private fun openLastChat() {
        openLastChat.value = true
    }


}