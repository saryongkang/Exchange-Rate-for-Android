package com.saryong.example.data.local

import com.saryong.example.presentation.currencylist.item.CurrencyItem
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class CurrencySetting(
  val code: String,
  val name: String,
  val symbol: String,
  val order: Int
) {
  fun toCurrencyItem() = CurrencyItem(code, name)
}