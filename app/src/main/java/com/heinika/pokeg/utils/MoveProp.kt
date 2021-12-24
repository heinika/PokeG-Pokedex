package com.heinika.pokeg.utils

import androidx.compose.ui.graphics.Color
import com.heinika.pokeg.R
import com.heinika.pokeg.ui.theme.AccuracySortColor
import com.heinika.pokeg.ui.theme.PPSortColor
import com.heinika.pokeg.ui.theme.PowerSortColor

object MoveProp {
  enum class SortType(val resStringId: Int, val color: Color) {
    Power(R.string.power, PowerSortColor),
    Accuracy(R.string.accuracy, AccuracySortColor),
    PP(R.string.pp, PPSortColor);
  }
}