package com.example.simplechat.data.firebase.chatfirebase

import android.util.Log
import com.example.simplechat.data.model.Message
import com.example.simplechat.data.model.User
import com.google.firebase.database.*
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class ChatFirebaseImp : ChatFirebase {

    private val userReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child("USER")

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
        //receiverNode
        message.senderId?.let { senderId ->
            message.receiverId?.let { receiverId ->
                reference.child("CHAT").child(receiverId).child(senderId).child("MESSAGE").push()
                    .setValue(message)
                    .addOnSuccessListener {
                        emitter.onSuccess(message)
                    }
                    .addOnFailureListener {
                        emitter.onError(it)
                    }
            }
        }
        //senderNode
        message.senderId?.let { senderId ->
            message.receiverId?.let { receiverId ->
                reference.child("CHAT").child(senderId).child(receiverId).child("MESSAGE")
                    .push()
                    .setValue(message)
                    .addOnSuccessListener {
                        emitter.onSuccess(message)
                    }
                    .addOnFailureListener {
                        emitter.onError(it)
                    }
            }
        }
    }

    override fun getMessages(senderId: String, receiverId: String):
            Observable<List<Message>> = Observable.create { emitter ->
        val messageReference: DatabaseReference =
            FirebaseDatabase.getInstance().reference.child("CHAT")
                .child(senderId).child(receiverId).child("MESSAGE")
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
                        Log.e("allMessages", list.toString())
                        list.add(message!!)
                    }
                }
                emitter.onNext(list)
            }
        }
        messageReference.addValueEventListener(messageValueEventListener)
    }
}
