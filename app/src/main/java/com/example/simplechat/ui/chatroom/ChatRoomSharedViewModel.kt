package com.example.simplechat.ui.chatroom

import com.example.cleanarchproject.ui.base.BaseViewModel
import com.example.simplechat.utils.SingleLiveEvent

class ChatRoomSharedViewModel : BaseViewModel() {
    val openChatRoom = SingleLiveEvent<Boolean>()
    var title = SingleLiveEvent<String>()

    init {
        openChatRoom()
    }

    private fun openChatRoom() {
        openChatRoom.value = true
    }

    fun setTitle(title: String){
        this.title.value = title
    }

}