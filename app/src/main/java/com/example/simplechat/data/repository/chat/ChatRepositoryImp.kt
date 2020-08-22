package com.example.simplechat.data.repository.chat

import com.example.simplechat.data.firebase.chatfirebase.ChatFirebase
import com.example.simplechat.data.model.Message
import com.example.simplechat.data.model.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class ChatRepositoryImp(private val chatFirebase: ChatFirebase) : ChatRepository {
    override fun getAllMessages(senderId: String, receiverId: String): Observable<List<Message>> {
        return chatFirebase.getMessages(senderId, receiverId)
    }

    override fun sendMessage(message: Message): Single<Message> {
        return chatFirebase.sendMessage(message)
    }

    override fun getAllUser(): Observable<List<User>> {
        return chatFirebase.getUsers()
    }
}