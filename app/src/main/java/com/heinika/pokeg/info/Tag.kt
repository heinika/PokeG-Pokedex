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

val babyIdList = listOf(298,172,173,174,175,236,238,239,240,360,406,433,438,439,440,446,447,458,848)

val legendaryIdList = listOf(144,145,146,150,640,243,244,245,250,249,377,378,379,380,381,383,382,384,10118,481,480,483,482,485,486,487,484,488,890,638,639,641,642,643,644,645,646,716,718,717,785,786,787,788,789,790,792,791,800,888,889,891,892,895,894,896,897,898,10007,10019,10020,10021,10022,10023,10043,10044,10062,10063,10077,10078,10079,10119,10120,10155,10156,10157,10170,10169,10171,10181,10188,10189,10190,10193,10191,10194,10226,10227)

val mythicalIdList = listOf(151,251,385,386,489,490,492,491,494,493,647,648,649,719,720,721,801,802,807,808,809,893,10002,10001,10003,10006,10018,10024,10075,10086,10147,10192,10208)