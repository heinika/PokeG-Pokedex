package com.heinika.pokeg.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonAbility(
    @Json(name = "ability_id")
    val abilityId: Int,
    @Json(name = "is_hidden")
    val isHidden: Int,
    @Json(name = "pokemon_id")
    val pokemonId: Int,
    @Json(name = "slot")
    val slot: Int
)