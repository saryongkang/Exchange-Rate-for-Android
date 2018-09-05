package com.saryong.example.presentation.currencydetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.saryong.example.data.local.CurrencySetting
import com.saryong.example.data.local.PredefinedConstantStorage
import com.saryong.example.data.pref.Preferences
import com.saryong.example.domain.GetCurrencyDetailUseCase
import com.saryong.example.presentation.common.BaseViewModel
import com.saryong.example.presentation.currencylist.item.CurrencyItem
import com.saryong.example.util.livedata.Event
import timber.log.Timber
import javax.inject.Inject

class CurrencyDetailViewModel @Inject constructor(
  private val getCurrencyDetailUseCase: GetCurrencyDetailUseCase,
  private val predefinedConstantStorage: PredefinedConstantStorage
) : BaseViewModel() {
  
  lateinit var targetCurrency: LiveData<CurrencyItem>
  
  private val _initAction = MutableLiveData<Event<CurrencySetting>>()
  val initAction: LiveData<Event<CurrencySetting>>
    get() = _initAction
  
  private val _snackbarMessage = MutableLiveData<String>()
  val snackbarMessage: LiveData<String>
    get() = _snackbarMessage

  val exchangeResult = MutableLiveData<String>()
  val sourceAmountText = MutableLiveData<String>()
  
  private var sourceCurrency: CurrencySetting? = null
  
  fun init(targetCode: String) {
    val sourceCode = Preferences.baseCurrency
    val currencies = predefinedConstantStorage.currencies
    sourceCurrency = currencies.find { it.code == sourceCode }
  
    if (sourceCurrency == null) {
      val errorMessage = "Couldn't find source currency!"
      Timber.e(errorMessage)
      _snackbarMessage.value = errorMessage
      return
    }
    
    _initAction.value = Event(sourceCurrency!!)
    
    targetCurrency = getCurrencyDetailUseCase(targetCode)
  }
  
  fun onDigitChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    s.toString().toDoubleOrNull()?.let { amount ->
      targetCurrency.value?.let {
        val result: Double = amount * it.exchangeRate
        exchangeResult.postValue(result.toString())
      }
    }
  }
}