package com.heinika.pokeg.info

import android.content.Context
import com.heinika.pokeg.R

enum class BaseStatus(private val resId: Int, private val resColor: Int) {
  HP(R.string.hp, R.color.colorPrimary),
  ATK(R.string.atk, R.color.md_orange_100),
  DEF(R.string.def, R.color.md_blue_200),
  SP_ATK(R.string.sp_atk, R.color.flying),
  SP_DEF(R.string.sp_def, R.color.md_green_200),
  SPEED(R.string.spd, R.color.poison);

  fun getName(context: Context): CharSequence {
    return context.getString(resId)
  }

  fun getColor(context: Context): Int {
    return context.getColor(resColor)
  }
}