package com.example.simplechat.ui.chat.chats

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.example.cleanarchproject.ui.base.BaseFragment
import com.example.simplechat.R
import com.example.simplechat.data.model.User
import com.example.simplechat.databinding.FragmentChatsBinding
import com.example.simplechat.ui.chatroom.ChatRoomActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ChatsFragment : BaseFragment<FragmentChatsBinding>(), ClickListener {
    override val viewModel by viewModel<ChatsViewModel>()
    override val layoutId: Int = R.layout.fragment_chats

    private val adapter by lazy {
        ChatsAdapter(this, context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapter()
        initObserver()
    }

    private fun initObserver() {
        viewModel.listOfUsers.observe(this, Observer {
            Log.e("allUsers", it.toString())
            adapter.submitList(it)
        })

        viewModel.requestFail.observe(this, Observer {
            Log.e("allUsersFail", it)
        })
    }

    private fun setAdapter() {
        binding.chatsRecyclerView.adapter = adapter
    }

    companion object {
        fun newInstance() = ChatsFragment()
    }

    override fun itemClicked(user: User) {
        ChatRoomActivity.start(requireActivity(), user)
        activity?.finish()
    }
}


