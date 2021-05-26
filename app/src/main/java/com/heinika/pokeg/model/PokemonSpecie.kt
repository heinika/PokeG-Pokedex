package com.heinika.pokeg.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonSpecie(
    @Json(name = "baseHappiness")
    val baseHappiness: Int,
    @Json(name = "cName")
    val cName: String,
    @Json(name = "captureRate")
    val captureRate: Int,
    @Json(name = "color")
    val color: String,
    @Json(name = "eName")
    val eName: String,
    @Json(name = "eggGroups")
    val eggGroups: List<Any>,
    @Json(name = "evolutionChainId")
    val evolutionChainId: Int,
    @Json(name = "flavorTextEntries")
    val flavorTextEntries: List<Any>,
    @Json(name = "formDescriptions")
    val formDescriptions: List<Any>,
    @Json(name = "formsSwitchable")
    val formsSwitchable: Boolean,
    @Json(name = "genderRate")
    val genderRate: Int,
    @Json(name = "genera")
    val genera: String,
    @Json(name = "generation")
    val generation: String,
    @Json(name = "growthRate")
    val growthRate: String,
    @Json(name = "habitat")
    val habitat: String,
    @Json(name = "hatchCounter")
    val hatchCounter: Int,
    @Json(name = "id")
    val id: Int,
    @Json(name = "isBaby")
    val isBaby: Boolean,
    @Json(name = "isLegendary")
    val isLegendary: Boolean,
    @Json(name = "isMythical")
    val isMythical: Boolean,
    @Json(name = "jName")
    val jName: String,
    @Json(name = "order")
    val order: Int,
    @Json(name = "pokedexNumbers")
    val pokedexNumbers: List<PokedexNumber>,
    @Json(name = "shape")
    val shape: String,
    @Json(name = "varieties")
    val varieties: List<Variety>
)