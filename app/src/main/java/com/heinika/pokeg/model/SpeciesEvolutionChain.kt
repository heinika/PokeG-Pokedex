package com.heinika.pokeg.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpeciesEvolutionChain(
  var evolvedFromSpeciesId: Int = -1,
  var evolvedFromName: String = "",
  var evolvedToName: String = "",
  @Json(name = "evolution_trigger_id")
  val evolutionTriggerId: Int,
  @Json(name = "evolved_species_id")
  val evolvedToSpeciesId: Int,
  @Json(name = "gender_id")
  val genderId: String,
  @Json(name = "held_item_id")
  val heldItemId: String,
  @Json(name = "id")
  val id: Int,
  @Json(name = "known_move_id")
  val knownMoveId: String,
  @Json(name = "known_move_type_id")
  val knownMoveTypeId: String,
  @Json(name = "location_id")
  val locationId: String,
  @Json(name = "minimum_affection")
  val minimumAffection: String,
  @Json(name = "minimum_beauty")
  val minimumBeauty: String,
  @Json(name = "minimum_happiness")
  val minimumHappiness: String,
  @Json(name = "minimum_level")
  val minimumLevel: String,
  @Json(name = "needs_overworld_rain")
  val needsOverworldRain: Int,
  @Json(name = "party_species_id")
  val partySpeciesId: String,
  @Json(name = "party_type_id")
  val partyTypeId: String,
  @Json(name = "relative_physical_stats")
  val relativePhysicalStats: String,
  @Json(name = "time_of_day")
  val timeOfDay: String,
  @Json(name = "trade_species_id")
  val tradeSpeciesId: String,
  @Json(name = "trigger_item_id")
  val triggerItemId: String,
  @Json(name = "turn_upside_down")
  val turnUpsideDown: Int
)