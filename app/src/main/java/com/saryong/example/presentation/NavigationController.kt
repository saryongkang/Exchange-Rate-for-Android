package com.saryong.example.presentation

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import com.saryong.example.R
import com.saryong.example.presentation.currencylist.MainActivity
import com.saryong.example.presentation.common.Findable
import javax.inject.Inject

class NavigationController @Inject constructor(
  private val activity: AppCompatActivity
) {
  private val containerId = R.id.container
  private val fragmentManager: FragmentManager = activity.supportFragmentManager

  fun navigateToMainActivity() {
    val intent = Intent(activity, MainActivity::class.java)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
    activity.startActivity(intent)
  }

  private fun replaceFragment(fragment: Fragment) {
    fragmentManager
      .beginTransaction()
      .replace(containerId, fragment, (fragment as? Findable)?.tagForFinding)
      .commitAllowingStateLoss()
  }
}