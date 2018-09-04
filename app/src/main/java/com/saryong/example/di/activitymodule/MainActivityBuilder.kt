package com.saryong.example.di.activitymodule

import com.saryong.example.presentation.currencylist.CurrencyListActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface CurrencyListActivityBuilder {
  @ContributesAndroidInjector(modules = [CurrencyListActivityModule::class])
  fun contributeCurrencyListActivity(): CurrencyListActivity
}