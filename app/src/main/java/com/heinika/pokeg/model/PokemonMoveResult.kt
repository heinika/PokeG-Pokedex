package com.heinika.getpokeinfo.model.movecsv

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonAndMoveCvsX(
  val id: Int,
  val moves: MutableList<PokemonMoveCsvX>,
)
