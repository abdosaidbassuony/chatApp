package com.example.simplechat.data.repository.auth

import com.example.simplechat.data.model.User
import io.reactivex.rxjava3.core.Single

interface AuthRepository {

    fun anonymousLogin(): Single<User>
}