package com.heinika.pokeg.model

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
)