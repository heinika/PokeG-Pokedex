package com.heinika.pokeg.model

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.heinika.pokeg.repository.res.ResUtils
import com.heinika.pokeg.ui.theme.PhysicalMoveColor
import com.heinika.pokeg.ui.theme.SpecialMoveColor
import com.heinika.pokeg.ui.theme.StatusMoveColor
import com.heinika.pokeg.utils.toTypeColor
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class Move(
  val accuracy: String,
  val damageClassId: Int,
  val generationId: Int,
  val id: Int,
  val eName: String,
  val power: String,
  val pp: String,
  var flavorText: String = "",
  val typeId: Int
) {
  val formatId: String = String.format("#%03d", id)

  fun getName(context: Context): String = ResUtils.getMoveName(id, context)

  fun getTypeName(context: Context) = ResUtils.getTypeString(typeId, context)
  fun getTypeColor(context: Context) = Color(ResUtils.getTypeColor(typeId, context))

  fun getDamageClassName(context: Context) = ResUtils.getDamageClassName(damageClassId, context)

  val typeColor = id.toTypeColor

  val damageClassColor = when (damageClassId) {
    1 -> StatusMoveColor
    2 -> PhysicalMoveColor
    3 -> SpecialMoveColor
    else -> StatusMoveColor
  }
}