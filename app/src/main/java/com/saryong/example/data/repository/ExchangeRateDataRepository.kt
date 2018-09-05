package com.saryong.example.data.repository

import com.saryong.example.data.api.CurrencyLayerApi
import com.saryong.example.data.api.response.ExchangeRate
import com.saryong.example.data.api.response.ExchangeRateTW
import com.saryong.example.data.pref.Preferences
import com.saryong.example.util.rx.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

// Couldn't use because of absent of API key
//class ExchangeRateTransferWiseRepository @Inject constructor(
//  private val api: TransferWiseApi,
//  private val schedulerProvider: SchedulerProvider
//) : ExchangeRateRepository {
//
//  override fun getExchangeRates(source: String): Observable<ExchangeRateTW> {
//    val selectedCurrencies: Set<String> = Preferences.selectedCurrencies
//
//    return api.getExchangeRates(source)
//      .subscribeOn(schedulerProvider.io())
//      .observeOn(schedulerProvider.io())
//      .flatMapObservable {
//        Observable.fromIterable(it)
//      }
//      .filter { selectedCurrencies.contains(it.target) }
//      .doOnNext { Timber.d(it.toString()) }
//  }
//}

class ExchangeRateDataRepository @Inject constructor(
  private val api: CurrencyLayerApi,
  private val schedulerProvider: SchedulerProvider
) : ExchangeRateRepository {
  
  override fun getExchangeRates(): Observable<ExchangeRate> {
    val source = Preferences.baseCurrency
    val callList = Preferences.selectedCurrencies.map { targetCode ->
      api.getExchangeRate(source, targetCode)
        .subscribeOn(schedulerProvider.io())
    }
    
    return Single.merge(callList).toObservable().observeOn(schedulerProvider.io())
  }
}