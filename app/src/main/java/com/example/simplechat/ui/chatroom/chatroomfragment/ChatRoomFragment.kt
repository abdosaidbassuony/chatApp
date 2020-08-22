package com.example.simplechat.ui.chatroom.chatroomfragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.cleanarchproject.ui.base.BaseFragment
import com.example.simplechat.R
import com.example.simplechat.data.model.Message
import com.example.simplechat.data.model.User
import com.example.simplechat.databinding.FragmentChatRoomBinding
import com.example.simplechat.ui.chatroom.ChatRoomActivity.Companion.USER
import com.google.firebase.auth.FirebaseAuth
import org.koin.android.viewmodel.ext.android.viewModel


class ChatRoomFragment : BaseFragment<FragmentChatRoomBinding>() {
    override val viewModel by viewModel<ChatRoomViewModel>()
    override val layoutId: Int = R.layout.fragment_chat_room
    var receiverUser: User? = null
    private val adapter by lazy {
        ChatRoomAdapter()
    }
    private val senderId = FirebaseAuth.getInstance().currentUser?.uid

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
        senderId?.let { senderId ->
            receiverUser?.let { receiverUser ->
                receiverUser.userId?.let { receiverId ->
                    viewModel.getMessages(
                        senderId,
                        receiverId
                    )
                }
            }
        }

        initObservers()

        iniListener()



        setupAdapter()
    }

    private fun setupAdapter() {
        binding.chatRowRecycleView.adapter = adapter
    }

    private fun getArgs() {
        receiverUser = arguments?.getParcelable(USER)

    }

    private fun iniListener() {
        binding.sendMessageFloatingButton.setOnClickListener {
            val message = binding.messageEditText.text.toString()
            val senderId = FirebaseAuth.getInstance().currentUser?.uid
            val receiverId = receiverUser?.userId
            viewModel.sendMessage(
                Message(
                    senderId = senderId,
                    receiverId = receiverId,
                    message = message,
                    messageType = "text"
                )
            )
        }
    }

    private fun initObservers() {
        viewModel.isMessageSend.observe(this, Observer {
            Log.e("sendMessage", it.toString())
        })
        viewModel.requestFail.observe(this, Observer {
            Log.e("sendMessageFail", it)
        })
        viewModel.isGetMessages.observe(this, Observer {
            Log.e("allMessages", it.toString())
            adapter.submitList(it)
        })
    }

    companion object {
        fun newInstance(user: User) = ChatRoomFragment()
            .apply {
            arguments = Bundle().apply {
                putParcelable(USER, user)
            }
        }
    }
}
