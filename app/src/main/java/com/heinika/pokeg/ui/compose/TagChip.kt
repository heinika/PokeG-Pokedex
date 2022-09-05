package com.heinika.pokeg.ui.compose

import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.info.Tag

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TagChip(
  isSelected: Boolean,
  tag: Tag,
  modifier: Modifier = Modifier,
  onClick: (Tag) -> Unit,
) {
  BaseChip(
    chipStatus = isSelected,
    color = tag.color,
    modifier = modifier.width(80.dp),
    onClick = { onClick(tag) },
    text = stringResource(id = tag.resId)
  )
}