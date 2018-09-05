package com.saryong.example.domain

import com.saryong.example.data.local.PredefinedConstantStorage
import com.saryong.example.data.pref.Preferences
import com.saryong.example.data.repository.CurrencyInfoRepository
import com.saryong.example.data.repository.ExchangeRateRepository
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber
import javax.inject.Inject

open class AddCurrencyUseCase @Inject constructor(
  private val predefinedConstantStorage: PredefinedConstantStorage,
  private val currencyInfoRepository: CurrencyInfoRepository,
  private val exchangeRateRepository: ExchangeRateRepository
) : UseCase<String, Disposable>() {
  
  override fun executeDirectly(param: String): Disposable {
    val currencySetting = predefinedConstantStorage.currencies.find { it.code == param }
    if (currencySetting == null) {
      Timber.e("Couldn't find setting for $param from PredefinedConstantStorage!")
      return Disposables.empty()
    }
  
    currencyInfoRepository.addCurrency(currencySetting)
    
    return exchangeRateRepository.getExchangeRate(Preferences.baseCurrency, currencySetting.code)
      .subscribeBy {
        currencyInfoRepository.updateCurrencyModel(it)
      }
  }
}