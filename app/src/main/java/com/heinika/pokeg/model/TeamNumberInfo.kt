package com.heinika.pokeg.model

import com.heinika.pokeg.ui.gameprops.props.CarryProps
import com.heinika.pokeg.utils.PokemonProp

data class TeamNumberInfo(
  val id: Int,
  val typeIdList: List<PokemonProp.Type>,
  val carry: CarryProps,
  val nature: Nature,
  val moveIdList: List<Move>
)
