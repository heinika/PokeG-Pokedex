package com.heinika.pokeg.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpecieFlavorText(
    @Json(name = "flavor_text")
    val flavorText: String,
    @Json(name = "species_id")
    val speciesId: Int,
    @Json(name = "version_id")
    val versionId: Int
)