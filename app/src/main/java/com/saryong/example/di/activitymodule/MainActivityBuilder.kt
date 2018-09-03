package com.saryong.example.di.activitymodule

import com.saryong.example.presentation.currencylist.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityBuilder {
  @ContributesAndroidInjector(modules = [MainActivityModule::class])
  fun contributeMainActivity(): MainActivity
}