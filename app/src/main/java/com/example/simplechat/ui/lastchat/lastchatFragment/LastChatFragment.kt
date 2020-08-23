package com.example.simplechat.ui.lastchat.lastchatFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cleanarchproject.ui.base.BaseFragment
import com.example.simplechat.R
import com.example.simplechat.databinding.FragmentLastChatBinding
import com.example.simplechat.ui.chat.ChatActivity
import org.koin.android.viewmodel.ext.android.viewModel


class LastChatFragment : BaseFragment<FragmentLastChatBinding>() {
    override val viewModel by viewModel<LastChatViewModel>()
    override val layoutId: Int = R.layout.fragment_last_chat

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserver()
        initListener()
    }

    private fun initListener() {
        binding.newChatFloatButton.setOnClickListener {
            ChatActivity.start(requireActivity())
        }
    }

    private fun initObserver() {

    }

    companion object {
        fun newInstance() = LastChatFragment()
    }


}
