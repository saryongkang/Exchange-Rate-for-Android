package com.saryong.example.presentation.addcurrency

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.saryong.example.data.local.CurrencySetting
import com.saryong.example.data.local.PredefinedConstantStorage
import com.saryong.example.data.pref.Preferences
import com.saryong.example.presentation.common.BaseViewModel
import com.saryong.example.util.livedata.MutableListLiveData
import javax.inject.Inject
import com.saryong.example.util.livedata.Event


class AddCurrencyViewModel @Inject constructor(
  private val predefinedConstantStorage: PredefinedConstantStorage
) : BaseViewModel(), CurrencySettingEventListener {

  private val _currencyList = MutableListLiveData<CurrencySetting>()
  val currencyList: LiveData<List<CurrencySetting>>
    get() = _currencyList
  
  private val _finishWithCurrencyAction = MutableLiveData<Event<String>>()
  val finishWithCurrencyAction: LiveData<Event<String>>
    get() = _finishWithCurrencyAction
  
  
  init {
    val selectedCurrencies = Preferences.selectedCurrencies
    val baseCurrency = Preferences.baseCurrency

    val availableCurrencies = predefinedConstantStorage.currencies
      .filterNot { selectedCurrencies.contains(it.code) || it.code == baseCurrency }
      .sortedBy { it.order }

    _currencyList.addAll(availableCurrencies)
  }
  
  override fun onSelect(currency: CurrencySetting) {
    _finishWithCurrencyAction.value = Event(currency.code)
  }
}

interface CurrencySettingEventListener {
  fun onSelect(currency: CurrencySetting)
}