package com.heinika.pokeg.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpecieFlavorBaseText(
    @Json(name = "description")
    val description: String,
    @Json(name = "id")
    val id: Int
)