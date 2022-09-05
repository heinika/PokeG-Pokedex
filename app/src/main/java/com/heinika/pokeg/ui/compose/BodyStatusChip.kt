package com.heinika.pokeg.ui.compose

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.heinika.pokeg.info.BodyStatus

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BodyStatusChip(
  isSelected: Boolean,
  bodyStatus: BodyStatus,
  modifier: Modifier = Modifier,
  onClick: (BodyStatus) -> Unit,
) {
  BaseChip(
    chipStatus = isSelected,
    color = bodyStatus.color,
    modifier = modifier,
    onClick = { onClick(bodyStatus) },
    text = stringResource(id = bodyStatus.resId)
  )
}