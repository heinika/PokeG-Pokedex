package com.heinika.pokeg.ui.moves.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.ui.theme.BlackColor
import com.heinika.pokeg.ui.theme.DisableColor

@ExperimentalMaterialApi
@Composable
fun BaseChip(
  chipStatus: ChipStatus,
  color: Color,
  modifier: Modifier,
  onClick: () -> Unit,
  text: String
) {
  Card(
    backgroundColor = when (chipStatus) {
      ChipStatus.Selected -> color
      ChipStatus.UnSelected -> Color.Transparent
      ChipStatus.Disable -> Color.Transparent
    },
    contentColor = when (chipStatus) {
      ChipStatus.Selected -> BlackColor
      ChipStatus.UnSelected -> color
      ChipStatus.Disable -> DisableColor
    },
    shape = CircleShape,
    border = BorderStroke(
      width = 1.dp,
      color = when (chipStatus) {
        ChipStatus.Selected -> color
        ChipStatus.UnSelected -> color
        ChipStatus.Disable -> DisableColor
      }
    ),
    modifier = modifier,
    onClick = { onClick() }
  ) {
    Text(
      text = text,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.body2,
      modifier = Modifier.padding(8.dp)
    )

  }
}