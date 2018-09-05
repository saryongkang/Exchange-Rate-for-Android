package com.saryong.example.presentation.currencydetail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.databinding.ObservableField
import android.support.design.widget.Snackbar
import com.saryong.example.data.local.CurrencySetting
import com.saryong.example.data.local.PredefinedConstantStorage
import com.saryong.example.data.model.CurrencyModel
import com.saryong.example.data.pref.Preferences
import com.saryong.example.presentation.EXTRA_KEY_CURRENCY_CODE
import com.saryong.example.presentation.common.BaseViewModel
import com.saryong.example.util.livedata.Event
import com.saryong.example.util.rx.toLiveData
import io.reactivex.rxkotlin.plusAssign
import io.realm.Realm
import io.realm.kotlin.where
import timber.log.Timber
import javax.inject.Inject

class CurrencyDetailViewModel @Inject constructor(
  private val predefinedConstantStorage: PredefinedConstantStorage
) : BaseViewModel() {
  
  lateinit var targetCurrency: LiveData<CurrencyModel>
  
  private val _initAction = MutableLiveData<Event<CurrencySetting>>()
  val initAction: LiveData<Event<CurrencySetting>>
    get() = _initAction
  
  val exchangeResult = MutableLiveData<String>()
  val sourceAmountText = MutableLiveData<String>()
  
  private var sourceCurrency: CurrencySetting? = null
  
//  val sourceAmount = MutableLiveData<String>()
  
  fun init(targetCode: String) {
    val sourceCode = Preferences.baseCurrency
    val currencies = predefinedConstantStorage.currencies
    sourceCurrency = currencies.find { it.code == sourceCode }
  
    if (sourceCurrency == null) {
        val errorMessage = "Couldn't find source currency!"
        Timber.e(errorMessage)
      // TODO
//        Snackbar.make(currentFocus, errorMessage, Snackbar.LENGTH_LONG).show()
        return
    }
    
    _initAction.value = Event(sourceCurrency!!)
    
    // TODO move
    val realm = Realm.getDefaultInstance()
    val flowable = realm.where<CurrencyModel>().equalTo("code", targetCode).findFirstAsync()
      .asFlowable<CurrencyModel>()
      .filter { it.isLoaded }
    
    targetCurrency = flowable.toLiveData()
    
    disposables += flowable.subscribe {
      Timber.d("Good: " + it.exchangedAmount)
    }
      
  }
  
  fun onDigitChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    Timber.d("onTextChanged $s")
    
    s.toString().toDoubleOrNull()?.let { amount ->
      targetCurrency.value?.let {
        val result: Double = amount * it.exchangedAmount
        exchangeResult.postValue(result.toString())
        
  
        Timber.d("result: $result")
      }
    }
  }
}