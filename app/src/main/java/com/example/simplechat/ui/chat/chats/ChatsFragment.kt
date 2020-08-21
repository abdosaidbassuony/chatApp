package com.example.simplechat.ui.chat.chats

import com.example.cleanarchproject.ui.base.BaseFragment
import com.example.simplechat.R
import com.example.simplechat.databinding.FragmentChatsBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ChatsFragment : BaseFragment<FragmentChatsBinding>() {
    override val viewModel by viewModel<ChatsViewModel>()
    override val layoutId: Int = R.layout.fragment_chats


}


