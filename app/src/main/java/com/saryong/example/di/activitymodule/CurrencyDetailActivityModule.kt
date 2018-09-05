package com.saryong.example.di.activitymodule

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import com.saryong.example.di.ViewModelKey
import com.saryong.example.presentation.currencydetail.CurrencyDetailActivity
import com.saryong.example.presentation.currencydetail.CurrencyDetailFragment
import com.saryong.example.presentation.currencydetail.CurrencyDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
interface CurrencyDetailActivityModule {
  @Binds
  fun providesActivity(activity: CurrencyDetailActivity): AppCompatActivity
  
  @ContributesAndroidInjector
  fun contributeCurrencyDetailFragment(): CurrencyDetailFragment
  
  @Binds
  @IntoMap
  @ViewModelKey(CurrencyDetailViewModel::class)
  fun bindCurrencyDetailViewModel(currencyDetailViewModel: CurrencyDetailViewModel): ViewModel
}