package com.heinika.pokeg.model

import com.heinika.pokeg.repository.res.PokemonRes
import com.heinika.pokeg.utils.getPokemonImageUrl

data class Pokemon(
  val id: Int,
  val speciesId: Int,
  val name: String,
  val types: List<PokemonType>,
  val totalBaseStat: Int,
  val hp: Int,
  val atk: Int,
  val def: Int,
  val spAtk: Int,
  val spDef: Int,
  val speed: Int,
  val generationId: Int,
  val weight: Int,
  val height: Int
) {

//  fun getImageUrl(): String {
//    val index = url.split("/".toRegex()).dropLast(1).last()
//    return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
//  }

  fun getImageUrl(): String = getPokemonImageUrl(id, name)

  fun getCName(pokemonRes: PokemonRes): String = pokemonRes.getNameById(id, name)

  fun getIdString(): String = String.format("#%03d", id)
}


