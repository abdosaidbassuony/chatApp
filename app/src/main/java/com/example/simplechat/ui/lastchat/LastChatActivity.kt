package com.example.simplechat.ui.lastchat

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.example.cleanarchproject.ui.base.BaseActivity
import com.example.simplechat.R
import com.example.simplechat.data.model.User
import com.example.simplechat.databinding.ActivityLastChatBinding
import com.example.simplechat.ui.chatroom.ChatRoomActivity
import com.example.simplechat.ui.lastchat.lastchatFragment.LastChatFragment
import com.example.simplechat.utils.openFragment
import org.koin.android.viewmodel.ext.android.viewModel

class LastChatActivity : BaseActivity<ActivityLastChatBinding>() {
    override val layoutId: Int = R.layout.activity_last_chat
    override val viewModel by viewModel<LastChatSharedViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObserver()
    }

    private fun initObserver() {
        viewModel.openLastChat.observe(this, Observer {
            openFragment(R.id.last_chat_container, LastChatFragment.newInstance(), true)
        })
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val USER = "USER"
        fun start(activity: Activity, user: User?=null) {
            val intent = Intent(activity, LastChatActivity::class.java)
            intent.putExtra(USER, user)
            activity.startActivity(intent)

        }
    }
}
