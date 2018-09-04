package com.saryong.example.presentation.splash

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.saryong.example.R.layout
import com.saryong.example.data.pref.Preferences
import com.saryong.example.presentation.NavigationController
import com.saryong.example.presentation.currencylist.CurrencyListActivity
import com.saryong.example.util.fastLazy
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.fullscreen_content
import timber.log.Timber
import java.lang.ref.WeakReference
import javax.inject.Inject

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
class SplashActivity : DaggerAppCompatActivity() {
  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  @Inject lateinit var navigationController: NavigationController
  
  private val hideHandler = Handler()
  private val hideRunnable = Runnable { hide() }
  private val hidePart2Runnable = Runnable {
    // Delayed removal of status and navigation bar

    // Some of these constants are new as of API 16 (Jelly Bean)
    // and API 19 (KitKat). It is safe to use them, as they are inlined
    // at compile-time and do nothing on earlier devices.
    fullscreen_content.systemUiVisibility =
      View.SYSTEM_UI_FLAG_LOW_PROFILE or
      View.SYSTEM_UI_FLAG_FULLSCREEN or
      View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
      View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
      View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
      View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
  }

  private val viewModel by fastLazy {
    ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)
  }

  private var startupTask: StartupTask? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    setContentView(layout.activity_splash)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)

    startupTask = StartupTask(WeakReference(this))
    startupTask?.execute()
  }

  override fun onPostCreate(savedInstanceState: Bundle?) {
    super.onPostCreate(savedInstanceState)

    // Trigger the initial hide() shortly after the activity has been
    // created, to briefly hint to the user that UI controls
    // are available.
    delayedHide(HIDE_DELAY)
  }

  override fun onDestroy() {
    startupTask?.cancel(true)

    super.onDestroy()
  }

  private fun hide() {
    supportActionBar?.hide()

    // Schedule a runnable to remove the status and navigation bar after a delay
    hideHandler.postDelayed(hidePart2Runnable, UI_ANIMATION_DELAY)
  }

  private fun delayedHide(delayMillis: Long) {
    hideHandler.removeCallbacks(hideRunnable)
    hideHandler.postDelayed(hideRunnable, delayMillis)
  }

  class StartupTask(private val context: WeakReference<Context>) : AsyncTask<Void, Void, Boolean>() {
    override fun doInBackground(vararg params: Void?): Boolean {
      Thread.sleep(HIDE_DELAY + UI_ANIMATION_DELAY) // this is unnecessary in 'real' project

      if (Preferences.firstLaunch) {

//        Preferences.firstLaunch = false
      }

      return true
    }

    override fun onPostExecute(result: Boolean?) {
      if (result == true && !isCancelled) {

        context.get()?.let {
          // Navigate to Main Activity
          val intent = Intent(it, CurrencyListActivity::class.java)
          intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
          it.startActivity(intent)
        }

      } else {
        Timber.e("Startup process failed!")

        // TODO show error popup
      }
    }
  }

  companion object {
    private const val HIDE_DELAY = 100L

    /*
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private const val UI_ANIMATION_DELAY = 300L
  }
}
