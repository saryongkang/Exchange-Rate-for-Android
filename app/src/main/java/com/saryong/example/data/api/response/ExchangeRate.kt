package com.saryong.example.data.api.response

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class ExchangeRate(
  @Json(name = "1. From_Currency Code")
  val exchangeRate: String,

  @Json(name = "6. Last Refreshed")
  val updatedAt: String
)