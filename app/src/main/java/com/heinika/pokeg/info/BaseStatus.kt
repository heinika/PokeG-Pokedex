package com.heinika.pokeg.info

import androidx.compose.ui.graphics.Color
import com.heinika.pokeg.R
import com.heinika.pokeg.ui.theme.*

enum class BaseStatus(val resId: Int, val color: Color) {
  HP(R.string.hp, Red200),
  ATK(R.string.atk, progressAttackColor),
  DEF(R.string.def, progressDefenseColor),
  SP_ATK(R.string.sp_atk, progressSpAttackColor),
  SP_DEF(R.string.sp_def, progressSpDefenseColor),
  SPEED(R.string.spd, progressSpeedColor);
}