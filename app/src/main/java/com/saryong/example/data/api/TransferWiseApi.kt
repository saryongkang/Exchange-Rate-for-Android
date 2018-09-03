package com.saryong.example.data.api

import android.support.annotation.CheckResult
import com.saryong.example.data.api.response.ExchangeRateTW
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface TransferWiseApi {
  
  @GET("/v1/rates")
  @CheckResult
  fun getExchangeRates(@Path("source") source: String): Single<List<ExchangeRateTW>>
}