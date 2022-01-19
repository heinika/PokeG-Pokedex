package com.heinika.pokeg.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Team(
  val teamName: String,
  val teamNumbers: List<TeamNumber>
)
