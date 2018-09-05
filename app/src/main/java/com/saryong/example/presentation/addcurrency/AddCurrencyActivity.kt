package com.saryong.example.presentation.addcurrency

import android.app.Activity
import android.arch.lifecycle.ViewModelProvider
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.saryong.example.R
import com.saryong.example.databinding.ActivityAddCurrencyBinding
import com.saryong.example.presentation.EXTRA_KEY_CURRENCY_CODE
import com.saryong.example.presentation.common.BaseActivity
import com.saryong.example.util.fastLazy
import com.saryong.example.util.livedata.EventObserver
import javax.inject.Inject


class AddCurrencyActivity : BaseActivity() {
  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

  private val binding: ActivityAddCurrencyBinding by fastLazy {
    DataBindingUtil.setContentView<ActivityAddCurrencyBinding>(this, R.layout.activity_add_currency)
  }

  private val viewModel by fastLazy {
    viewModelProvider(viewModelFactory) as AddCurrencyViewModel
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    supportActionBar?.title = "Select Currency"
  
    binding.setLifecycleOwner(this)
    binding.viewModel = viewModel
    binding.newCurrenciesRecyclerView.adapter = SimpleCurrencyListAdapter(viewModel)
    
    viewModel.finishWithCurrencyAction.observe(this, EventObserver {
      val intent = Intent()
      intent.putExtra(EXTRA_KEY_CURRENCY_CODE, it)
      setResult(Activity.RESULT_OK, intent)
      
      finish()
    })
  }
}
