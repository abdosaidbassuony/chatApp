package com.example.simplechat.ui.chatroom.chatroomfragment

import com.example.cleanarchproject.ui.base.BaseViewModel
import com.example.simplechat.data.model.Message
import com.example.simplechat.data.repository.chat.ChatRepository
import com.example.simplechat.utils.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ChatRoomViewModel(
    private val chatRepository: ChatRepository
) : BaseViewModel() {

    val isMessageSend = SingleLiveEvent<Message>()

    val requestFail = SingleLiveEvent<String>()

    val isGetMessages = SingleLiveEvent<List<Message>>()


    fun sendMessage(message: Message) {
        chatRepository.sendMessage(message)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                isMessageSend.value = it
            }, {
                requestFail.value = it.message
            })
    }

    fun getMessages(senderId: String, receiverId: String) {
        chatRepository.getAllMessages(senderId, receiverId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                isGetMessages.value = it
            }, {
                requestFail.value = it.message
            })
    }
}