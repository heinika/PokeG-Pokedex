package com.heinika.pokeg.module.main.itemdelegate.model

import com.heinika.pokeg.info.BaseStatus

data class BaseStatusSelectItem(
  val baseStatus: BaseStatus,
  val onClick: (name: BaseStatus, isChecked: Boolean) -> Unit
)