package com.saryong.example.presentation.currencylist

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.saryong.example.R
import com.saryong.example.databinding.ActivityCurrencyListBinding
import com.saryong.example.presentation.EXTRA_KEY_CURRENCY_CODE
import com.saryong.example.presentation.NavigationController
import com.saryong.example.presentation.REQUEST_CODE_ADD_CURRENCY
import com.saryong.example.presentation.common.BaseActivity
import com.saryong.example.util.extention.isNetworkConnected
import com.saryong.example.util.fastLazy
import com.saryong.example.util.livedata.EventObserver
import timber.log.Timber
import javax.inject.Inject

class CurrencyListActivity : BaseActivity() {
  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  @Inject lateinit var navigationController: NavigationController
  
  private val binding: ActivityCurrencyListBinding by fastLazy {
    DataBindingUtil.setContentView<ActivityCurrencyListBinding>(this, R.layout.activity_currency_list)
  }
  
  private lateinit var viewModel: CurrencyListViewModel
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setSupportActionBar(binding.toolbar)
    viewModel = viewModelProvider(viewModelFactory)

    binding.setLifecycleOwner(this)
    binding.viewModel = viewModel
    binding.recyclerViewCurrencyList.adapter = CurrencyListAdapter(viewModel)
  
    binding.fab.setOnClickListener {
      navigationController.navigateToAddCurrencyActivity()
    }
    
    viewModel.navigateToDetailAction.observe(this, EventObserver {
      navigationController.navigateToDetailActivity(it)
    })
    
    viewModel.snackbarMessage.observe(this, Observer {
      it?.let { message ->
        Snackbar.make(currentFocus, message, Snackbar.LENGTH_LONG).show()
      }
    })
    
    if (applicationContext.isNetworkConnected()) {
      viewModel.networkAvailability.onNext(true)
    } else {
      // TODO make loop to check if network is connected, using HandlerThread or coroutine
    }
  }
  
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    
    if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_ADD_CURRENCY) {
      Timber.d(data?.getStringExtra(EXTRA_KEY_CURRENCY_CODE))
      data?.getStringExtra(EXTRA_KEY_CURRENCY_CODE)?.let { currencyCode ->
        viewModel.addCurrency(currencyCode)
      }
    }
  }
}
