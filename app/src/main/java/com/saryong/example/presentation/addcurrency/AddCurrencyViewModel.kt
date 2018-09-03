package com.saryong.example.presentation.addcurrency

import android.arch.lifecycle.LiveData
import com.saryong.example.data.local.PredefinedConstantDataStorage
import com.saryong.example.data.pref.Preferences
import com.saryong.example.presentation.common.BaseViewModel
import com.saryong.example.presentation.currencylist.item.CurrencyItem
import com.saryong.example.util.livedata.MutableListLiveData
import javax.inject.Inject

class AddCurrencyViewModel @Inject constructor(
  private val predefinedConstantDataStorage: PredefinedConstantDataStorage
) : BaseViewModel() {

  private var _currencyList = MutableListLiveData<CurrencyItem>()
  var currencyList: LiveData<List<CurrencyItem>> = _currencyList

  init {
    val selectedCurrencies = Preferences.selectedCurrencies

    val availableCurrencies = predefinedConstantDataStorage.currencies
      .filterNot { selectedCurrencies.contains(it.code) }
      .sortedBy { it.order }
      .map { it.toCurrencyItem() }

    _currencyList.addAll(availableCurrencies)
  }
}