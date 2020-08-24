package com.example.simplechat.data.firebase.chatfirebase

import com.example.simplechat.data.model.LastChat
import com.example.simplechat.data.model.Message
import com.example.simplechat.data.model.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface ChatFirebase {

    fun getUsers(): Observable<List<User>>

    fun sendMessage(message: Message) :Single<Message>

    fun getMessages(senderId:String,receiverId:String):Observable<List<Message>>

    fun getUserMessage(senderId: String, receiverId: String): Observable<List<String>>

    fun getOneToOneChat(messageId: List<String>): Observable<List<Message>>

    fun getListOFUserIdChats(userId: String): Observable<List<String>>

    fun getUserById(listOfUserId: List<String>): Observable<List<User>>

    fun getLastMessage(senderId: String, receiverId: String, user: User): Observable<String>

    fun getLastChat(lastMessage: String?, user: User): Observable<List<LastChat>>


}