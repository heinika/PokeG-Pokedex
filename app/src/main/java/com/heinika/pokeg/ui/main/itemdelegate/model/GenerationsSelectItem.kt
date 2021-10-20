package com.heinika.pokeg.ui.main.itemdelegate.model

import com.heinika.pokeg.utils.PokemonProp

data class GenerationsSelectItem(
  val generation: PokemonProp.Generation,
  val onClick: (name: PokemonProp.Generation, isChecked: Boolean) -> Unit
)