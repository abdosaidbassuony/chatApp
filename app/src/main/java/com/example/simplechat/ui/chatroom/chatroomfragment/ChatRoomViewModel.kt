package com.example.simplechat.ui.chatroom.chatroomfragment

import android.util.Log
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

    var messageList: List<Message>? = emptyList()
    fun getMessages(senderId: String, receiverId: String) {
        chatRepository.getAllMessages(senderId, receiverId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
//                messageList = it
//                isGetMessages.value = it
                Log.e("userMessagesViewModel",it.toString())
//                showMessage(senderId,receiverId)
            }, {
                requestFail.value = it.message
            })
    }

    fun getUserMessages(senderId: String, receiverId: String) {
        chatRepository.getUserMessage(senderId, receiverId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                getOneToOneMessage(it)
                Log.e("userMessagesViewModel",it.toString())
//                showMessage(senderId,receiverId)
            }, {
                requestFail.value = it.message
            })
    }

    private fun getOneToOneMessage(messageIdList: List<String>) {
        chatRepository.getOneToOneChat(messageIdList)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                isGetMessages.value = it
            },{

            })
    }

}