package com.saryong.example.presentation.currencylist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import com.saryong.example.data.local.PredefinedConstantStorage
import com.saryong.example.data.model.CurrencyModel
import com.saryong.example.data.pref.Preferences
import com.saryong.example.data.repository.ExchangeRateRepository
import com.saryong.example.presentation.common.BaseViewModel
import com.saryong.example.presentation.currencylist.item.CurrencyItem
import com.saryong.example.util.livedata.Event
import com.saryong.example.util.rx.SchedulerProvider
import com.saryong.example.util.rx.toLiveData
import io.reactivex.rxkotlin.plusAssign
import io.realm.Realm
import io.realm.kotlin.where
import timber.log.Timber
import javax.inject.Inject

class CurrencyListViewModel @Inject constructor(
  private val predefinedConstantStorage: PredefinedConstantStorage,
  private val exchangeRateRepository: ExchangeRateRepository,
  private val schedulerProvider: SchedulerProvider
): BaseViewModel(), CurrencyListEventListener {
  
//  private val _currencyList = MutableListLiveData<CurrencyItem>()
  val currencyList: LiveData<List<CurrencyItem>>
//    get() = _currencyList
  
  val selectableCodes = ObservableArrayList<String>()
  
  private val _navigateToDetailAction = MutableLiveData<Event<String>>()
  val navigateToDetailAction: LiveData<Event<String>>
    get() = _navigateToDetailAction

  init {
    // manual add
//    _currencyList.add(CurrencyItem("KRW", "Korean Won", 1.0))
//    _currencyList.add(CurrencyItem("USD", "US Dollar", 1.0))
//    _currencyList.add(CurrencyItem("EUR", "Euro", 1.0))
  
    val currentCurrency = Preferences.baseCurrency
  
    val codeList = predefinedConstantStorage.currencies
      .filter { it.code != currentCurrency }
      .map { it.code }
      .toMutableList()
  
    selectableCodes.add(currentCurrency)
    selectableCodes.addAll(codeList)
  
    val realm = Realm.getDefaultInstance()
    
    val flowable =
      realm.where<CurrencyModel>().findAllAsync().sort("order").asFlowable()
        .filter { it.isLoaded && it.isValid }
        .map {
          it.toList().map { model ->
            Timber.d("!!! + ${model.code}")
            model.toViewEntity()
          }
        }
    
    currencyList = flowable.toLiveData()

    update()
    
//    disposables += flowable
//        .subscribe {
//          it.forEach {
//            Timber.d(it.toString())
//            _currencyList.add(it)
//          }
//        }
  }
  
  private fun update() {
  
    disposables += exchangeRateRepository.getExchangeRates()
      .observeOn(schedulerProvider.io())
      .subscribe { rate ->
        val realmDb = Realm.getDefaultInstance()
        Timber.d("RESULT! $rate")
  
        realmDb.executeTransaction { realm ->
          val currency = realm.where<CurrencyModel>().equalTo("code", rate.query.targetCode).findFirst()
          currency?.let {
            it.exchangeRate = rate.info.quote
            it.updatedAt = rate.info.timestamp
          }
        }
      }
  }
  
  fun addCurrency(currencyCode: String) {
    val currencySetting = predefinedConstantStorage.currencies.find { it.code == currencyCode }
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
//      newItem = _currencyList[index].copy(name = "123", exchangeRate = 123.0)
//    }
//    newItem?.let {
//      _currencyList[index] = newItem
//    }
    
    // change in async way
//    val realmDb = Realm.getDefaultInstance()
//    realmDb.executeTransaction { realm ->
//      val currency = realm.where<CurrencyModel>().equalTo("code", "GBP").findFirst()
//      currency?.let {
//        it.exchangeRate += 1.0
//      }
//    }
//
//    Timber.d(currencyList.value.toString())
  }
  
  override fun onSelect(currency: CurrencyItem) {
    _navigateToDetailAction.value = Event(currency.code)
  }
  
  fun onChangeBaseCurrency(position: Int) {
    Timber.d("ITEM!!! $position")
  }
}

interface CurrencyListEventListener {
  fun onSelect(currency: CurrencyItem)
}