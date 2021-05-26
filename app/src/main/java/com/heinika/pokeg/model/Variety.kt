package com.heinika.pokeg.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Variety(
    @Json(name = "id")
    val id: Int,
    @Json(name = "isDefault")
    val isDefault: Boolean,
    @Json(name = "name")
    val name: String
)