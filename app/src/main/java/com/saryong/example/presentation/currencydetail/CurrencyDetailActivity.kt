package com.saryong.example.presentation.currencydetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.saryong.example.R
import com.saryong.example.data.local.PredefinedConstantStorage
import com.saryong.example.databinding.ActivityCurrencyDetailBinding
import com.saryong.example.presentation.EXTRA_KEY_CURRENCY_CODE
import com.saryong.example.util.extention.inTransaction
import com.saryong.example.util.fastLazy
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class CurrencyDetailActivity : DaggerAppCompatActivity() {
//  @Inject lateinit var navigationController: NavigationController
//  @Inject lateinit var predefinedConstantStorage: PredefinedConstantStorage
  
  private val binding: ActivityCurrencyDetailBinding by fastLazy {
    DataBindingUtil.setContentView<ActivityCurrencyDetailBinding>(this, R.layout.activity_currency_detail)
  }
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setSupportActionBar(binding.toolbar)
  
    supportActionBar?.title = "Currency Converter"
  
    if (savedInstanceState == null) {
      val targetCode = intent.getStringExtra(EXTRA_KEY_CURRENCY_CODE)
      val fragment = targetCode?.let {
        CurrencyDetailFragment.newInstance(it)
      } ?: CurrencyDetailFragment()
      
      supportFragmentManager.inTransaction {
        add(R.id.fragment_container, fragment)
      }
    }
  
//    val targetCode = intent.getStringExtra(EXTRA_KEY_CURRENCY_CODE)
//    val sourceCode = Preferences.baseCurrency
//    val currencies = predefinedConstantStorage.currencies
//    val sourceCurrency = currencies.find { it.code == sourceCode }
//    val targetCurrency = currencies.find { it.code == targetCode }
//
//    if (sourceCurrency == null || targetCurrency == null) {
//      val errorMessage = "Couldn't find source currency or target!"
//      Timber.e(errorMessage)
//      Snackbar.make(currentFocus, errorMessage, Snackbar.LENGTH_LONG).show()
//      return
//    }
//
    
//    supportActionBar?.title = targetCurrency.name
  
//    binding.fab.setOnClickListener { view ->
//      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_SHORT).show()
//    }
  }
}
