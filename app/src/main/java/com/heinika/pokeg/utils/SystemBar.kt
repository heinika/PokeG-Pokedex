package com.heinika.pokeg.utils

import android.app.Activity
import android.os.Build
import android.view.WindowInsets
import timber.log.Timber

object SystemBar {
  var statusBarHeight: Int = 12.dp
    private set
  var navigationBarHeight = 0.dp
    private set

  @Suppress("DEPRECATION")
  fun initStatusBarHeight(activity: Activity) {
    val statusBarResourceId: Int = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (statusBarResourceId > 0) {
      statusBarHeight = activity.resources.getDimensionPixelSize(statusBarResourceId)
    }
    val navigationBarResourceId: Int = activity.resources.getIdentifier("navigation_bar_height", "dimen", "android")
    if (navigationBarResourceId > 0) {
      navigationBarHeight = activity.resources.getDimensionPixelSize(navigationBarResourceId)
    }

    activity.window.decorView.setOnApplyWindowInsetsListener { _, insets ->
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        statusBarHeight = insets.getInsets(WindowInsets.Type.systemBars()).top
        navigationBarHeight = insets.getInsets(WindowInsets.Type.systemBars()).bottom
      }else{
        statusBarHeight = insets.systemWindowInsetTop
        navigationBarHeight = insets.systemWindowInsetBottom
      }
      Timber.i("negationBarHeight:$navigationBarHeight")
      return@setOnApplyWindowInsetsListener insets
    }
  }
}