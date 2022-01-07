package com.heinika.pokeg.ui.gameprops.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.utils.Generation

@Composable
fun GenerationCard(gen: Generation) {
  Card(
    Modifier
      .padding(12.dp)
      .fillMaxWidth(), backgroundColor = colorResource(gen.resColor)
  ) {
    Text(
      text = stringResource(gen.resId) + "引入",
      Modifier
        .padding(8.dp)
        .fillMaxWidth(), textAlign = TextAlign.Center
    )
  }
}

@Composable
fun CategoryCard(text: String, color: Color,textColor:Color = Color.White) {
  Card(
    Modifier
      .padding(12.dp)
      .fillMaxWidth(), backgroundColor = color
  ) {
    Text(
      text = text, Modifier
        .padding(8.dp)
        .fillMaxWidth(), textAlign = TextAlign.Center, color = textColor
    )
  }
}