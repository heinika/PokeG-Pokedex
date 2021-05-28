package com.heinika.pokeg.utils

import android.content.res.Resources

val Number.dp: Int
  get() = (this.toInt() * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Int.toBoolean: Boolean
  get() = this == 1

val Int.isCnId: Boolean
  get() = this == 12

val Int.isEnId: Boolean
  get() = this == 9

val Int.isJaId: Boolean
  get() = this == 1

val Int.isHPStat: Boolean
  get() = this == 1

val Int.isAttackStat: Boolean
  get() = this == 2

val Int.isDefenseStat: Boolean
  get() = this == 3

val Int.isSAttackStat: Boolean
  get() = this == 4

val Int.isSDefenseStat: Boolean
  get() = this == 5

val Int.isSPeedStat: Boolean
  get() = this == 6