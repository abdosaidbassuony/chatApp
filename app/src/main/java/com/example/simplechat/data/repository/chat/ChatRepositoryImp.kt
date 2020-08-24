package com.example.simplechat.data.repository.chat

import com.example.simplechat.data.firebase.chatfirebase.ChatFirebase
import com.example.simplechat.data.model.LastChat
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

    override fun getUserMessage(senderId: String, receiverId: String): Observable<List<String>> {
        return chatFirebase.getUserMessage(senderId, receiverId)
    }

    override fun getOneToOneChat(messageId: List<String>): Observable<List<Message>> {
        return chatFirebase.getOneToOneChat(messageId)
    }

    override fun getListOFUserIdChats(userId: String): Observable<List<String>> {
        return chatFirebase.getListOFUserIdChats(userId)
    }

    override fun getUserById(listOfUserId: List<String>): Observable<List<User>> {
        return chatFirebase.getUserById(listOfUserId)
    }

    override fun getLastMessage(senderId: String, receiverId: String, user: User): Observable<String> {
        return chatFirebase.getLastMessage(senderId,receiverId,user)
    }

    override fun getLastChat(lastMessage: String?, user: User): Observable<List<LastChat>> {
        return chatFirebase.getLastChat(lastMessage,user)
    }
}