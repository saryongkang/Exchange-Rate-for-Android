package com.saryong.example.data.api

import android.support.annotation.CheckResult
import com.saryong.example.data.api.response.ExchangeRate
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface CurrencyLayerApi {
  
  @GET("/api/convert?access_key=8619a2a39333ca02d3b852e64fa8af9f")
  @CheckResult
  fun getExchangeRate(@Query("from") source: String, @Query("to") target: String, @Query("amount") amount: Int = 1): Single<ExchangeRate>
}