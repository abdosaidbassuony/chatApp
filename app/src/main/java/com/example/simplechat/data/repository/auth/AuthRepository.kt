package com.example.simplechat.data.repository.auth

import com.example.simplechat.data.model.User
import com.google.firebase.auth.FirebaseUser
import io.reactivex.rxjava3.core.Single

interface AuthRepository {

    fun anonymousLogin(): Single<FirebaseUser>

    fun saveUser(user:User):Single<User>
}