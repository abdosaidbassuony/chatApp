package com.example.simplechat.ui.chooseuser

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.simplechat.R
import com.example.simplechat.data.model.User
import com.example.simplechat.databinding.UserItemBinding

class ChooserUserAdapter(private val clickListener: ClickListener, private val context: Context?) :
    ListAdapter<User, ChooserUserAdapter.ViewHolder>(ChatsCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent, let { context })
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder internal constructor(
        private val binding: UserItemBinding,
        private val context: Context?
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User, clickListener: ClickListener) {
            binding.userNameText.text = user.name
            context?.let { Glide.with(it).load(R.drawable.ic_user).into(binding.userCircleImage) }
            binding.root.setOnClickListener {
                clickListener.itemClicked(user)
            }

        }

        companion object {
            fun from(parent: ViewGroup, context: Context?): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UserItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, let { context })
            }
        }

    }
}

class ChatsCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.userId == newItem.userId
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}

interface ClickListener {
    fun itemClicked(user: User)
}