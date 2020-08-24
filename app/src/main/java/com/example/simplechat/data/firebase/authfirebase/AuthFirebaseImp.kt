package com.example.simplechat.data.firebase.authfirebase

import com.example.simplechat.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.rxjava3.core.Single

class AuthFirebaseImp : AuthFirebase {
    private val auth = FirebaseAuth.getInstance()
    private val reference = FirebaseDatabase.getInstance().reference
    override fun anonymousLogin(): Single<FirebaseUser> = Single.create { emitter ->
        auth.signInAnonymously()
            .addOnSuccessListener {
                emitter.onSuccess(it.user)
            }
            .addOnFailureListener {
                emitter.onError(it)
            }
    }

    override fun saveUser(user: User): Single<User> = Single.create { emitter ->
        reference.child("users").child(user.userId.toString()).setValue(user)
            .addOnSuccessListener {
                emitter.onSuccess(user)
            }
            .addOnFailureListener {
                emitter.onError(it)
            }
    }

    override fun loginOrCreateUser(user: User): Single<User> = Single.create { emitter ->
        val loginUser: User
        when (user.name) {
            "Ahmed" -> {
                loginUser =
                    User(userId = user.userId, imageUrl = "", name = user.name)
                emitter.onSuccess(loginUser)
            }
            "Abdo" -> {
                loginUser =
                    User(userId = user.userId, imageUrl = "", name = user.name)
                emitter.onSuccess(loginUser)
            }
            "Fawzy" -> {
                loginUser =
                    User(userId = user.userId, imageUrl = "", name = user.name)
                emitter.onSuccess(loginUser)
            }
            "Mohamed" -> {
                loginUser =
                    User(userId = user.userId, imageUrl = "", name = user.name)
                emitter.onSuccess(loginUser)
            }
        }

    }
}
