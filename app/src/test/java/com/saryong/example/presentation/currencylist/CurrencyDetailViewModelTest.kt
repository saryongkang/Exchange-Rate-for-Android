package com.saryong.example

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class, application = TestApp::class)
open class ExampleUnitTest {
  @Before
  fun setUp() {
  }
  
  @Test
  fun addition_isCorrect() {
    assertEquals(4, 2 + 2)
  }
}
