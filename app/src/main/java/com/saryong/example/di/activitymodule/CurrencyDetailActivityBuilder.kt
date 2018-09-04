package com.saryong.example.di.activitymodule

import com.saryong.example.presentation.currencydetail.CurrencyDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface CurrencyDetailActivityBuilder {
  @ContributesAndroidInjector(modules = [CurrencyDetailActivityModule::class])
  fun contributeCurrencyDetailActivity(): CurrencyDetailActivity
}