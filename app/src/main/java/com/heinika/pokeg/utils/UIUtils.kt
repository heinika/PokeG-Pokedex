package com.heinika.pokeg.utils

import android.content.res.Resources

val Number.dp: Int
  get() = (this.toInt() * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Int.toBoolean: Boolean
  get() = this == 1