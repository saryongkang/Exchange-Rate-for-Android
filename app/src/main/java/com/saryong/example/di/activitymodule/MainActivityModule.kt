package com.saryong.example.di.activitymodule

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import com.saryong.example.di.ViewModelKey
import com.saryong.example.presentation.currencylist.CurrencyListActivity
import com.saryong.example.presentation.currencylist.CurrencyListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface CurrencyListActivityModule {
  @Binds
  fun providesActivity(activity: CurrencyListActivity): AppCompatActivity
  
  @Binds
  @IntoMap
  @ViewModelKey(CurrencyListViewModel::class)
  fun bindCurrencyListViewModel(currencyListViewModel: CurrencyListViewModel): ViewModel
}