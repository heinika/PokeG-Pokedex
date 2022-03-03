package com.heinika.pokeg.model

import com.heinika.pokeg.info.Ability
import com.heinika.pokeg.info.Generation
import com.heinika.pokeg.info.Move
import com.heinika.pokeg.info.Nature
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.module.gameprops.props.CarryProps


data class MyPokemonInfo(
  var name: String,
  val id: Int,
  val gen: Generation,
  val typeIdList: List<Type>,
  val carry: CarryProps,
  var nature: Nature,
  val ability: Ability,
  val moveList: List<Move>,
  var teamNameList: List<String>
) {
  fun toMyPokemon() = MyPokemon(
    name,
    id,
    gen.id,
    typeIdList.map { it.typeId },
    carry = carry.cname,
    natureId = nature.ordinal,
    abilityId = ability.id,
    moveIdList = moveList.map { it.id },
    teamName = teamNameList.joinToString(";")
  )

  val formatId: String = String.format("%03d", id)

  var teamName:String = ""
}
