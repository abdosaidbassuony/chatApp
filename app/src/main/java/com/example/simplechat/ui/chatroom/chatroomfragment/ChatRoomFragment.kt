package com.example.simplechat.ui.chatroom.chatroomfragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.cleanarchproject.ui.base.BaseFragment
import com.example.simplechat.R
import com.example.simplechat.data.model.Message
import com.example.simplechat.data.model.User
import com.example.simplechat.data.prefs.Prefs
import com.example.simplechat.databinding.FragmentChatRoomBinding
import com.example.simplechat.ui.chatroom.ChatRoomActivity.Companion.USER
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*


class ChatRoomFragment : BaseFragment<FragmentChatRoomBinding>() {
    override val viewModel by viewModel<ChatRoomViewModel>()
    override val layoutId: Int = R.layout.fragment_chat_room
    var receiverUser: User? = null
    private val prefs by inject<Prefs>()
    private val adapter by lazy {
        ChatRoomAdapter(prefs)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()

            receiverUser?.let { receiverUser ->
                receiverUser.userId?.let { receiverId ->
                    prefs.user.userId?.let {senderId->
                        viewModel.getMessages(
                            senderId,
                            receiverId
                        )
                    }

            }
        }
        prefs.user.userId?.let { senderId ->
            receiverUser?.let { receiverUser ->
                receiverUser.userId?.let { receiverId ->
                    viewModel.getUserMessages(
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
            val senderId = prefs.user.userId
            val receiverId = receiverUser?.userId
            val messageTime = Calendar.getInstance().timeInMillis.toString()
            viewModel.sendMessage(
                Message(
                    senderId = senderId,
                    receiverId = receiverId,
                    message = message,
                    messageType = "text",
                    messageTime = messageTime
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
            Log.e("allMessagesFragment", it.toString())
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
