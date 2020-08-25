package com.example.simplechat.data.firebase.chatfirebase

import android.util.Log
import com.example.simplechat.data.model.LastChat
import com.example.simplechat.data.model.Message
import com.example.simplechat.data.model.User
import com.google.firebase.database.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class ChatFirebaseImp : ChatFirebase {

    private val userReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child("users")

    private val reference = FirebaseDatabase.getInstance().reference

    override fun getUsers(): Observable<List<User>> = Observable.create { emitter ->

        val userValueListener = object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                emitter.onError(error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<User>()
                var user: User?
                snapshot.children.forEach {
                    user = it.getValue(User::class.java)
                    if (user != null) {
                        list.add(user!!)
                    }
                }
                emitter.onNext(list)
            }
        }
        userReference.addValueEventListener(userValueListener)
    }

    override fun sendMessage(message: Message): Single<Message> = Single.create { emitter ->
        reference.child("messages").push().setValue(message)
            .addOnSuccessListener {
                emitter.onSuccess(message)
            }
            .addOnFailureListener {
                emitter.onError(it)
            }
    }

    override fun getMessages(senderId: String, receiverId: String):
            Observable<List<Message>> = Observable.create { emitter ->
        val messageReference: DatabaseReference =
            FirebaseDatabase.getInstance().reference.child("messages")

        val messageValueEventListener = object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                emitter.onError(error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<Message>()
                var message: Message?
                snapshot.children.forEach {
                    message = it.getValue(Message::class.java)
                    if (message != null) {
//                        Log.e("allMessages", list.toString())
                        list.add(message!!)
                        if (senderId == message?.senderId && receiverId == message?.receiverId) {
                            if (senderId > receiverId) {
                                it.key?.let { key -> setUserMessages(senderId, receiverId, key) }
                                it.key?.let { key -> setUserMessages(receiverId, senderId, key) }
                            } else {
                                it.key?.let { key -> setUserMessages(receiverId, senderId, key) }
                                it.key?.let { key -> setUserMessages(senderId, receiverId, key) }

                            }

                        }

                    }
                }

                emitter.onNext(list)
            }
        }
        messageReference.addValueEventListener(messageValueEventListener)
    }

    private fun setUserMessages(senderId: String, receiverId: String, key: String) {
        val hashMap = HashMap<String, Any>()
        hashMap[key] = 1
        reference.child("user-messages").child(senderId).child(receiverId).updateChildren(hashMap)
    }

    override fun getUserMessage(senderId: String, receiverId: String):
            Observable<List<String>> = Observable.create { emitter ->
        val userMessages: DatabaseReference =
            if (senderId > receiverId) {
                FirebaseDatabase.getInstance().reference.child("user-messages").child(senderId)
                    .child(receiverId)
            } else {
                FirebaseDatabase.getInstance().reference.child("user-messages").child(receiverId)
                    .child(senderId)
            }

        val messageValueEventListener = object : ValueEventListener {

            override fun onCancelled(error: DatabaseError) {
                emitter.onError(error.toException())
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val listOfString = ArrayList<String>()
                snapshot.children.forEach {
                    val messageId = it.key
                    messageId?.let { messageId -> listOfString.add(messageId) }
                }
                emitter.onNext(listOfString)
                Log.e("userMessagesFirebase", listOfString.toString())
            }
        }
        userMessages.addValueEventListener(messageValueEventListener)
    }

    override fun getOneToOneChat(messageId: List<String>): Observable<List<Message>> =
        Observable.create { emitter ->
            val messageList = ArrayList<Message>()

            val oneToOneMessage: DatabaseReference =
                FirebaseDatabase.getInstance().reference.child("messages")
            val onToOneValueEventListener = object : ValueEventListener {

                override fun onCancelled(error: DatabaseError) {
                    emitter.onError(error.toException())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    messageId.forEach { messageId ->
                        snapshot.children.forEach { dataSnapshot ->
                            if (dataSnapshot.key == messageId) {
                                val message = dataSnapshot.getValue(Message::class.java)
                                message?.let { message -> messageList.add(message) }
                            }
                        }
                        emitter.onNext(messageList)
                    }
                }
            }
            oneToOneMessage.addValueEventListener(onToOneValueEventListener)
        }


    override fun getListOFUserIdChats(userId: String): Observable<List<String>> =
        Observable.create { emitter ->
            val userIdList = ArrayList<String>()
            val userIdChats =
                FirebaseDatabase.getInstance().reference.child("user-messages").child(userId)
            val userIdChatsValueEventListener = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    emitter.onError(error.toException())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    userIdList.clear()
                    snapshot.children.forEach { dataSnapshot ->
                        userIdList.add(dataSnapshot.key.toString())
                    }
                    Log.e("userIdList", userIdList.toString())
                    emitter.onNext(userIdList)
                }
            }
            userIdChats.addValueEventListener(userIdChatsValueEventListener)


        }

    override fun getUserById(listOfUserId: List<String>): Observable<List<User>> =
        Observable.create { emitter ->
            val usersList = ArrayList<User>()

            val userReference = FirebaseDatabase.getInstance().reference.child("users")
            val userValueEventListener = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    emitter.onError(error.toException())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    listOfUserId.forEach { userId ->
                        snapshot.children.forEach { dataSnapshot ->
                            if (userId == dataSnapshot.key) {
                                val user = dataSnapshot.getValue(User::class.java)
                                user?.let { usersList.add(it) }
                            }
                        }
                    }
                    Log.e("usersList", usersList.toString())
                    emitter.onNext(usersList)
                }
            }
            userReference.addValueEventListener(userValueEventListener)

        }

    override fun getLastMessage(
        senderId: String,
        receiverId: String,
        user: User
    ): Observable<String> =
        Observable.create { emitter ->
            val userMessage =
                FirebaseDatabase.getInstance().reference.child("user-messages").child(senderId)
                    .child(receiverId)
            val userMeValueEventListener = object : ValueEventListener {
                override fun onCancelled(error: DatabaseError) {
                    emitter.onError(error.toException())
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    emitter.onNext(snapshot.children.last().key)

                }
            }
            userMessage.addValueEventListener(userMeValueEventListener)

        }

    val lastChatList = ArrayList<LastChat>()
    override fun getLastChat(lastMessage: String?, user: User): Observable<List<LastChat>> =
        Observable.create { emitter ->
            lastChatList.clear()
            val lastMessageReference = FirebaseDatabase.getInstance().reference.child("messages")
            val lastMessageValueEventListener = object : ValueEventListener {

                override fun onCancelled(error: DatabaseError) {
                    emitter.onError(error.toException())
                }

                override fun onDataChange(snapshot: DataSnapshot) {

                    snapshot.children.forEach { dataSnapshot ->
                        Log.e("LastMessageId", lastMessage.toString())
                        if (dataSnapshot.key == lastMessage) {
                            val message = dataSnapshot.getValue(Message::class.java)
                            Log.e("message", message.toString())
                            message?.let { lastMessage -> LastChat(user, lastMessage) }
                                ?.let { list -> lastChatList.add(list) }
                            emitter.onNext(lastChatList)
                        }
                    }
                }
            }
            lastMessageReference.addValueEventListener(lastMessageValueEventListener)
//
        }

}


