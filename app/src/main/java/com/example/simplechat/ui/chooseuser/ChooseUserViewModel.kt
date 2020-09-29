package com.example.simplechat.ui.chooseuser

import com.example.cleanarchproject.ui.base.BaseViewModel
import com.example.simplechat.data.model.User
import com.example.simplechat.data.prefs.Prefs
import com.example.simplechat.data.repository.auth.AuthRepository
import com.example.simplechat.utils.SingleLiveEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class ChooseUserViewModel(
    private val authRepository: AuthRepository,
    private val prefs: Prefs
) : BaseViewModel() {
    val isUserLogin = SingleLiveEvent<Boolean>()

    val isSaveUserSuccess = SingleLiveEvent<User>()

    val isRequestFail = SingleLiveEvent<String>()

    init {
        checkIsUserLogin()
    }

    private fun checkIsUserLogin() {
        isUserLogin.value = prefs.isUserLogin
    }

    fun anonymousLogin(user: User) {
        authRepository.anonymousLogin()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                saveUser(User(user.name, "", it.uid))
            }, {
                isRequestFail.value = it.message.toString()
            })
    }

    fun loginOrCreateUser(user: User){
        authRepository.loginOrCreateUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                saveUser(User(it.name, "", it.userId))
            }, {

            })
    }

    private fun saveUser(user: User) {
        authRepository.saveUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                isSaveUserSuccess.value = it
                prefs.isUserLogin=true
                prefs.user =it
            }, {
                isRequestFail.value = it.message.toString()
            })
    }
}