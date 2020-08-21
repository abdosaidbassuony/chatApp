package com.example.simplechat.data.repository.auth

import com.example.simplechat.data.model.Message
import com.example.simplechat.data.repository.chat.ChatRepository
import io.reactivex.rxjava3.core.Single

class ChatRepositoryImp : ChatRepository {
    override fun getAllMessages(): Single<Message> {
        TODO("Not yet implemented")
    }

    override fun sendMessage(message: Message): Single<Message> {
        TODO("Not yet implemented")
    }

}