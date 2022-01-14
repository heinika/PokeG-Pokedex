package com.heinika.pokeg.info

import androidx.compose.ui.graphics.Color
import com.heinika.pokeg.R
import com.heinika.pokeg.ui.theme.*

object MoveProp {
  enum class SortType(val resStringId: Int, val color: Color) {
    Power(R.string.power, PowerSortColor),
    Accuracy(R.string.accuracy, AccuracySortColor),
    PP(R.string.pp, PPSortColor);
  }

  enum class DamageClass(val resStringId: Int, val color: Color, val resDrawableId: Int) {
    Status(R.string.damage_class_1, StatusMoveColor, R.drawable.status),
    Physical(R.string.damage_class_2, PhysicalMoveColor, R.drawable.physical),
    Special(R.string.damage_class_3, SpecialMoveColor, R.drawable.special);
  }
}