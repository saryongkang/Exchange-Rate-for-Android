package com.saryong.example.presentation.addcurrency

import android.arch.lifecycle.LiveData
import com.saryong.example.data.local.CurrencySetting
import com.saryong.example.data.local.PredefinedConstantDataStorage
import com.saryong.example.data.pref.Preferences
import com.saryong.example.presentation.common.BaseViewModel
import com.saryong.example.util.livedata.MutableListLiveData
import javax.inject.Inject

class AddCurrencyViewModel @Inject constructor(
  private val predefinedConstantDataStorage: PredefinedConstantDataStorage
) : BaseViewModel() {

  private var _currencyList = MutableListLiveData<CurrencySetting>()
  var currencyList: LiveData<List<CurrencySetting>> = _currencyList

  init {
    val selectedCurrencies = Preferences.selectedCurrencies
    val baseCurrency = Preferences.baseCurrency

    val availableCurrencies = predefinedConstantDataStorage.currencies
      .filterNot { selectedCurrencies.contains(it.code) || it.code == baseCurrency }
      .sortedBy { it.order }

    _currencyList.addAll(availableCurrencies)
  }
}