package com.saryong.example.presentation.currencydetail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.saryong.example.data.local.CurrencySetting
import com.saryong.example.databinding.FragmentCurrencyDetailBinding
import com.saryong.example.util.extention.viewModelProvider
import com.saryong.example.util.fastLazy
import com.saryong.example.util.livedata.EventObserver
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class CurrencyDetailFragment : DaggerFragment() {
  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  
  private val viewModel by fastLazy {
    viewModelProvider(viewModelFactory) as CurrencyDetailViewModel
  }
  
  private lateinit var binding: FragmentCurrencyDetailBinding
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    binding = FragmentCurrencyDetailBinding.inflate(inflater, container, false)
    
    viewModel.initAction.observe(this, EventObserver {
      bindCurrencySettings(it.first, it.second)
    })
    
    if (savedInstanceState == null) {
      arguments?.getString(ARG_TARGET_CURRENCY)?.let {
        viewModel.init(it)
      }
    }
    
    binding.setLifecycleOwner(this)
    binding.viewModel = viewModel
    
    return binding.root
  }
  
  private fun bindCurrencySettings(source: CurrencySetting, target: CurrencySetting) {
    binding.sourceCurrencyText.text = source.longName
    binding.targetCurrencyText.text = target.longName
  }
  
  
  companion object {
    private const val ARG_TARGET_CURRENCY = "arg.TARGET_CURRENCY"
    
    fun newInstance(targetCurrency: String): CurrencyDetailFragment =
      CurrencyDetailFragment().apply {
        arguments = bundleOf(ARG_TARGET_CURRENCY to targetCurrency)
      }
  }
}
