package com.example.simplechat.data.repository.auth

import com.example.simplechat.data.firebase.authfirebase.AuthFirebase
import com.example.simplechat.data.model.User
import com.google.firebase.auth.FirebaseUser
import io.reactivex.rxjava3.core.Single

class AuthRepositoryImp(private val authFirebase: AuthFirebase):AuthRepository {
    override fun anonymousLogin(): Single<FirebaseUser> {
        return authFirebase.anonymousLogin()
    }

    override fun saveUser(user: User): Single<User> {
        return authFirebase.saveUser(user)
    }
}