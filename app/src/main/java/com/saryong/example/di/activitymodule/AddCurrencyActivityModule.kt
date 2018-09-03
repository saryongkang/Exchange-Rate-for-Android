package com.saryong.example.di.activitymodule

import android.arch.lifecycle.ViewModel
import android.support.v7.app.AppCompatActivity
import com.saryong.example.di.ViewModelKey
import com.saryong.example.presentation.addcurrency.AddCurrencyActivity
import com.saryong.example.presentation.addcurrency.AddCurrencyViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface AddCurrencyActivityModule {
  @Binds
  fun providesActivity(activity: AddCurrencyActivity): AppCompatActivity

  @Binds
  @IntoMap
  @ViewModelKey(AddCurrencyViewModel::class)
  fun bindAddCurrencyViewModel(addCurrencyViewModel: AddCurrencyViewModel): ViewModel

}