package com.saryong.example.data.model

import com.saryong.example.presentation.currencylist.item.CurrencyItem
import com.saryong.example.presentation.currencylist.item.UpdateStatus
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

// TODO move to somewhere
//fun getSystemZoneOffset(): ZoneOffset =
//  ZoneOffset.of(ZoneOffset.systemDefault().toString())

open class CurrencyModel : RealmObject() {
  @PrimaryKey
  var code: String = ""
  
  var name: String = ""
  var order: Int = -1
  var exchangeRate: Double = 1.0
  var updatedAt: Long = 0L // LocalDateTime.now().toEpochSecond(getSystemZoneOffset())
  var status: Int = UpdateStatus.LOADING.rawValue
  
  val updatedDatetimeString: String
    get() = if (updatedAt == 0L) {
      "not updated yet"
    } else {
      "" // LocalDateTime.ofEpochSecond()
    }
  
  fun toViewEntity() =
    CurrencyItem(
      code = code,
      name = name,
      exchangedAmount = exchangeRate.toString(),
      updatedAt = updatedDatetimeString,
      status = UpdateStatus.valueOf(status)
    )
}