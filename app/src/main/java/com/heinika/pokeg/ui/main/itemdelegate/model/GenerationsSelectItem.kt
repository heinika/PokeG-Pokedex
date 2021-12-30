package com.heinika.pokeg.ui.main.itemdelegate.model

import com.heinika.pokeg.utils.Generation

data class GenerationsSelectItem(
  val generation: Generation,
  val onClick: (name: Generation, isChecked: Boolean) -> Unit
)