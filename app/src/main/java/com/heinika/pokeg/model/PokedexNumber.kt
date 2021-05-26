package com.heinika.pokeg.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokedexNumber(
    @Json(name = "entryNumber")
    val entryNumber: Int,
    @Json(name = "pokedexId")
    val pokedexId: Int,
    @Json(name = "pokedexName")
    val pokedexName: String
)