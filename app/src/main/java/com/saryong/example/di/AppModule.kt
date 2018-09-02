package com.saryong.example.di

import android.content.Context
import com.saryong.example.App
import com.saryong.example.util.rx.AppSchedulerProvider
import com.saryong.example.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object AppModule {
  @Singleton
  @Provides @JvmStatic
  fun provideContext(application: App): Context = application

  @Singleton @Provides @JvmStatic
  fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

}