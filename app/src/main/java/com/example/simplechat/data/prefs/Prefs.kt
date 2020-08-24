package com.example.simplechat.data.prefs

import com.example.simplechat.data.model.User

interface Prefs {
    var isUserLogin: Boolean
    var user: User
}