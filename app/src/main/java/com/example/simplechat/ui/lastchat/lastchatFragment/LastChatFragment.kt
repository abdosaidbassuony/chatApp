package com.example.simplechat.ui.lastchat.lastchatFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.cleanarchproject.ui.base.BaseFragment
import com.example.simplechat.R
import com.example.simplechat.data.model.LastChat
import com.example.simplechat.data.prefs.Prefs
import com.example.simplechat.databinding.FragmentLastChatBinding
import com.example.simplechat.ui.chat.ChatActivity
import com.example.simplechat.ui.chatroom.ChatRoomActivity
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class LastChatFragment : BaseFragment<FragmentLastChatBinding>(), ClickListener {
    override val viewModel by viewModel<LastChatViewModel>()
    override val layoutId: Int = R.layout.fragment_last_chat
    private val prefs by inject<Prefs>()
    private val adapter by lazy {
        LastChatAdapter(this, context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // prefs.user.userId?.let {viewModel.getListOfUserIdChats(it) }
        initObserver()
        initListener()
        setupAdapter()
    }

    private fun setupAdapter() {
        binding.lastChatRecyclerView.adapter = adapter
    }

    private fun initListener() {
        binding.newChatFloatButton.setOnClickListener {
            ChatActivity.start(requireActivity())
        }
    }

    private fun initObserver() {
        viewModel.lastChatModel.observe(this, Observer {
            Log.e("lastChatListFragment", it.toString())
            adapter.submitList(it)
        })
        viewModel.userList.observe(this, Observer {
            viewModel.getUserMessages(it)
        })
    }

    companion object {
        fun newInstance() = LastChatFragment()
    }

    override fun onStart() {
        super.onStart()
        prefs.user.userId?.let { viewModel.getListOfUserIdChats(it) }
    }

    override fun itemClicked(user: LastChat) {
        user.user?.let { ChatRoomActivity.start(requireActivity(), it) }
    }


}
