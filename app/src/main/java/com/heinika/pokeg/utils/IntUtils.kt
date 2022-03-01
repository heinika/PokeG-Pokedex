package com.heinika.pokeg.utils

import android.content.Context
import com.heinika.pokeg.R

fun Int.toFormString(context: Context): String  = when(this){
    2 -> context.getString(R.string.alola)
    3 -> context.getString(R.string.galar)
    4 -> context.getString(R.string.hisui)
    5 -> context.getString(R.string.white_stripes)
    else -> ""
  }