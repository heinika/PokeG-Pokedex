package com.heinika.pokeg.ui.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.ui.theme.BlackColor


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseChip(
  chipStatus: Boolean,
  color: Color,
  modifier: Modifier,
  onClick: () -> Unit,
  text: String
) {
  val backgroundColor = when (chipStatus) {
    true -> color
    false -> Color.Transparent
  }

  val contentColor = when (chipStatus) {
    true -> BlackColor
    false -> color
  }
  Card(
    colors = CardDefaults.cardColors(containerColor = backgroundColor, contentColor = contentColor),
    shape = CircleShape,
    border = BorderStroke(
      width = 1.dp,
      color = when (chipStatus) {
        true -> color
        false -> color
      }
    ),
    modifier = modifier,
    onClick = { onClick() }
  ) {
    Text(
      text = text,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.bodyMedium,
      modifier = Modifier.padding(8.dp).fillMaxWidth()
    )

  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseChip(
  chipStatus: ChipStatus,
  color: Color,
  modifier: Modifier,
  onClick: () -> Unit,
  text: String
) {
  val backgroundColor = when (chipStatus) {
    ChipStatus.Selected -> color
    ChipStatus.UnSelected -> Color.Transparent
    ChipStatus.Disable -> Color.Transparent
  }
  val contentColor = when (chipStatus) {
    ChipStatus.Selected -> BlackColor
    ChipStatus.UnSelected -> color
    ChipStatus.Disable -> MaterialTheme.colorScheme.errorContainer
  }
  Card(
    colors = CardDefaults.cardColors(containerColor = backgroundColor, contentColor = contentColor) ,
    shape = CircleShape,
    border = BorderStroke(
      width = 1.dp,
      color = when (chipStatus) {
        ChipStatus.Selected -> color
        ChipStatus.UnSelected -> color
        ChipStatus.Disable -> MaterialTheme.colorScheme.errorContainer
      }
    ),
    modifier = modifier,
    onClick = { onClick() }
  ) {
    Text(
      text = text,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.bodyMedium,
      modifier = Modifier.padding(8.dp).fillMaxWidth()
    )

  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseImageChip(
  isSelected: Boolean,
  color: Color,
  modifier: Modifier,
  onClick: () -> Unit,
  text: String,
  painter: Painter
) {
  val backgroundColor = if (isSelected) color else Color.Transparent
  val contentColor = if (isSelected) BlackColor else color
  Card(
    colors = CardDefaults.cardColors(containerColor = backgroundColor,contentColor = contentColor),
    shape = CircleShape,
    border = BorderStroke(
      width = 1.dp,
      color = color
    ),
    modifier = modifier,
    onClick = { onClick() }
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.Center
    ) {
      Image(painter = painter, contentDescription = "")

      Text(
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(8.dp)
      )
    }

  }
}