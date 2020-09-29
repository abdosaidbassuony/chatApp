package com.example.simplechat.ui.chatroom.chatroomfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simplechat.data.model.Message
import com.example.simplechat.data.prefs.Prefs
import com.example.simplechat.databinding.ChatFromRawBinding
import com.example.simplechat.databinding.ChatToRowBinding

class ChatRoomAdapter(private val prefs: Prefs) :
    ListAdapter<Message, RecyclerView.ViewHolder>(ChatsCallback()) {

    private val SENDER = 0
    private val RECEIVER = 1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return if (viewType == SENDER) {
            val binding =
                ChatToRowBinding.inflate(layoutInflater, parent, false)

            SenderViewHolder(binding)
        } else {
            val binding =
                ChatFromRawBinding.inflate(layoutInflater, parent, false)

            ReceiverViewHolder(binding)
        }
    }

    class ReceiverViewHolder(
        private val fromBinding: ChatFromRawBinding
    ) : RecyclerView.ViewHolder(fromBinding.root) {

        fun bind(message: Message) {
            fromBinding.receiverMessage.text = message.message
        }
    }

    class SenderViewHolder(private val toBinding: ChatToRowBinding) :
        RecyclerView.ViewHolder(toBinding.root) {

        fun bind(message: Message) {
            toBinding.senderMessage.text = message.message
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (currentList[position].senderId.equals(prefs.user.userId)) {
            SENDER
        } else {
            RECEIVER
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            SENDER -> (holder as SenderViewHolder).bind(getItem(position))
            RECEIVER -> (holder as ReceiverViewHolder).bind(getItem(position))
        }
    }
}

class ChatsCallback() : DiffUtil.ItemCallback<Message>() {
    override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
        return oldItem == newItem
    }
}

