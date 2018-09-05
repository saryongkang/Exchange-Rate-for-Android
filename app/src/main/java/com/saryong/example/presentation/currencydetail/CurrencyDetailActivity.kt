package com.saryong.example.presentation.currencydetail

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.saryong.example.R
import com.saryong.example.databinding.ActivityCurrencyDetailBinding
import com.saryong.example.presentation.EXTRA_KEY_CURRENCY_CODE
import com.saryong.example.util.extention.inTransaction
import com.saryong.example.util.fastLazy
import dagger.android.support.DaggerAppCompatActivity

class CurrencyDetailActivity : DaggerAppCompatActivity() {
  
  private val binding: ActivityCurrencyDetailBinding by fastLazy {
    DataBindingUtil.setContentView<ActivityCurrencyDetailBinding>(this, R.layout.activity_currency_detail)
  }
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    //setSupportActionBar(binding.toolbar)
  
    binding
    
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
  }
}
