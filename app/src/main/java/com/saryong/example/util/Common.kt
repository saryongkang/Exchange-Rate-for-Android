package com.saryong.example.util

fun <T> fastLazy(initializer: () -> T): Lazy<T> =
  lazy(LazyThreadSafetyMode.NONE, initializer)