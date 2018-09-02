package com.saryong.example.presentation.currencylist

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableArrayList
import com.saryong.example.presentation.currencylist.item.Currency

class MainViewModel : ViewModel() {
  var currencyList = ObservableArrayList<Currency>()

  fun onAddButtonClick() {
    currencyList.add(Currency("KRW", "Korean Won", 1.0))
  }
}