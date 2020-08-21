package com.example.simplechat.data.model

data class Message(
    val senderId: String?,
    val receiverId: String?,
    val message: String?,
    val messageType: String?
)