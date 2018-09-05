package com.saryong.example.domain

import android.arch.lifecycle.LiveData
import com.saryong.example.data.repository.CurrencyInfoRepository
import com.saryong.example.presentation.currencylist.item.CurrencyItem
import com.saryong.example.util.rx.toLiveData
import javax.inject.Inject

open class GetCurrencyDetailUseCase @Inject constructor(
  private val currencyInfoRepository: CurrencyInfoRepository
) : UseCase<String, CurrencyItem>() {
  
  override fun invoke(param: String): LiveData<CurrencyItem> {
    return currencyInfoRepository.getCurrencyModel(param)
      .map { it.toViewEntity() }
      .toLiveData()
  }
}