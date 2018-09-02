package com.saryong.example.presentation.currencylist

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import com.saryong.example.presentation.currencylist.item.Currency
import timber.log.Timber

class MainViewModel : ViewModel() {

  var currencyList = ObservableArrayList<Currency>()


  fun onAddButtonClick() {
    Timber.d("HEY!")
    currencyList.add(Currency("KRW", "Korean Won", "Korea", 1.0))
  }
}