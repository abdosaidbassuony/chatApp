package com.example.simplechat.ui.chat

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.example.cleanarchproject.ui.base.BaseActivity
import com.example.simplechat.R
import com.example.simplechat.databinding.ActivityChatBinding
import com.example.simplechat.ui.chat.chats.ChatsFragment
import com.example.simplechat.utils.openFragment
import org.koin.android.viewmodel.ext.android.viewModel

class ChatActivity : BaseActivity<ActivityChatBinding>() {

    override val layoutId: Int = R.layout.activity_chat
    override val viewModel by viewModel<ChatSharedViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.openChats.observe(this, Observer {
            openFragment(R.id.chats_container, ChatsFragment.newInstance(), true)
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

        fun start(activity: Activity) {
            val intent = Intent(activity, ChatActivity::class.java)
            activity.startActivity(intent)
        }
    }


}
