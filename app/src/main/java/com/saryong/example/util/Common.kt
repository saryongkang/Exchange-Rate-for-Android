package com.saryong.example.util

import timber.log.Timber

fun <T> fastLazy(initializer: () -> T): Lazy<T> =
  lazy(LazyThreadSafetyMode.NONE, initializer)

fun defaultErrorHandler(): (Throwable) -> Unit = { e -> Timber.e(e) }