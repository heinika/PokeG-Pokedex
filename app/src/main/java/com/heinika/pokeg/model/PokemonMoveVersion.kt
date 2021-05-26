package com.heinika.getpokeinfo.model.movecsv

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonMoveVersion(
  val id: Int,
  @Json(name = "l")
  val versionList:MutableList<Int>
)