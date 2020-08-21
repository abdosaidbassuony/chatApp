package com.example.cleanarchproject.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
    }

    protected fun Disposable.addToDisposableBag() {
        compositeDisposable.add(this)
    }
}