package com.example.simplechat.ui.authentication

import com.example.cleanarchproject.ui.base.BaseViewModel
import com.example.simplechat.data.model.User
import com.example.simplechat.data.repository.auth.AuthRepository
import com.example.simplechat.utils.SingleLiveEvent
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class AuthenticationViewModel(
    private val authRepository: AuthRepository,
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
) : BaseViewModel() {

    val isSaveUserSuccess = SingleLiveEvent<User>()

    val isRequestFail = SingleLiveEvent<String>()

    val isUserLogin = SingleLiveEvent<Boolean>()

    init {
        checkIsUserLogin()
    }

    fun anonymousLogin() {
        authRepository.anonymousLogin()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                saveUser(User("userName", "", it.uid))
            }, {
                isRequestFail.value = it.message.toString()
            })
    }

    private fun saveUser(user: User) {
        authRepository.saveUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                isSaveUserSuccess.value = it
            }, {
                isRequestFail.value = it.message.toString()
            })
    }


    private fun checkIsUserLogin() {
        isUserLogin.value = auth.currentUser != null
    }
}