package com.saryong.example

import android.annotation.SuppressLint
import android.content.Context
import android.support.multidex.MultiDex
import com.chibatching.kotpref.Kotpref
import com.jakewharton.threetenabp.AndroidThreeTen
import com.saryong.example.di.DaggerAppComponent
import com.saryong.example.di.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

@SuppressLint("Registered")
open class App : DaggerApplication() {
  override fun onCreate() {
    super.onCreate()

    AndroidThreeTen.init(this)
    Kotpref.init(this)
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder()
      .application(this)
      .networkModule(NetworkModule.instance)
      .build()
  }

  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }
}