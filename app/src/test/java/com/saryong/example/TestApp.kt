package com.saryong.example

import com.chibatching.kotpref.Kotpref
import org.robolectric.RuntimeEnvironment

class TestApp : App() {
  override fun onCreate() {
    Kotpref.init(RuntimeEnvironment.application)
  }
  
  override fun isInUnitTests() = true
  
}