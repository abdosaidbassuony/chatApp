package com.example.simplechat.utils

import android.util.Log
import com.example.simplechat.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

object RemoteConfigUtils {
    private const val TAG = "RemoteConfigUtils"

    private const val START_NEW_CHAT_BUTTON = "start_new_chat_button_color"

    private val DEFAULTS: HashMap<String, Any> =
        hashMapOf(
            START_NEW_CHAT_BUTTON to "#03DAC5"
        )

    private lateinit var remoteConfig: FirebaseRemoteConfig

    fun init() {
        remoteConfig = getFirebaseRemoteConfig()
    }

    private fun getFirebaseRemoteConfig(): FirebaseRemoteConfig {

        val remoteConfig = Firebase.remoteConfig

        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) {
                0 // Kept 0 for quick debug
            } else {
                60 * 60 // Change this based on your requirement
            }
        }

        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(DEFAULTS)

        remoteConfig.fetchAndActivate().addOnCompleteListener {
            Log.d(TAG, "addOnCompleteListener")
        }

        return remoteConfig
    }

    fun getNextButtonColor(): String = remoteConfig.getString(START_NEW_CHAT_BUTTON)

}