package com.heinika.pokeg.ui.compose

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.heinika.pokeg.info.BaseStatus

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BaseStatusChip(
  isSelected: Boolean,
  baseStatus: BaseStatus,
  modifier: Modifier = Modifier,
  onClick: (BaseStatus) -> Unit,
) {
  BaseChip(
    chipStatus = isSelected,
    color = baseStatus.color,
    modifier = modifier,
    onClick = { onClick(baseStatus) },
    text = stringResource(id = baseStatus.resId)
  )
}