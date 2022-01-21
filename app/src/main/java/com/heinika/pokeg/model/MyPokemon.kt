package com.heinika.pokeg.model

import android.content.Context
import com.heinika.pokeg.info.Generation
import com.heinika.pokeg.info.Nature
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.utils.findCarryByCName
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MyPokemon(
  val name: String,
  val id: Int,
  val genId: Int,
  val typeIdList: List<Int>,
  val carry: String,
  val natureId: Int,
  val abilityId: Int,
  val moveIdList: List<Int>
) {
  fun toMyPokemonInfo(
    allMoves: List<Move>,
    allAbilityList: List<Ability>
  ): MyPokemonInfo {
    return MyPokemonInfo(
      name,
      id,
      Generation.values()[genId -1],
      typeIdList = typeIdList.map { Type.values()[it - 1] },
      carry = findCarryByCName(carry),
      nature = Nature.values()[natureId],
      ability = allAbilityList.first { abilityId == it.num },
      moveList = moveIdList.map { moveId -> allMoves.first { it.id == moveId } },
    )
  }
}
