package com.saryong.example.presentation.currencylist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.preference.Preference
import com.saryong.example.data.local.PredefinedConstantDataStorage
import com.saryong.example.data.model.CurrencyModel
import com.saryong.example.data.pref.Preferences
import com.saryong.example.data.repository.ExchangeRateRepository
import com.saryong.example.presentation.common.BaseViewModel
import com.saryong.example.presentation.currencylist.item.CurrencyItem
import com.saryong.example.util.livedata.Event
import com.saryong.example.util.livedata.MutableListLiveData
import com.saryong.example.util.rx.toLiveData
import io.realm.Realm
import io.realm.kotlin.where
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
  private val predefinedConstantDataStorage: PredefinedConstantDataStorage,
  private val exchangeRateRepository: ExchangeRateRepository
): BaseViewModel() {
  
  val currencyList: LiveData<List<CurrencyItem>>
  
  private val _navigateToDetailActivity = MutableLiveData<Event<Unit>>()
  val navigateToDetailActivity: LiveData<Event<Unit>>
    get() = _navigateToDetailActivity

  init {
    // manual add
//    _currencyList.add(CurrencyItem("KRW", "Korean Won", 1.0))
//    _currencyList.add(CurrencyItem("USD", "US Dollar", 1.0))
//    _currencyList.add(CurrencyItem("EUR", "Euro", 1.0))
  
    val realm = Realm.getDefaultInstance()
    
    val flowable =
      realm.where<CurrencyModel>().findAllAsync().sort("order").asFlowable()
        .filter { it.isLoaded && it.isValid }
        .map { it.toList().map { model -> model.toViewEntity() } }
    
    currencyList = flowable.toLiveData()

//        .subscribe {
//          it.forEach {
//            Timber.d(it.toString())
//            _currencyList.add(it.toViewEntity())
//          }
//        }
  }
  
  fun addCurrency(currencyCode: String) {
    val currencySetting = predefinedConstantDataStorage.currencies.find { it.code == currencyCode }
    if (currencySetting == null) {
      Timber.e("Couldn't find setting for $currencyCode from PredefinedConstantStorage!")
      return
    }
    
    val realmDb = Realm.getDefaultInstance()
    realmDb.executeTransaction { realm ->
      realm.createObject(CurrencyModel::class.java, currencyCode).run {
        name = currencySetting.name
        order = currencySetting.order
      }
      
      Preferences.selectedCurrencies.add(currencyCode)
    }
  }
  
  
  fun onAddButtonClick() {
    // insert
//    _currencyList.add(CurrencyItem("KRW", "Korean Won", 1.0))
    
    // insert into the middle
//    _currencyList.add(1, CurrencyItem("JPY", "Japanese Yen", 1.0))
    
    // change
//    var newItem: CurrencyItem? = null
//    var index: Int = -1
//    _currencyList.value?.indexOfFirst { it.code == "KRW" }?.let {
//      index = it
//    }
//    if (index != -1) {
//      newItem = _currencyList[index].copy(name = "123", exchangedAmount = 123.0)
//    }
//    newItem?.let {
//      _currencyList[index] = newItem
//    }
    
    // change in async way
//    val realmDb = Realm.getDefaultInstance()
//    realmDb.executeTransaction { realm ->
//      val currency = realm.where<CurrencyModel>().equalTo("code", "GBP").findFirst()
//      currency?.let {
//        it.exchangedAmount += 1.0
//      }
//    }
//
//    Timber.d(currencyList.value.toString())
  }
}