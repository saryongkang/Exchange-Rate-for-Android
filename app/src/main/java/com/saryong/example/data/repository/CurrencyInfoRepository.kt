package com.saryong.example.data.repository

import com.saryong.example.data.api.response.ExchangeRateTW

interface CurrencyInfoRepository {
  fun update(code: String, rateModel: ExchangeRateTW)
}