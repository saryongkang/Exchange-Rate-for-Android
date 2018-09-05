package com.saryong.example

import android.annotation.SuppressLint
import android.content.Context
import android.support.multidex.MultiDex
import com.chibatching.kotpref.Kotpref
import com.jakewharton.threetenabp.AndroidThreeTen
import com.saryong.example.data.local.PredefinedConstantDataStorage
import com.saryong.example.data.model.CurrencyModel
import com.saryong.example.data.pref.Preferences
import com.saryong.example.di.DaggerAppComponent
import com.saryong.example.di.NetworkModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject

@SuppressLint("Registered")
open class App : DaggerApplication() {
  override fun onCreate() {
    super.onCreate()
  
    initRealmDb()
    
    AndroidThreeTen.init(this)
    Kotpref.init(this)
    
    initGlobal()
  }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return DaggerAppComponent.builder()
      .application(this)
      .networkModule(NetworkModule.instance)
      .build()
  }

  override fun attachBaseContext(base: Context) {
    super.attachBaseContext(base)
    MultiDex.install(this)
  }
  
  private fun initRealmDb() {
    Realm.init(this)
    
    val config = RealmConfiguration.Builder()
      .name("example.realm")
      .schemaVersion(0)
      .deleteRealmIfMigrationNeeded()
      .build()
  
    Realm.setDefaultConfiguration(config)
  }
  
  // TODO: move this to Splash screen
  private fun initGlobal() {
//    Preferences.firstLaunch = true
    
    if (Preferences.firstLaunch) {
      Preferences.deleteAll()
      fillDefaultData()

      Preferences.firstLaunch = false
    }
  }
  
  private fun fillDefaultData() {
    val storage = PredefinedConstantDataStorage(this)
    val baseCurrency = Preferences.baseCurrency
    
    val currencyList = mutableListOf<String>()
    val realmDb = Realm.getDefaultInstance()
    
    realmDb.executeTransaction { realm ->
      realm.deleteAll()

      storage.currencies.filter { it.code != baseCurrency }.sortedBy { it.order }.take(10)
        .forEach { currency ->
          realm.createObject<CurrencyModel>(currency.code).run {
            name = currency.name
            order = currency.order
          }
          currencyList.add(currency.code)
        }
    }
    
    Preferences.selectedCurrencies.addAll(currencyList)
  }
  
  protected open fun isInUnitTests() = false
}