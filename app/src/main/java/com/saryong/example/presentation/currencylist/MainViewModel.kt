package com.saryong.example.presentation.currencylist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.saryong.example.presentation.currencylist.item.Currency
import com.saryong.example.util.livedata.MutableListLiveData
import timber.log.Timber

class MainViewModel : ViewModel() {
  
  private var _currencyList = MutableListLiveData<Currency>()
  var currencyList: LiveData<List<Currency>> = _currencyList

  init {
    _currencyList.add(Currency("KRW", "Korean Won", 1.0))
    _currencyList.add(Currency("USD", "US Dollar", 1.0))
    _currencyList.add(Currency("EUR", "Euro", 1.0))
  }
  
  fun onAddButtonClick() {
    // insert
//    _currencyList.add(Currency("KRW", "Korean Won", 1.0))
    
    // insert into the middle
//    _currencyList.add(1, Currency("JPY", "Japanese Yen", 1.0))
    
    // change
//    var newItem: Currency? = null
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
    
    Timber.d(currencyList.value.toString())
  }
}