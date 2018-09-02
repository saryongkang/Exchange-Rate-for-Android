package com.saryong.example

import android.annotation.SuppressLint
import com.squareup.leakcanary.LeakCanary
import timber.log.Timber

@SuppressLint("Registered")
class DebugApp : App() {
  override fun onCreate() {
    super.onCreate()
    setupTimber()
    setupLeakCanary()
  }

  private fun setupTimber() {
    Timber.plant(Timber.DebugTree())
  }

  private fun setupLeakCanary() {
    if (LeakCanary.isInAnalyzerProcess(this)) {
      return
    }
    LeakCanary.install(this)
  }
}