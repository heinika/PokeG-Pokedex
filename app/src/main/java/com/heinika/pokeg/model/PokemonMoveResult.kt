package com.heinika.pokeg.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonMoveResult(
  val id: Int,
  val moves: MutableList<PokemonMove>,
)
