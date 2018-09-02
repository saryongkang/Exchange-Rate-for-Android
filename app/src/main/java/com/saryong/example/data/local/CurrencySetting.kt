package com.saryong.example.data.local

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class CurrencySetting(
  val code: String,
  val name: String,
  val symbol: String,
  val order: Int
)