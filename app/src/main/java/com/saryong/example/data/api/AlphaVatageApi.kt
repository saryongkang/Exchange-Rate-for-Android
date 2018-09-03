package com.saryong.example.data.api

import android.support.annotation.CheckResult
import com.saryong.example.data.api.response.ExchangeRate
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface AlphaVantageApi {
  
  @GET("?function=CURRENCY_EXCHANGE_RATE&apikey=5ULZMDUAH955E1M6")
  @CheckResult
  fun getExchangeRate(@Path("from_currency") source: String, @Path("to_currency") target: String): Single<ExchangeRate>
}