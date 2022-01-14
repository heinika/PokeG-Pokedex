package com.heinika.pokeg.info

import android.content.Context
import com.heinika.pokeg.R

enum class BodyStatus(private val resId: Int, private val resColor: Int) {
  WEIGHT(R.string.weight, R.color.weight),
  HEIGHT(R.string.height, R.color.height);

  fun getName(context: Context): CharSequence {
    return context.getString(resId)
  }

  fun getColor(context: Context): Int {
    return context.getColor(resColor)
  }
}