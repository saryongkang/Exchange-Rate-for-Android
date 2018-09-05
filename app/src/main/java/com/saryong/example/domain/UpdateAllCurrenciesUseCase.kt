package com.saryong.example.domain

import com.saryong.example.data.repository.CurrencyInfoRepository
import com.saryong.example.data.repository.ExchangeRateRepository
import com.saryong.example.util.rx.SchedulerProvider
import io.reactivex.disposables.Disposable
import javax.inject.Inject

open class UpdateAllCurrenciesUseCase @Inject constructor(
  private val currencyInfoRepository: CurrencyInfoRepository,
  private val exchangeRateRepository: ExchangeRateRepository,
  private val schedulerProvider: SchedulerProvider
): UseCase<Unit, Disposable>() {
  
  override fun executeDirectly(param: Unit): Disposable {
    return exchangeRateRepository.getAllExchangeRates()
      .observeOn(schedulerProvider.io())
      .subscribe { rate ->
        currencyInfoRepository.updateCurrencyModel(rate)
      }
  }
}