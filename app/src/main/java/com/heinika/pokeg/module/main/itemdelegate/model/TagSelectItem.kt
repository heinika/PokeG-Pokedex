package com.heinika.pokeg.module.main.itemdelegate.model

import com.heinika.pokeg.info.Tag


data class TagSelectItem(
  val tag: Tag,
  val onClick: (tag: Tag, isChecked: Boolean) -> Unit
)