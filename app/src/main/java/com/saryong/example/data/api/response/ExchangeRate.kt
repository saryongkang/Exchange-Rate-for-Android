package com.saryong.example.data.api.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

// Response for Currency Layer
@JsonSerializable
data class ExchangeRate(
  val query: ExchangeRateQuery,
  val info: ExchangeRateInfo
)

@JsonSerializable
data class ExchangeRateQuery(
  @Json(name = "to")
  val targetCode: String
)

@JsonSerializable
data class ExchangeRateInfo(
  val timestamp: Long,
  val quote: Double
)

// Response for Alpha Vantage
//@JsonSerializable
//data class ExchangeRate(
//  @Json(name = "Realtime Currency Exchange Rate")
//  val data: ExchangeRateData
//)
//
//@JsonSerializable
//data class ExchangeRateData(
//  @Json(name = "5. Exchange Rate")
//  val rateInString: String,
//
//  @Json(name = "6. Last Refreshed")
//  val updatedAt: String
//) {
//  val rate: Double
//    get() = rateInString.toDoubleOrNull() ?: 0.0
//}