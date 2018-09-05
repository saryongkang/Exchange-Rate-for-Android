package com.saryong.example.presentation.currencylist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import com.saryong.example.data.local.PredefinedConstantStorage
import com.saryong.example.data.pref.Preferences
import com.saryong.example.domain.AddCurrencyUseCase
import com.saryong.example.domain.LoadAllCurrenciesUseCase
import com.saryong.example.domain.UpdateAllCurrenciesUseCase
import com.saryong.example.presentation.common.BaseViewModel
import com.saryong.example.presentation.currencylist.item.CurrencyItem
import com.saryong.example.util.livedata.Event
import io.reactivex.rxkotlin.plusAssign
import timber.log.Timber
import javax.inject.Inject

class CurrencyListViewModel @Inject constructor(
  private val loadAllCurrenciesUseCase: LoadAllCurrenciesUseCase,
  private val updateAllCurrenciesUseCase: UpdateAllCurrenciesUseCase,
  private val addCurrencyUseCase: AddCurrencyUseCase,
  private val predefinedConstantStorage: PredefinedConstantStorage
): BaseViewModel(), CurrencyListEventListener {
  
  val currencyList: LiveData<List<CurrencyItem>>
  
  val selectableCodes = ObservableArrayList<String>()
  
  private val _navigateToDetailAction = MutableLiveData<Event<String>>()
  val navigateToDetailAction: LiveData<Event<String>>
    get() = _navigateToDetailAction
  
  private val _snackbarMessage = MutableLiveData<String>()
  val snackbarMessage: LiveData<String>
    get() = _snackbarMessage

  init {
    val currentCurrency = Preferences.baseCurrency
  
    val codeList = predefinedConstantStorage.currencies
      .filter { it.code != currentCurrency }
      .map { it.code }
      .toMutableList()
  
    selectableCodes.add(currentCurrency)
    selectableCodes.addAll(codeList)
  
    currencyList = loadAllCurrenciesUseCase(Unit)
  
    disposables += updateAllCurrenciesUseCase.executeDirectly(Unit)
  }
  
  fun addCurrency(currencyCode: String) {
    disposables += addCurrencyUseCase.executeDirectly(currencyCode)
  }
  
  override fun onSelect(currency: CurrencyItem) {
    _navigateToDetailAction.value = Event(currency.code)
  }
  
  fun onChangeBaseCurrency(position: Int) {
    if (position == 0) {
      return
    }
    _snackbarMessage.value = "Not supported yet..."
  }
}

interface CurrencyListEventListener {
  fun onSelect(currency: CurrencyItem)
}