package com.heinika.pokeg.model

import android.content.Context
import com.heinika.pokeg.info.Nature
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.utils.findCarryByName
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class TeamNumber(
  val id: Int,
  val typeIdList: List<Int>,
  val carry: String,
  val natureId: Int,
  val abilityId:Int,
  val moveIdList: List<Int>
) {
  fun toTeamNumberInfo(context: Context, allMoves: List<Move>, allAbilityList: List<Ability>): TeamNumberInfo {
    return TeamNumberInfo(
      id,
      typeIdList = typeIdList.map { Type.values()[it - 1] } ,
      carry = findCarryByName(carry, context),
      nature = Nature.values()[natureId],
      ability = allAbilityList.first { abilityId == it.num }.cname,
      moveList = moveIdList.map { moveId -> allMoves.first { it.id == moveId } },
    )
  }
}
