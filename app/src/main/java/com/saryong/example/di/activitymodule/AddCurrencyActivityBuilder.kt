package com.saryong.example.di.activitymodule

import com.saryong.example.presentation.addcurrency.AddCurrencyActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface AddCurrencyActivityBuilder {
  @ContributesAndroidInjector(modules = [AddCurrencyActivityModule::class])
  fun contributeActivity(): AddCurrencyActivity
}