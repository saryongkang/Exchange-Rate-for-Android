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
  val imagePath: String
    get() = code.toLowerCase() + ".png"
  
  fun toCurrencyItem() = CurrencyItem(code, name, symbol)
}