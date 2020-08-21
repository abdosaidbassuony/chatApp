package com.example.simplechat.data.firebase.authfirebase

import com.example.simplechat.data.model.User
import com.google.firebase.auth.FirebaseUser
import io.reactivex.rxjava3.core.Single

interface AuthFirebase {

    fun anonymousLogin():Single<FirebaseUser>

    fun saveUser(user: User):Single<User>
}