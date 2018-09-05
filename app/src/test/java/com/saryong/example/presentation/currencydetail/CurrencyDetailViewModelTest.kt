package com.saryong.example.presentation.currencydetail

import android.arch.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.mock
import com.saryong.example.BuildConfig
import com.saryong.example.TestApp
import com.saryong.example.data.local.CurrencySetting
import com.saryong.example.data.local.PredefinedConstantStorage
import com.saryong.example.data.pref.Preferences
import com.saryong.example.domain.*
import com.saryong.example.presentation.currencylist.CurrencyListViewModel
import com.saryong.example.presentation.currencylist.item.CurrencyItem
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = TestApp::class)
open class CurrencyDetailViewModelTest {
  
  lateinit var getCurrencyDetailUseCase: GetCurrencyDetailUseCase
  lateinit var predefinedConstantStorage: PredefinedConstantStorage
  lateinit var viewModel: CurrencyDetailViewModel
  
  @Before
  fun setUp() {
    getCurrencyDetailUseCase = mock {
      on { invoke("USD") }.thenReturn(MutableLiveData<CurrencyItem>().apply {
        value = CurrencyItem("USD", "United States Dollar", "$", exchangeRate = 10.0)
      })
    }
    predefinedConstantStorage = mock {
      on { currencies }.thenReturn(dummyCurrencySettings())
    }
  }
  
  @Test
  fun testInitialization() {
    Preferences.baseCurrency = "EUR"
    viewModel = CurrencyDetailViewModel(
      getCurrencyDetailUseCase,
      predefinedConstantStorage
    )
    viewModel.init("USD")
    assertEquals("USD", viewModel.targetCurrency.value!!.code)
  }
  
  @Test
  fun testCalculation() {
    Preferences.baseCurrency = "EUR"
    viewModel = CurrencyDetailViewModel(
      getCurrencyDetailUseCase,
      predefinedConstantStorage
    )
    viewModel.init("USD")
    viewModel.onDigitChanged("1234", 0, 0, 0)
    
    assertEquals("$12340.0", viewModel.exchangeResult.value)
  }
  
  private fun dummyCurrencySettings() =
    listOf(
      CurrencySetting("KRW", "Korean Won", "₩", 14),
      CurrencySetting("EUR", "Euro", "€", 1),
      CurrencySetting("USD", "United States Dollar", "$", 0)
    )
}