package com.saryong.example.presentation.currencylist

import com.nhaarman.mockitokotlin2.mock
import com.saryong.example.BuildConfig
import com.saryong.example.TestApp
import com.saryong.example.data.local.CurrencySetting
import com.saryong.example.data.local.PredefinedConstantStorage
import com.saryong.example.data.pref.Preferences
import com.saryong.example.domain.AddCurrencyUseCase
import com.saryong.example.domain.LoadAllCurrenciesUseCase
import com.saryong.example.domain.UpdateAllCurrenciesUseCase
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = TestApp::class)
open class CurrencyListViewModelTest {
  lateinit var loadAllCurrenciesUseCase: LoadAllCurrenciesUseCase
  lateinit var updateAllCurrenciesUseCase: UpdateAllCurrenciesUseCase
  lateinit var addCurrencyUseCase: AddCurrencyUseCase
  lateinit var predefinedConstantStorage: PredefinedConstantStorage
  lateinit var viewModel: CurrencyListViewModel

  @Before
  fun setUp() {
    loadAllCurrenciesUseCase = mock()
    updateAllCurrenciesUseCase = mock()
    addCurrencyUseCase = mock()
    predefinedConstantStorage = mock {
      on { currencies }.thenReturn(dummyCurrencySettings())
    }
  }
  
  @Test
  fun testSelectableCode() {
    Preferences.baseCurrency = "EUR"
    viewModel = CurrencyListViewModel(
      loadAllCurrenciesUseCase,
      updateAllCurrenciesUseCase,
      addCurrencyUseCase,
      predefinedConstantStorage
    )
    
    assertEquals(3, viewModel.selectableCodes.size)
    assertEquals("EUR", viewModel.selectableCodes[0]) // selected one
    assertEquals("USD", viewModel.selectableCodes[1]) // by order
    assertEquals("KRW", viewModel.selectableCodes[2])
  }
  
  private fun dummyCurrencySettings() =
    listOf(
      CurrencySetting("KRW", "Korean Won", "₩", 14),
      CurrencySetting("EUR", "Euro", "€", 1),
      CurrencySetting("USD", "United States Dollar", "$", 0)
    )
}
