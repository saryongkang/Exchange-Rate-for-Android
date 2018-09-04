package com.saryong.example.presentation.addcurrency

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.saryong.example.data.local.CurrencySetting
import com.saryong.example.data.local.PredefinedConstantDataStorage
import com.saryong.example.data.pref.Preferences
import com.saryong.example.presentation.common.BaseViewModel
import com.saryong.example.util.livedata.MutableListLiveData
import timber.log.Timber
import javax.inject.Inject
import com.saryong.example.util.livedata.Event


class AddCurrencyViewModel @Inject constructor(
  private val predefinedConstantDataStorage: PredefinedConstantDataStorage
) : BaseViewModel() {

  private val _currencyList = MutableListLiveData<CurrencySetting>()
  val currencyList: LiveData<List<CurrencySetting>>
    get() = _currencyList
  
  private val _finishWithCurrency = MutableLiveData<Event<String>>()
  val finishWithCurrency: LiveData<Event<String>>
    get() = _finishWithCurrency
  
  
  init {
    val selectedCurrencies = Preferences.selectedCurrencies
    val baseCurrency = Preferences.baseCurrency

    val availableCurrencies = predefinedConstantDataStorage.currencies
      .filterNot { selectedCurrencies.contains(it.code) || it.code == baseCurrency }
      .sortedBy { it.order }

    _currencyList.addAll(availableCurrencies)
  }
  
  fun onClickItem(currency: CurrencySetting) {
    _finishWithCurrency.value = Event(currency.code)
  }
}