package com.saryong.example.data.repository

import android.support.annotation.CheckResult
import com.saryong.example.data.api.response.ExchangeRate
import com.saryong.example.data.api.response.ExchangeRateTW
import io.reactivex.Observable
import io.reactivex.Single

interface ExchangeRateRepository {
  @CheckResult fun getAllExchangeRates(): Observable<ExchangeRate>
  
  @CheckResult fun getExchangeRate(source: String, target: String): Single<ExchangeRate>
}