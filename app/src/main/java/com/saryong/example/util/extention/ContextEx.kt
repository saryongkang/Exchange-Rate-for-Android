package com.saryong.example.util.extention

import android.content.Context
import android.net.ConnectivityManager

fun Context.isNetworkConnected(): Boolean {
  // Don't call getSystemService from context of Activity, or memory leaks.
  // https://stackoverflow.com/questions/41431409/connectivitymanager-leaking-not-sure-how-to-resolve
  val connectivityManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
  return (connectivityManager?.activeNetworkInfo?.isConnected == true)
}