package com.example.simplechat.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.simplechat.ui.authentication.AuthenticationActivity
import com.example.simplechat.ui.chat.ChatActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {
    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.isUserLogin.observe(this, Observer {
            if (it) {
                startActivity(Intent(this@SplashActivity, ChatActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, AuthenticationActivity::class.java))

            }
            finish()
        })

    }
}
