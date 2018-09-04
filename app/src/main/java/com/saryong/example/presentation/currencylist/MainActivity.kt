package com.saryong.example.presentation.currencylist

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.saryong.example.R
import com.saryong.example.databinding.ActivityMainBinding
import com.saryong.example.presentation.NavigationController
import com.saryong.example.util.fastLazy
import com.saryong.example.util.livedata.EventObserver
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  @Inject lateinit var navigationController: NavigationController
  
  private val binding: ActivityMainBinding by fastLazy {
    DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
  }

  private val viewModel by fastLazy {
    ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
  }
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setSupportActionBar(binding.toolbar)

    binding.setLifecycleOwner(this)
    binding.viewModel = viewModel
    binding.recyclerViewCurrencyList.adapter = CurrencyListAdapter()

    binding.fab.setOnClickListener {
      navigationController.navigateToAddCurrencyActivity()
    }
    
    viewModel.navigateToDetailActivity.observe(this, EventObserver {
      navigationController.navigateToDetailActivity()
    })
  }
  
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    
    if (resultCode == Activity.RESULT_OK && requestCode == NavigationController.ADD_CURRENCY_REQUEST_CODE) {
      Timber.d(data?.getStringExtra(RESULT_KEY))
      data?.getStringExtra(RESULT_KEY)?.let { currencyCode ->
        viewModel.addCurrency(currencyCode)
      }
    }
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
  
  companion object {
    const val RESULT_KEY = "currency_code"
  }
}
