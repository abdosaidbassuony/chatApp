package com.example.simplechat.ui.lastchat.lastchatFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simplechat.R
import com.example.simplechat.data.model.LastChat
import com.example.simplechat.databinding.ChatsItemBinding

class LastChatAdapter(private val clickListener: ClickListener, private val context: Context?) :
    ListAdapter<LastChat, LastChatAdapter.ViewHolder>(LastAdapterCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(
        private val binding: ChatsItemBinding,
        private val context: Context?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: LastChat, clickListener: ClickListener) {
            binding.messageText.text = user.message.message
            binding.messageTime.text = user.message.messageTime
            binding.userNameText.text = user.user?.name
            context?.let { Glide.with(it).load(R.drawable.ic_user).into(binding.userCircleImage) }
            binding.root.setOnClickListener {
                clickListener.itemClicked(user)
            }
        }

        companion object {
            fun from(parent: ViewGroup, context: Context?): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ChatsItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, let { context })
            }
        }
    }
}

class LastAdapterCallback() : DiffUtil.ItemCallback<LastChat>() {
    override fun areItemsTheSame(oldItem: LastChat, newItem: LastChat): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: LastChat, newItem: LastChat): Boolean {
        return oldItem == newItem
    }
}

interface ClickListener {
    fun itemClicked(user: LastChat)
}