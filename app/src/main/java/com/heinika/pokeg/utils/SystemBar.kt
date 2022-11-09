package com.heinika.pokeg.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Resources

object SystemBar {
  var statusBarHeight: Int = 12.dp
    private set
  var navigationBarHeight = 0.dp
    private set

  val statusBarHeightDp: Float
    get() = statusBarHeight.toFloat() / Resources.getSystem().displayMetrics.density

  val navigationBarHeightDp: Float
    get() = navigationBarHeight.toFloat() / Resources.getSystem().displayMetrics.density


  @SuppressLint("InternalInsetResource", "DiscouragedApi")
  fun initStatusBarHeight(activity: Activity) {
    val statusBarResourceId: Int =
      activity.resources.getIdentifier("status_bar_height", "dimen", "android")
    if (statusBarResourceId > 0) {
      statusBarHeight = activity.resources.getDimensionPixelSize(statusBarResourceId)
    }

    val navigationBarResourceId: Int =
      activity.resources.getIdentifier("navigation_bar_height", "dimen", "android")
    if (navigationBarResourceId > 0) {
      navigationBarHeight = activity.resources.getDimensionPixelSize(navigationBarResourceId)
    }

//    activity.window.decorView.setOnApplyWindowInsetsListener { _, insets ->
//      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
//        statusBarHeight = insets.getInsets(WindowInsets.Type.systemBars()).top
//        navigationBarHeight = insets.getInsets(WindowInsets.Type.systemBars()).bottom
//      } else {
//        statusBarHeight = insets.systemWindowInsetTop
//        navigationBarHeight = insets.systemWindowInsetBottom
//      }
//      return@setOnApplyWindowInsetsListener insets
//    }
  }
}