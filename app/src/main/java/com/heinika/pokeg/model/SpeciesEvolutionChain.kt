package com.heinika.pokeg.model


import com.heinika.pokeg.repository.res.PokemonRes
import com.heinika.pokeg.utils.getPokemonImageUrl
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
  val genderId: Int,
  @Json(name = "held_item_id")
  val heldItemId: Int,
  @Json(name = "id")
  val id: Int,
  @Json(name = "known_move_id")
  val knownMoveId: Int,
  @Json(name = "known_move_type_id")
  val knownMoveTypeId: Int,
  @Json(name = "location_id")
  val locationId: Int,
  @Json(name = "minimum_affection")
  val minimumAffection: Int,
  @Json(name = "minimum_beauty")
  val minimumBeauty: Int,
  @Json(name = "minimum_happiness")
  val minimumHappiness: Int,
  @Json(name = "minimum_level")
  val minimumLevel: Int,
  @Json(name = "needs_overworld_rain")
  val needsOverworldRain: Int,
  @Json(name = "party_species_id")
  val partySpeciesId: Int,
  @Json(name = "party_type_id")
  val partyTypeId: Int,
  @Json(name = "relative_physical_stats")
  val relativePhysicalStats: Int,
  @Json(name = "time_of_day")
  val timeOfDay: String,
  @Json(name = "trade_species_id")
  val tradeSpeciesId: Int,
  @Json(name = "trigger_item_id")
  val triggerItemId: Int,
  @Json(name = "turn_upside_down")
  val turnUpsideDown: Int
) {
  fun getSpeciesFromImageUrl() = getPokemonImageUrl(evolvedFromSpeciesId, evolvedFromName)

  fun getSpeciesToImageUrl() = getPokemonImageUrl(evolvedToSpeciesId, evolvedToName)

  fun getDescText(pokemonRes: PokemonRes): String {
    return when (evolutionTriggerId) {
      1 -> {
        when {
          minimumLevel != -1 -> "${minimumLevel}级${timeOfDay.dayTimeToCn}进化"
          minimumHappiness != -1 -> "亲密度超过${minimumHappiness}${timeOfDay.dayTimeToCn}升级进化"
          minimumBeauty != -1 -> "美丽值超过${minimumBeauty}${timeOfDay.dayTimeToCn}升级进化"
          minimumAffection != -1 -> "友好度超过${minimumAffection}${timeOfDay.dayTimeToCn}升级进化"
          else -> "升级进化"
        }
      }
      2 -> if (tradeSpeciesId == -1) "交换进化" else "和${pokemonRes.getNameById(tradeSpeciesId, "")}交换进化"
      3 -> "使用${pokemonRes.getItemById(triggerItemId)}进化"
      4 -> "20级，包包中有精灵球，且同行宝可梦有空位"
      else -> "未知"
    }

  }

  val String.dayTimeToCn: String
    get() = when (this) {
      "day" -> "白天"
      "night" -> "黑夜"
      else -> ""
    }

}