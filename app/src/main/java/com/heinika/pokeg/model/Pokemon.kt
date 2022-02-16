package com.heinika.pokeg.model

import com.heinika.pokeg.curDexType
import com.heinika.pokeg.info.DexType
import com.heinika.pokeg.info.RegionNumber.HiSuiMap
import com.heinika.pokeg.repository.res.PokemonRes
import com.heinika.pokeg.utils.getPokemonImageUrl
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon(
  val id: Int,
  @Json(name = "a")
  val globalId: Int,
  @Json(name = "b")
  val speciesId: Int,
  @Json(name = "c")
  val name: String,
  @Json(name = "d")
  val types: List<Int>,
  @Json(name = "e")
  var totalBaseStat: Int = -1,
  @Json(name = "f")
  var hp: Int = -1,
  @Json(name = "g")
  var atk: Int = -1,
  @Json(name = "h")
  var def: Int = -1,
  @Json(name = "i")
  var spAtk: Int = -1,
  @Json(name = "j")
  var spDef: Int = -1,
  @Json(name = "k")
  var speed: Int = -1,
  @Json(name = "l")
  var generationId: Int = -1,
  @Json(name = "m")
  var weight: Int = -1,
  @Json(name = "n")
  var height: Int = -1,
  @Json(name = "o")
  val form: Int,
  @Json(ignore = true)
  var isBaby: Boolean = false,
  @Json(ignore = true)
  var isLegendary: Boolean = false,
  @Json(ignore = true)
  var isMythical: Boolean = false,
  @Json(ignore = true)
  var isFavorite: Boolean = false
) {

//  fun getImageUrl(): String {
//    val index = url.split("/".toRegex()).dropLast(1).last()
//    return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
//  }

  fun getImageUrl(): String = getPokemonImageUrl(globalId, name)

  fun getCName(pokemonRes: PokemonRes): String = pokemonRes.getNameById(id, name)

  fun getFormatId(): String {
    return when (curDexType) {
      DexType.Global -> String.format("#%03d", id)
      DexType.HiSui -> String.format("#%03d", HiSuiMap[globalId] ?: globalId)
    }
  }

  fun getFormatWeight(): String = String.format("%.1f KG", weight.toFloat() / 10)

  fun getFormatHeight(): String = String.format("%.1f M", height.toFloat() / 10)
}


