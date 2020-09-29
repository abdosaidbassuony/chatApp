package com.example.simplechat.ui.lastchat.lastchatFragment

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.cleanarchproject.ui.base.BaseFragment
import com.example.simplechat.R
import com.example.simplechat.data.model.LastChat
import com.example.simplechat.databinding.FragmentLastChatBinding
import com.example.simplechat.ui.chat.ChatActivity
import com.example.simplechat.ui.chatroom.ChatRoomActivity
import com.example.simplechat.utils.RemoteConfigUtils
import org.koin.android.viewmodel.ext.android.viewModel


class LastChatFragment : BaseFragment<FragmentLastChatBinding>(), ClickListener {
    override val viewModel by viewModel<LastChatViewModel>()
    override val layoutId: Int = R.layout.fragment_last_chat
    private val adapter by lazy {
        LastChatAdapter(this, context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initListener()
        updateUi()
        setupAdapter()
    }

    private fun updateUi() {
        binding.newChatFloatButton.backgroundTintList =
            ColorStateList.valueOf(Color.parseColor(RemoteConfigUtils.getNextButtonColor()))
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
            adapter.notifyDataSetChanged()

        })

    }

    companion object {
        fun newInstance() = LastChatFragment()
    }


    override fun itemClicked(user: LastChat) {
        user.user?.let { ChatRoomActivity.start(requireActivity(), it) }
    }


}
