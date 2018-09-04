package com.saryong.example.presentation

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.saryong.example.R
import com.saryong.example.presentation.addcurrency.AddCurrencyActivity
import com.saryong.example.presentation.currencylist.CurrencyListActivity
import com.saryong.example.presentation.common.Findable
import com.saryong.example.presentation.currencydetail.CurrencyDetailActivity
import javax.inject.Inject

const val EXTRA_KEY_CURRENCY_CODE = "EXTRA_KEY_CURRENCY_CODE"
const val REQUEST_CODE_ADD_CURRENCY = 0x0001

class NavigationController @Inject constructor(
  private val activity: AppCompatActivity
) {
  private val containerId = R.id.container
  private val fragmentManager: FragmentManager = activity.supportFragmentManager

  fun navigateToCurrencyListActivity() {
    val intent = Intent(activity, CurrencyListActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
    activity.startActivity(intent)
  }

  fun navigateToAddCurrencyActivity() {
    val intent = Intent(activity.applicationContext, AddCurrencyActivity::class.java)
    activity.startActivityForResult(intent, REQUEST_CODE_ADD_CURRENCY)
  }
  
  fun navigateToDetailActivity(currencyCode: String) {
    val intent = Intent(activity, CurrencyDetailActivity::class.java)
    intent.putExtra(EXTRA_KEY_CURRENCY_CODE, currencyCode)
    activity.startActivity(intent)
  }

  private fun replaceFragment(fragment: Fragment) {
    fragmentManager
      .beginTransaction()
      .replace(containerId, fragment, (fragment as? Findable)?.tagForFinding)
      .commitAllowingStateLoss()
  }
}