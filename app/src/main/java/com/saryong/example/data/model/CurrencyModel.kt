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
  var exchangedAmount: Double = 0.0
  var updatedAt: Long = 0 // LocalDateTime.now().toEpochSecond(getSystemZoneOffset())
  var status: Int = UpdateStatus.LOADING.rawValue
  
  fun toViewEntity() =
    CurrencyItem(
      code = code,
      name = name,
      exchangedAmount = exchangedAmount.toString(),
//      updatedAt = LocalDateTime.ofEpochSecond(updatedAt, 0, getSystemZoneOffset()),
      status = UpdateStatus.valueOf(status)
    )
}