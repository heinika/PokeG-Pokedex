package com.heinika.pokeg.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpeciesEggGroup(
    @Json(name = "egg_group_id")
    val eggGroupId: Int,
    @Json(name = "species_id")
    val speciesId: Int
)