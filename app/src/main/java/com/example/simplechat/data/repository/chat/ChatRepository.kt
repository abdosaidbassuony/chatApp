package com.example.simplechat.data.repository.chat

import com.example.simplechat.data.model.Message
import io.reactivex.rxjava3.core.Single

interface ChatRepository {

    fun getAllMessages(): Single<Message>

    fun sendMessage(message: Message): Single<Message>
}