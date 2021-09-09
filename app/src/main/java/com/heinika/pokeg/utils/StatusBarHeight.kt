package com.heinika.pokeg.utils

import android.app.Activity

object StatusBarHeight {
    var value: Int = 12.dp

    fun initStatusBarHeight(activity: Activity) {
        val resourceId: Int =
            activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            value = (activity.resources.getDimensionPixelSize(resourceId) + 0.5f).toInt()
        }
    }
}