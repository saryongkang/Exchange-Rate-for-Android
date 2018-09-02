package com.saryong.example.di

import dagger.Module
import dagger.Provides
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
internal class BuildTypeBasedNetworkModule {
  @Singleton @Provides
  fun provideNetworkLogger(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
  }
}