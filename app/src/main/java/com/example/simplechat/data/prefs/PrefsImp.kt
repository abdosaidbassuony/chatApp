package com.example.simplechat.data.prefs

import android.content.SharedPreferences
import com.example.simplechat.data.model.User
import com.google.gson.Gson

class PrefsImp(private val sharedPreferences: SharedPreferences) : Prefs {
    override var isUserLogin: Boolean
        get() = sharedPreferences.getBoolean(LOGGED_IN, false)
        set(value) {
            sharedPreferences.edit().putBoolean(LOGGED_IN, value).apply()
        }
    override var user: User
        get() {
            return Gson().fromJson(
                sharedPreferences.getString(USER, "{}"), User::class.java
            )
        }
        set(value) {
            sharedPreferences.edit().putString(USER, Gson().toJson(value)).apply()
        }

    companion object {
        const val LOGGED_IN = "loggedIn"
        const val USER = "user"
    }
}