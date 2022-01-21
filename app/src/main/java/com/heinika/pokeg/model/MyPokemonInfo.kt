package com.heinika.pokeg.model

import android.content.Context
import com.heinika.pokeg.info.Generation
import com.heinika.pokeg.info.Nature
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.module.gameprops.props.CarryProps


data class MyPokemonInfo(
  val name: String,
  val id: Int,
  val gen:Generation,
  val typeIdList: List<Type>,
  val carry: CarryProps,
  val nature: Nature,
  val ability: Ability,
  val moveList: List<Move>
){
  fun toMyPokemon() = MyPokemon(
    name,
    id,
    gen.id,
    typeIdList.map { it.typeId },
    carry = carry.cname,
    natureId = nature.ordinal,
    abilityId = ability.num,
    moveIdList = moveList.map { it.id }
  )
}
