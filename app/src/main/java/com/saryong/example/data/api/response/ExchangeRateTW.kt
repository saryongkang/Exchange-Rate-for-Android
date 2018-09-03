package com.saryong.example.data.api.response

import org.threeten.bp.Instant

data class ExchangeRateTW(
  val rate: Double,
  val source: String,
  val target: String,
  val time: String // Instant
)