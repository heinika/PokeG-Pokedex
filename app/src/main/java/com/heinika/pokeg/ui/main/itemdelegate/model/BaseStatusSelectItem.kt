package com.heinika.pokeg.ui.main.itemdelegate.model

import com.heinika.pokeg.utils.BaseStatus

data class BaseStatusSelectItem(
  val baseStatus: BaseStatus,
  val onClick: (name: BaseStatus, isChecked: Boolean) -> Unit
)