package com.heinika.pokeg.model

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
//      typeIdList = typeIdList.map { Type.values()[i - 1] },
//      carry = findCarryByName(),
//      nature = ,
//      moveIdList: ,
//    )
//  }
}
