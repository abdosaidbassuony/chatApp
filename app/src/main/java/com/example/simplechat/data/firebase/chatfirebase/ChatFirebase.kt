package com.example.simplechat.data.firebase.chatfirebase

import com.example.simplechat.data.model.Message
import com.example.simplechat.data.model.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ChatFirebase {

    fun getUsers(): Observable<List<User>>

    fun sendMessage(message: Message) :Single<Message>

    fun getMessages(senderId:String,receiverId:String):Observable<List<Message>>
}