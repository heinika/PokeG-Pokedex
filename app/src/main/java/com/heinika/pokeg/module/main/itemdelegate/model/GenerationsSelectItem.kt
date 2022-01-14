package com.heinika.pokeg.module.main.itemdelegate.model

import com.heinika.pokeg.info.Generation

data class GenerationsSelectItem(
  val generation: Generation,
  val onClick: (name: Generation, isChecked: Boolean) -> Unit
)