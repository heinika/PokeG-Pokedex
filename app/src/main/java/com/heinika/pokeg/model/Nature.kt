package com.heinika.pokeg.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Nature(
    val cname: String,
    val ename: String,
    val jname: String,
    val grow_easy: String,
    val grow_hard: String,
    val taste_dislike: String,
    val taste_like: String
)