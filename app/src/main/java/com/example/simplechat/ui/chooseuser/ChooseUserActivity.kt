package com.example.simplechat.ui.chooseuser

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.cleanarchproject.ui.base.BaseActivity
import com.example.simplechat.R
import com.example.simplechat.data.model.User
import com.example.simplechat.databinding.ActivityChooseUserBinding
import com.example.simplechat.ui.lastchat.LastChatActivity
import org.koin.android.viewmodel.ext.android.viewModel

class ChooseUserActivity : BaseActivity<ActivityChooseUserBinding>(), ClickListener {
    override val layoutId: Int = R.layout.activity_choose_user
    override val viewModel by viewModel<ChooseUserViewModel>()
    private val adapter by lazy {
        ChooserUserAdapter(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupAdapter()
        initObserver()
    }

    private fun initObserver() {
        viewModel.isUserLogin.observe(this, Observer {
            if (it) {
                LastChatActivity.start(this)
                finish()
            }
        })
        viewModel.isSaveUserSuccess.observe(this, Observer {
            Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
            Log.e("userData", it.toString())
            LastChatActivity.start(this, it)
            finish()
        })
        viewModel.isRequestFail.observe(this, Observer {
            Log.e("anonymousUserFail", it.toString())

        })
    }

    private fun setupAdapter() {
        binding.chooseUserRecyclerView.adapter = adapter
        adapter.submitList(usersList())
    }

    private fun usersList(): List<User> {
        val list = ArrayList<User>()
        list.add(User("Ahmed", "", ""))
        list.add(User("Abdo", "", ""))
        list.add(User("Fawzy", "", ""))
        list.add(User("Mohamed", "", ""))
        return list.toList()

    }

    override fun itemClicked(user: User) {
        viewModel.anonymousLogin(user)
    }
}
