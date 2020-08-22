package com.example.simplechat.data.repository.chat

import com.example.simplechat.data.model.Message
import com.example.simplechat.data.model.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ChatRepository {

    fun getAllMessages(senderId:String,receiverId:String): Observable<List<Message>>

    fun sendMessage(message: Message): Single<Message>

    fun getAllUser():Observable<List<User>>

}