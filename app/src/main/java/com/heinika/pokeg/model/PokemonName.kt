package com.heinika.pokeg.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonName(
    @Json(name = "genus")
    val genus: String,
    @Json(name = "local_language_id")
    val localLanguageId: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "pokemon_species_id")
    val pokemonSpeciesId: Int
)