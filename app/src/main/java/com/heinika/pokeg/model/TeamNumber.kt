package com.heinika.pokeg.model

import com.heinika.pokeg.utils.PokemonProp
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class TeamNumber(
  val id: Int,
  val typeIdList: List<Int>,
  val carry: String,
  val nature: Int,
  val moveIdList: List<Int>
) {
//  fun toTeamNumberInfo(): TeamNumberInfo {
//    return TeamNumberInfo(
//      id,
//      typeIdList = typeIdList.map { PokemonProp.Type.values()[id - 1] },
//      carry = findCarryByName(),
//      nature = ,
//      moveIdList: ,
//    )
//  }
}
