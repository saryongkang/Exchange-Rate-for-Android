package com.saryong.example.data.model

import com.saryong.example.presentation.currencylist.item.CurrencyItem
import com.saryong.example.presentation.currencylist.item.UpdateStatus
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import org.threeten.bp.Instant

open class CurrencyModel : RealmObject() {
  @PrimaryKey
  var code: String = ""
  var name: String = ""
  var symbol: String = ""
  var order: Int = -1
  var exchangeRate: Double = 1.0
  var updatedAt: Long = 0L
  var status: Int = UpdateStatus.LOADING.rawValue
  
  val updatedDatetimeString: String
    get() = if (updatedAt == 0L) {
      "not updated yet"
    } else {
      Instant.ofEpochSecond(updatedAt).toString()
    }
  
  fun toViewEntity() =
    CurrencyItem(
      code = code,
      name = name,
      symbol = symbol,
      exchangeRate = exchangeRate,
      updatedAt = updatedDatetimeString,
      status = UpdateStatus.valueOf(status)
    )
}