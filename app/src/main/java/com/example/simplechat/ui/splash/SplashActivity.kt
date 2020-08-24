package com.example.simplechat.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.simplechat.ui.chat.ChatActivity
import com.example.simplechat.utils.openActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SplashActivity : AppCompatActivity() {
    private val viewModel by viewModel<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObservers()
    }

    private fun initObservers() {
        viewModel.isSaveUserSuccess.observe(this, Observer {
            Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
            Log.e("userData", it.toString())
            openActivity(ChatActivity::class.java)
            finish()
        })
        viewModel.isRequestFail.observe(this, Observer {
            Log.e("anonymousUserFail", it.toString())

        })
        viewModel.isUserLogin.observe(this, Observer {
            if (it) {
                startActivity(Intent(this@SplashActivity, ChatActivity::class.java))
                finish()
            } else {
                viewModel.anonymousLogin()
            }

        })


    }
}
