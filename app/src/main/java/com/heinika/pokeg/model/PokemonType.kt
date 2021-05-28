package com.heinika.pokeg.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonType(
    @Json(name = "pokemon_id")
    val pokemonId: Int,
    @Json(name = "slot")
    val slot: Int,
    @Json(name = "type_id")
    val typeId: Int
)