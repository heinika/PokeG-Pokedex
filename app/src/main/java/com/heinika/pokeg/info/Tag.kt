package com.heinika.pokeg.info

import androidx.compose.ui.graphics.Color
import com.heinika.pokeg.R
import com.heinika.pokeg.ui.theme.AlolaColor
import com.heinika.pokeg.ui.theme.BabyColor
import com.heinika.pokeg.ui.theme.FavoriteColor
import com.heinika.pokeg.ui.theme.GalarColor
import com.heinika.pokeg.ui.theme.GmaxColor
import com.heinika.pokeg.ui.theme.HisuiColor
import com.heinika.pokeg.ui.theme.LegendaryColor
import com.heinika.pokeg.ui.theme.MegaColor
import com.heinika.pokeg.ui.theme.MythicalColor

enum class Tag(val resId: Int, val color: Color) {
  Favorite(R.string.favorite, FavoriteColor),
  Legendary(R.string.legendary, LegendaryColor),
  Mythical(R.string.mythical, MythicalColor),
  BABY(R.string.baby, BabyColor),
  ALOLA(R.string.alola, AlolaColor),
  GALAR(R.string.galar, GalarColor),
  HISUI(R.string.hisui, HisuiColor),
  MEGA(R.string.mega, MegaColor),
  GMAX(R.string.gmax, GmaxColor);
}

val babyIdList = listOf(298,172,173,174,175,236,238,239,240,360,406,433,438,439,440,446,447,458,848)

val legendaryIdList = listOf(144,145,146,150,640,243,244,245,250,249,377,378,379,380,381,383,382,384,10118,481,480,483,482,485,486,487,484,488,890,638,639,641,642,643,644,645,646,716,718,717,785,786,787,788,789,790,792,791,800,888,889,891,892,895,894,896,897,898,10007,10019,10020,10021,10022,10023,10043,10044,10062,10063,10077,10078,10079,10119,10120,10155,10156,10157,10170,10169,10171,10181,10188,10189,10190,10193,10191,10194,10226,10227)

val mythicalIdList = listOf(151,251,385,386,489,490,492,491,494,493,647,648,649,719,720,721,801,802,807,808,809,893,10002,10001,10003,10006,10018,10024,10075,10086,10147,10192,10208)

val alolaIdList = 10091..10115
val galarIdList = 10158..10177
val hisuiIdList = listOf(40058,40059,40100,40101,40157,40211,40215,40503,40549,40570,40571,40628,40705,40706,40713,40724,70550,)
val megaIdList = 10033..10090

val gmaxIdRange = 10195..10229