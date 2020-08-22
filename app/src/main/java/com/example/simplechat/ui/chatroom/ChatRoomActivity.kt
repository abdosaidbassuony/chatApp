package com.example.simplechat.ui.chatroom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import com.example.cleanarchproject.ui.base.BaseActivity
import com.example.simplechat.R
import com.example.simplechat.data.model.User
import com.example.simplechat.databinding.ActivityChatRoomBinding
import com.example.simplechat.ui.chatroom.chatroomfragment.ChatRoomFragment
import com.example.simplechat.utils.openFragment
import org.koin.android.viewmodel.ext.android.viewModel

class ChatRoomActivity : BaseActivity<ActivityChatRoomBinding>() {
    override val layoutId: Int = R.layout.activity_chat_room
    override val viewModel by viewModel<ChatRoomSharedViewModel>()
    var user: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initObservers()
        getArgs()
    }

    private fun getArgs() {
        user = intent.getParcelableExtra(USER)
        user?.let {
            it.name?.let { title ->
                viewModel.setTitle(title)
            }
        }

    }

    private fun initObservers() {
        viewModel.openChatRoom.observe(this, Observer {
            user?.let { user -> ChatRoomFragment.newInstance(user) }?.let { fragment ->
                openFragment(
                    R.id.chat_room_container,
                    fragment, true
                )
            }
        })
        viewModel.title.observe(this, Observer {
            supportActionBar?.title = it
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
        fun start(activity: Activity, user: User) {
            val intent = Intent(activity, ChatRoomActivity::class.java)
            intent.putExtra(USER, user)
            activity.startActivity(intent)

        }
    }
}
