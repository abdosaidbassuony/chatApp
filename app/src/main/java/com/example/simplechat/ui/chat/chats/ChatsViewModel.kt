package com.example.simplechat.ui.chat.chats

import com.example.cleanarchproject.ui.base.BaseViewModel
import com.example.simplechat.data.model.User
import com.example.simplechat.data.repository.chat.ChatRepository
import com.example.simplechat.utils.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ChatsViewModel(private val chatRepository: ChatRepository) : BaseViewModel() {

    val listOfUsers = SingleLiveEvent<List<User>>()

    val requestFail = SingleLiveEvent<String>()

    init {
        getAllUser()
    }

    private fun getAllUser() {
        chatRepository.getAllUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                listOfUsers.value = it
            }, {
                requestFail.value = it.message
            })
    }
}