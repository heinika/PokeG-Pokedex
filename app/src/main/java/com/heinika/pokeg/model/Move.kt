package com.heinika.pokeg.model

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.heinika.pokeg.repository.res.ResUtils
import com.heinika.pokeg.ui.theme.physicalMoveColor
import com.heinika.pokeg.ui.theme.specialMoveColor
import com.heinika.pokeg.ui.theme.statusMoveColor
import com.squareup.moshi.Json
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

  val damageClassColor = when (damageClassId) {
    1 -> statusMoveColor
    2 -> physicalMoveColor
    3 -> specialMoveColor
    else -> statusMoveColor
  }
}