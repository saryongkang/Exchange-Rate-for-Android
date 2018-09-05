package com.saryong.example.domain

import android.arch.lifecycle.LiveData

abstract class UseCase<in P, R> {
  open operator fun invoke(param: P): LiveData<R> {
    throw NotImplementedError()
  }
  
  open fun executeDirectly(param: P): R {
    throw NotImplementedError()
  }
}

