package com.heinika.pokeg.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ability(
    val cname: String,
    val effect: String,
    val ename: String,
    @field:Json(name = "no") val num: Int,
    val synopsis: String
)