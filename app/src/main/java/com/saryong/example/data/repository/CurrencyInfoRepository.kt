package com.saryong.example.data.repository

import com.saryong.example.data.api.response.ExchangeRate
import com.saryong.example.data.api.response.ExchangeRateTW
import com.saryong.example.data.local.CurrencySetting
import com.saryong.example.data.model.CurrencyModel
import io.reactivex.Flowable

interface CurrencyInfoRepository {
  fun getAllCurrencyModels(): Flowable<List<CurrencyModel>>
  
  fun getCurrencyModel(targetCode: String): Flowable<CurrencyModel>
  
  fun updateCurrencyModel(rate: ExchangeRate)
  
  fun addCurrency(currencySetting: CurrencySetting)
}