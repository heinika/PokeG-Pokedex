package com.heinika.pokeg.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.heinika.pokeg.info.Generation
import com.heinika.pokeg.info.Move
import com.heinika.pokeg.info.Nature
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.utils.findCarryByCName
import com.squareup.moshi.Json

@Entity
data class MyPokemon(
  @PrimaryKey var name: String,
  val id: Int,
  val genId: Int = 8,
  val typeIdList: List<Int>,
  val carry: String,
  val natureId: Int,
  val abilityId: Int,
  val moveIdList: List<Int>,
  var teamName: String = ""
) {
  fun toMyPokemonInfo(
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
      moveList = moveIdList.map { moveId -> Move.values().first { it.id == moveId } },
      teamNameList = teamName.split(";")
    )
  }
}
