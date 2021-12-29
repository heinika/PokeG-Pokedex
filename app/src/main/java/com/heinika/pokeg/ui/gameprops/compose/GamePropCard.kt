package com.heinika.pokeg.ui.gameprops.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun GamePropCard(
  painter: Painter,
  title: String,
  content: String,
  color: Color = MaterialTheme.colors.surface,
  textColor: Color = MaterialTheme.colors.onSurface,
) {
  Card(
    modifier = Modifier
      .padding(12.dp, 6.dp)
      .fillMaxWidth()
      .height(90.dp)
      .fillMaxWidth(),
    backgroundColor = color,
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
        style = MaterialTheme.typography.subtitle1,
        color = textColor,
        modifier = Modifier.weight(1f)
      )
      Text(
        text = content,
        color = textColor,
        style = MaterialTheme.typography.body2,
        modifier = Modifier
          .weight(3f)
          .padding(12.dp, 0.dp)
      )
    }
  }
}