package com.heinika.pokeg.model

import com.heinika.pokeg.info.Type
import com.heinika.pokeg.module.gameprops.props.CarryProps


data class TeamNumberInfo(
  val id: Int,
  val typeIdList: List<Type>,
  val carry: CarryProps,
  val nature: Nature,
  val moveIdList: List<Move>
)
