package com.heinika.pokeg.model

import com.heinika.pokeg.repository.res.PokemonRes
import com.heinika.pokeg.utils.getPokemonImageUrl
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
  @Json(name = "a")
  val id: Int,
  @Json(name = "b")
  val speciesId: Int,
  @Json(name = "c")
  val name: String,
  @Json(name = "d")
  val types: List<Int>,
  @Json(name = "e")
  val totalBaseStat: Int,
  @Json(name = "f")
  val hp: Int,
  @Json(name = "g")
  val atk: Int,
  @Json(name = "h")
  val def: Int,
  @Json(name = "i")
  val spAtk: Int,
  @Json(name = "j")
  val spDef: Int,
  @Json(name = "k")
  val speed: Int,
  @Json(name = "l")
  val generationId: Int,
  @Json(name = "m")
  val weight: Int,
  @Json(name = "n")
  val height: Int,
  @Json(name = "o")
  val isBaby: Boolean,
  @Json(name = "p")
  val isLegendary: Boolean,
  @Json(name = "q")
  val isMythical: Boolean,
  @Json(ignore = true)
  var isFavorite: Boolean = false
) {

//  fun getImageUrl(): String {
//    val index = url.split("/".toRegex()).dropLast(1).last()
//    return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
//  }

  fun getImageUrl(): String = getPokemonImageUrl(id, name)

  fun getCName(pokemonRes: PokemonRes): String = pokemonRes.getNameById(id, name)

  fun getFormatId(): String = String.format("#%03d", id)

  fun getFormatWeight(): String = String.format("%.1f KG", weight.toFloat() / 10)

  fun getFormatHeight(): String = String.format("%.1f M", height.toFloat() / 10)
}


