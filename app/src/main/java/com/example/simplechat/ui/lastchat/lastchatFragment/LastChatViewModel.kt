package com.example.simplechat.ui.lastchat.lastchatFragment

import android.util.Log
import com.example.cleanarchproject.ui.base.BaseViewModel
import com.example.simplechat.data.model.LastChat
import com.example.simplechat.data.model.User
import com.example.simplechat.data.prefs.Prefs
import com.example.simplechat.data.repository.chat.ChatRepository
import com.example.simplechat.utils.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


class LastChatViewModel(
    private val chatRepository: ChatRepository,
    private val prefs: Prefs
) : BaseViewModel() {

    val lastChatModel = SingleLiveEvent<List<LastChat>>()
    val userList = SingleLiveEvent<List<User>>()

    init {
        prefs.user.userId?.let { getListOfUserIdChats(it) }
    }

    private fun getListOfUserIdChats(userId: String) {
        chatRepository.getListOFUserIdChats(userId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                Log.e("userIdListViewModel", it.toString())
                getUsersById(it)
            }, {

            })
    }

    private fun getUsersById(listOfId: List<String>) {
        chatRepository.getUserById(listOfId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
//                userList.value = it
                getUserMessages(it)
                Log.e("usersListViewModel", it.toString())
            }, {

            })
    }

    private fun getUserMessages(listUser: List<User>) {
        if (!listUser.isNullOrEmpty()) {
            listUser.forEach {
                prefs.user.userId?.let { senderId ->
                    it.userId?.let { receiverId ->
                        getLastMessageId(senderId, receiverId, it)

                    }
                }
            }
        }

    }

    private fun getLastMessageId(senderId: String, receiverId: String, user: User) {
        chatRepository.getLastMessage(senderId, receiverId, user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                getLastChat(it, user)
            }, {
                Log.e("getLastChat", it.message.toString())
            })
    }

    private fun getLastChat(message: String, user: User) {
        chatRepository.getLastChat(message, user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({ lastChat ->
                lastChatModel.value = lastChat
            }, {
                Log.e("getLastChat", it.message.toString())
            })
    }
}