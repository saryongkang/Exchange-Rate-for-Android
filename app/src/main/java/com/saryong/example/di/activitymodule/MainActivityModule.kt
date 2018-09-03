package com.saryong.example.di.activitymodule

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import com.saryong.example.di.ViewModelKey
import com.saryong.example.presentation.currencylist.MainActivity
import com.saryong.example.presentation.currencylist.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainActivityModule {
  @Binds
  fun providesActivity(activity: MainActivity): AppCompatActivity
  
  @Binds
  @IntoMap
  @ViewModelKey(MainViewModel::class)
  fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
}