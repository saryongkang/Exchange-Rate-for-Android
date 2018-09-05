package com.saryong.example.domain

import android.arch.lifecycle.LiveData
import com.saryong.example.data.repository.CurrencyInfoRepository
import com.saryong.example.presentation.currencylist.item.CurrencyItem
import com.saryong.example.util.rx.toLiveData
import javax.inject.Inject

open class LoadAllCurrenciesUseCase @Inject constructor(
  private val currencyInfoRepository: CurrencyInfoRepository
): UseCase<Unit, List<CurrencyItem>>() {
  
  override operator fun invoke(param: Unit): LiveData<List<CurrencyItem>> =
    currencyInfoRepository.getAllCurrencyModels()
      .map {
        it.map { model ->
          model.toViewEntity()
        }
      }.toLiveData()
  
}