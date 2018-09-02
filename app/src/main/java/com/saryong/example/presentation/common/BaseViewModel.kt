package com.saryong.example.presentation.common

import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel(), LifecycleObserver {
  protected val disposables = CompositeDisposable()

  override fun onCleared() {
    disposables.clear()
  }
}