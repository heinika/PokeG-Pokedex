package com.heinika.pokeg.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonSpecie(
  @Json(name = "base_happiness")
  val baseHappiness: Int,
  @Json(name = "capture_rate")
  val captureRate: Int,
  @Json(name = "color_id")
  val colorId: Int,
  @Json(name = "conquest_order")
  val conquestOrder: String,
  @Json(name = "evolution_chain_id")
  val evolutionChainId: Int,
  @Json(name = "evolves_from_species_id")
  val evolvesFromSpeciesId: Int,
  @Json(name = "forms_switchable")
  val formsSwitchable: Int,
  @Json(name = "gender_rate")
  val genderRate: Int,
  @Json(name = "generation_id")
  val generationId: Int,
  @Json(name = "growth_rate_id")
  val growthRateId: Int,
  @Json(name = "habitat_id")
  val habitatId: String,
  @Json(name = "has_gender_differences")
  val hasGenderDifferences: Int,
  @Json(name = "hatch_counter")
  val hatchCounter: Int,
  @Json(name = "id")
  val id: Int,
  @Json(name = "identifier")
  val identifier: String,
  @Json(name = "is_baby")
  val isBaby: Int,
  @Json(name = "is_legendary")
  val isLegendary: Int,
  @Json(name = "is_mythical")
  val isMythical: Int,
  @Json(name = "order")
  val order: Int,
  @Json(name = "shape_id")
  val shapeId: Int
) {
  fun getEggSteps() = "${255 * (hatchCounter + 1)}步"
}