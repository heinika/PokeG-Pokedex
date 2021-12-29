package com.heinika.pokeg.ui.gameprops.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.utils.EvolutionProps

@ExperimentalMaterialApi
@Preview
@Composable
fun EvolutionPropsCard(props: EvolutionProps = EvolutionProps.FireStone) {
  Card(
    modifier = Modifier
      .padding(12.dp)
      .fillMaxWidth()
      .height(90.dp)
      .fillMaxWidth(),
    backgroundColor = props.color,
    onClick = {}
  ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Image(
        painter = painterResource(id = props.imageResId), modifier = Modifier
          .size(80.dp)
          .padding(16.dp), contentDescription = ""
      )
      Text(
        text = stringResource(id = props.nameResId),
        style = MaterialTheme.typography.h5,
        color = Color.Black,
        modifier = Modifier.weight(1f)
      )
      Text(
        text = stringResource(id = props.flavorResId),
        color = Color.Black,
        modifier = Modifier.weight(3f).padding(12.dp,0.dp)
      )
    }
  }
}

@Preview
@ExperimentalMaterialApi
@Composable
fun EvolutionPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(EvolutionProps.values()) {
      EvolutionPropsCard(it)
    }
  }
}
