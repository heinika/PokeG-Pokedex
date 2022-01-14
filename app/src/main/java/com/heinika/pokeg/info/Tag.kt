package com.heinika.pokeg.info

import android.content.Context
import com.heinika.pokeg.R

enum class Tag(
  private val resId: Int,
  private val resColor: Int
) {
  Favorite(R.string.favorite, R.color.favorite),
  Legendary(R.string.legendary, R.color.legendary),
  Mythical(R.string.mythical, R.color.mythical),
  BABY(R.string.baby, R.color.baby);

  fun getName(context: Context): CharSequence {
    return context.getString(resId)
  }

  fun getColor(context: Context): Int {
    return context.getColor(resColor)
  }
}