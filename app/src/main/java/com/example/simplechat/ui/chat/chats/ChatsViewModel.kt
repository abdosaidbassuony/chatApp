package com.example.simplechat.ui.chat.chats

import android.os.Build
import com.example.cleanarchproject.ui.base.BaseViewModel
import com.example.simplechat.data.model.User
import com.example.simplechat.data.prefs.Prefs
import com.example.simplechat.data.repository.chat.ChatRepository
import com.example.simplechat.utils.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ChatsViewModel(private val chatRepository: ChatRepository,val prefs: Prefs) : BaseViewModel() {

    val listOfUsers = SingleLiveEvent<List<User>>()

    val requestFail = SingleLiveEvent<String>()

    init {
        getAllUser()
    }
   private var usersList :MutableList<User> = emptyList<User>().toMutableList()
    private fun getAllUser() {
        chatRepository.getAllUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                usersList = it.toMutableList()
                showUsers()
//                listOfUsers.value = it
            }, {
                requestFail.value = it.message
            })
    }

    fun showUsers(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            usersList.removeIf {
               prefs.user.userId==it.userId }
        }
        listOfUsers.value= usersList
    }
}