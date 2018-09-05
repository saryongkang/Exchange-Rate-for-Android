package com.saryong.example.data.repository

import com.saryong.example.data.api.response.ExchangeRate
import com.saryong.example.data.local.CurrencySetting
import com.saryong.example.data.model.CurrencyModel
import com.saryong.example.data.pref.Preferences
import com.saryong.example.presentation.currencylist.item.UpdateStatus
import com.saryong.example.util.rx.SchedulerProvider
import io.reactivex.Flowable
import io.realm.Realm
import io.realm.kotlin.where
import timber.log.Timber
import javax.inject.Inject

class CurrencyInfoDataRepository @Inject constructor(
  private val schedulerProvider: SchedulerProvider
) : CurrencyInfoRepository {
  override fun getAllCurrencyModels(): Flowable<List<CurrencyModel>> {
    val realm = Realm.getDefaultInstance()
  
    return realm.where<CurrencyModel>().findAllAsync()
      .sort("order").asFlowable()
      .filter { it.isLoaded && it.isValid }
      .map {
        it.toList()
      }
  }
  
  override fun getCurrencyModel(targetCode: String): Flowable<CurrencyModel> {
    val realm = Realm.getDefaultInstance()
    return realm.where<CurrencyModel>().equalTo("code", targetCode).findFirstAsync()
      .asFlowable<CurrencyModel>()
      .filter { it.isLoaded }
    
  }
  
  override fun updateCurrencyModel(rate: ExchangeRate) {
    val realmDb = Realm.getDefaultInstance()
    
    realmDb.executeTransaction { realm ->
      val currency = realm.where<CurrencyModel>().equalTo("code", rate.query.targetCode).findFirst()
      currency?.run {
        exchangeRate = rate.info.quote
        updatedAt = rate.info.timestamp
        status = UpdateStatus.SUCCESS.rawValue
      }
    }
  }
  
  override fun addCurrency(currencySetting: CurrencySetting) {
    val realmDb = Realm.getDefaultInstance()
    realmDb.executeTransaction { realm ->
      realm.createObject(CurrencyModel::class.java, currencySetting.code).run {
        name = currencySetting.name
        order = currencySetting.order
        status = UpdateStatus.LOADING.rawValue
      }
      
      Preferences.selectedCurrencies.add(currencySetting.code)
    }
  }
}