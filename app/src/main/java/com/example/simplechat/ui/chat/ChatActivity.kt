package com.example.simplechat.ui.chat

import com.example.cleanarchproject.ui.base.BaseActivity
import com.example.simplechat.R
import com.example.simplechat.databinding.ActivityChatBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ChatActivity : BaseActivity<ActivityChatBinding>() {

    override val layoutId: Int = R.layout.activity_chat
    override val viewModel by viewModel<ChatSharedViewModel>()


}
