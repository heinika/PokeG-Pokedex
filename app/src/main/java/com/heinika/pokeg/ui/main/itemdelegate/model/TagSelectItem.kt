package com.heinika.pokeg.ui.main.itemdelegate.model

import com.heinika.pokeg.utils.PokemonProp

data class TagSelectItem(
  val tag: PokemonProp.Tag,
  val onClick: (tag: PokemonProp.Tag, isChecked: Boolean) -> Unit
)