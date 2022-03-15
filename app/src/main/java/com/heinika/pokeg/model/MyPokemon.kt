package com.heinika.pokeg.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.heinika.pokeg.info.*
import com.heinika.pokeg.module.gameprops.props.CarryProps
import com.heinika.pokeg.utils.findCarryByCName

@Entity
data class MyPokemon(
  var name: String,
  val id: Int,
  val genId: Int = 8,
  val typeIdList: List<Int>,
  val carry: String,
  var natureId: Int,
  val abilityId: Int,
  val moveIdList: List<Int>,
  var teamName: String = "",
  @PrimaryKey(autoGenerate = true)
  var myPokemonId: Int = 0
) {

  @Ignore
  var nature: Nature = Nature.values()[natureId]

  @Ignore
  val gen = Generation.values()[genId - 1]

  @Ignore
  val formatId: String = String.format("%03d", id)

  @Ignore
  var teamNameList = teamName.split(";")

  @Ignore
  val carryProps: CarryProps = findCarryByCName(carry)

  @Ignore
  val typeList = typeIdList.map { typeId ->
    Type.values().first { it.typeId == typeId }
  }

  val moveList: List<Move>
    get() = moveIdList.map { id ->
      Move.values()[id - 1]
    }

  val ability: Ability
    get() = Ability.values()[abilityId - 1]
}
