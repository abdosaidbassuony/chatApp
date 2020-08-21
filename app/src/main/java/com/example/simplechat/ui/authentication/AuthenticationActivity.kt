package com.example.simplechat.ui.authentication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.cleanarchproject.ui.base.BaseActivity
import com.example.simplechat.R
import com.example.simplechat.databinding.ActivityAuthentecationBinding
import com.example.simplechat.ui.chat.ChatActivity
import com.example.simplechat.utils.openActivity
import org.koin.android.viewmodel.ext.android.viewModel

class AuthenticationActivity : BaseActivity<ActivityAuthentecationBinding>() {

    override val layoutId: Int = R.layout.activity_authentecation
    override val viewModel by viewModel<AuthenticationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initObservers()

        initListener()
    }

    private fun initListener() {
        binding.loginButton.setOnClickListener {
            viewModel.anonymousLogin()
        }
    }

    private fun initObservers() {
        viewModel.isSaveUserSuccess.observe(this, Observer {
            Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
            Log.e("userData", it.toString())
            openActivity(ChatActivity::class.java)
        })
        viewModel.isRequestFail.observe(this, Observer {
            Log.e("anonymousUserFail", it.toString())

        })
    }


}
