package com.saryong.example.di.activitymodule

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import com.saryong.example.di.ViewModelKey
import com.saryong.example.presentation.splash.SplashActivity
import com.saryong.example.presentation.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SplashActivityModule {
  @Binds
  fun providesActivity(activity: SplashActivity): AppCompatActivity

  @Binds
  @IntoMap
  @ViewModelKey(SplashViewModel::class)
  fun bindSplashViewModel(splashViewModel: SplashViewModel): ViewModel
}