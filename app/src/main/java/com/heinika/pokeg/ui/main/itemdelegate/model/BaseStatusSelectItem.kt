package com.heinika.pokeg.ui.main.itemdelegate.model

import com.heinika.pokeg.utils.PokemonProp

data class BaseStatusSelectItem(
  val baseStatus: PokemonProp.BaseStatus,
  val onClick: (name: PokemonProp.BaseStatus, isChecked: Boolean) -> Unit
)