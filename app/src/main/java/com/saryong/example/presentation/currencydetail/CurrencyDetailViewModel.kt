package com.saryong.example.presentation.currencydetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.support.design.widget.Snackbar
import com.saryong.example.data.local.CurrencySetting
import com.saryong.example.data.local.PredefinedConstantStorage
import com.saryong.example.data.pref.Preferences
import com.saryong.example.presentation.EXTRA_KEY_CURRENCY_CODE
import com.saryong.example.presentation.common.BaseViewModel
import com.saryong.example.util.livedata.Event
import timber.log.Timber
import javax.inject.Inject

class CurrencyDetailViewModel @Inject constructor(
  private val predefinedConstantStorage: PredefinedConstantStorage
) : BaseViewModel() {
  
  private var sourceCurrency: CurrencySetting? = null
  private var targetCurrency: CurrencySetting? = null
  
  private val _initAction = MutableLiveData<Event<Pair<CurrencySetting, CurrencySetting>>>()
  val initAction: LiveData<Event<Pair<CurrencySetting, CurrencySetting>>>
    get() = _initAction
  
  fun init(targetCode: String) {
    val sourceCode = Preferences.baseCurrency
    val currencies = predefinedConstantStorage.currencies
    sourceCurrency = currencies.find { it.code == sourceCode }
    targetCurrency = currencies.find { it.code == targetCode }

    if (sourceCurrency == null || targetCurrency == null) {
        val errorMessage = "Couldn't find source currency or target!"
        Timber.e(errorMessage)
      // TODO
//        Snackbar.make(currentFocus, errorMessage, Snackbar.LENGTH_LONG).show()
        return
    }
    
    _initAction.value = Event(sourceCurrency!! to targetCurrency!!)
  }
}