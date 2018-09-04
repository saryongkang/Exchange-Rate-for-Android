package com.saryong.example.presentation.currencydetail

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.saryong.example.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_currency_detail.*

class CurrencyDetailActivity : DaggerAppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_currency_detail)
    setSupportActionBar(toolbar)
    
    fab.setOnClickListener { view ->
      Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        .setAction("Action", null).show()
    }
  }
  
}
