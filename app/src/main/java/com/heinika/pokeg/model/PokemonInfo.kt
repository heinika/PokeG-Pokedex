package com.heinika.pokeg.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class PokemonInfo(
  @field:Json(name = "id") @PrimaryKey val id: Int,
  @field:Json(name = "name") val name: String,
  @field:Json(name = "height") val height: Int,
  @field:Json(name = "weight") val weight: Int,
  @field:Json(name = "base_experience") val experience: Int,
  @field:Json(name = "types") val types: List<TypeResponse>,
  @field:Json(name = "stats") val statResponses: List<StatResponse>,
  val hp: Int = statResponses[0].baseStat,
  val attack: Int = statResponses[1].baseStat,
  val defense: Int = statResponses[2].baseStat,
  val specialAttack: Int = statResponses[3].baseStat,
  val specialDefense: Int = statResponses[4].baseStat,
  val speed: Int = statResponses[5].baseStat
) {
  fun getIdString(): String = String.format("#%03d", id)
  fun getWeightString(): String = String.format("%.1f KG", weight.toFloat() / 10)
  fun getHeightString(): String = String.format("%.1f M", height.toFloat() / 10)
  fun getHpString(): String = "$hp/$maxHp"
  fun getAttackString(): String = "$attack/$maxAttack"
  fun getSpAttackString(): String = "$specialAttack/$maxSpecialAttack"
  fun getDefenseString(): String = "$defense/$maxDefense"
  fun getSpDefenseString(): String = "$specialDefense/$maxSpecialDefense"
  fun getSpeedString(): String = "$speed/$maxSpeed"
  fun getTotalStat():Int = hp + attack + defense + specialAttack + specialDefense + speed

  @JsonClass(generateAdapter = true)
  data class TypeResponse(
    @field:Json(name = "slot") val slot: Int,
    @field:Json(name = "type") val type: Type
  )

  @JsonClass(generateAdapter = true)
  data class StatResponse(
    @field:Json(name = "base_stat") val baseStat: Int
  )

  @JsonClass(generateAdapter = true)
  data class Type(
    @field:Json(name = "name") val name: String
  )

  companion object {
    const val maxHp = 200f
    const val maxAttack = 200f
    const val maxDefense = 200f
    const val maxSpecialAttack = 200f
    const val maxSpecialDefense = 200f
    const val maxSpeed = 200f
  }
}
