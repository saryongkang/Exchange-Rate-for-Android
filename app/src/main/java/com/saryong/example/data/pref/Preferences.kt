package com.saryong.example.data.pref

import com.chibatching.kotpref.KotprefModel

object Preferences : KotprefModel() {
  override val kotprefName: String = "example_prefs"

  var firstLaunch by booleanPref(true)
  var baseCurrency by stringPref("EUR")
  val selectedCurrencies by stringSetPref(emptySet())
  
  fun deleteAll() {
    clear()
  }
}