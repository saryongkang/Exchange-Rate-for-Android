package com.saryong.example.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides

@Module(
  includes = [BuildTypeBasedNetworkModule::class]
)
open class NetworkModule {
  companion object {
    val instance = NetworkModule()
  }

}