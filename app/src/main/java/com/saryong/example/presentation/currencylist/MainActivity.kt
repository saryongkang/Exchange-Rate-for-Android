package com.saryong.example.presentation.currencylist

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.saryong.example.R
import com.saryong.example.databinding.ActivityMainBinding
import com.saryong.example.util.fastLazy
import timber.log.Timber

class MainActivity : AppCompatActivity() {
  private val binding: ActivityMainBinding by fastLazy {
    DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
  }

  private val viewModel by fastLazy {
//    ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
    MainViewModel()
  }

  // FIXME
//  private lateinit var viewModelFactory: ViewModelProvider.Factory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setSupportActionBar(binding.toolbar)

    binding.viewModel = viewModel
    binding.recyclerViewCurrencyList.adapter = CurrencyListAdapter()
  }

//  override fun onCreateOptionsMenu(menu: Menu): Boolean {
//    // Inflate the menu; this adds items to the action bar if it is present.
//    menuInflater.inflate(R.menu.menu_main, menu)
//    return true
//  }

//  override fun onOptionsItemSelected(item: MenuItem): Boolean {
//    // Handle action bar item clicks here. The action bar will
//    // automatically handle clicks on the Home/Up button, so long
//    // as you specify a parent activity in AndroidManifest.xml.
//    return when (item.itemId) {
//      R.id.action_settings -> true
//      else -> super.onOptionsItemSelected(item)
//    }
//  }
}
