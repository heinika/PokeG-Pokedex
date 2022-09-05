package com.heinika.pokeg.info

import androidx.compose.ui.graphics.Color
import com.heinika.pokeg.R
import com.heinika.pokeg.ui.theme.HeightColor
import com.heinika.pokeg.ui.theme.WeightColor

enum class BodyStatus(val resId: Int,val color: Color) {
  WEIGHT(R.string.weight, WeightColor),
  HEIGHT(R.string.height, HeightColor);
}