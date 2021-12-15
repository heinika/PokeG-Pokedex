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
  val contest_effect_id: String,
  @Json(name = "contest_type_id")
  val contestTypeId: String,
  @Json(name = "damage_class_id")
  val damageClassId: Int,
  val effect_chance: String,
  val effect_id: Int,
  val generation_id: Int,
  val id: Int,
  val identifier: String,
  val power: String,
  val pp: String,
  val priority: Int,
  val super_contest_effect_id: String,
  val target_id: Int,
  @Json(name = "type_id")
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