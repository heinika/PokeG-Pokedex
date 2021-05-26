package com.heinika.pokeg.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonMove(
  @Json(name = "mo")
  val moveId: Int,
  @Json(name = "l")
  val level: Int,
  @Json(name = "me")
  val methodId: Int
)