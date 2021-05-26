package com.heinika.getpokeinfo.model.movecsv

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonMoveCsvX(
  @Json(name = "mo")
  val moveId: Int,
  @Json(name = "l")
  val level: Int,
  @Json(name = "me")
  val methodId: Int
)