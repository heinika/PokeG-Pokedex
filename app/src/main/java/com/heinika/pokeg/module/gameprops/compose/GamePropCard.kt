package com.heinika.pokeg.module.gameprops.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GamePropCard(
  painter: Painter,
  title: String,
  content: String,
  color: Color = MaterialTheme.colorScheme.surface,
  textColor: Color = MaterialTheme.colorScheme.onSurface,
) {
  Card(
    modifier = Modifier
      .padding(12.dp, 6.dp)
      .fillMaxWidth()
      .height(90.dp)
      .fillMaxWidth(),
    colors = CardDefaults.cardColors(containerColor = color),
    onClick = {}
  ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Image(
        painter = painter, modifier = Modifier
          .size(80.dp)
          .padding(16.dp), contentDescription = ""
      )
      Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = textColor,
        modifier = Modifier.weight(1f)
      )
      Text(
        text = content,
        color = textColor,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
          .weight(3f)
          .padding(12.dp, 0.dp)
      )
    }
  }
}