package com.heinika.pokeg.module.gameprops.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.heinika.pokeg.R
import com.heinika.pokeg.module.gameprops.props.EvolutionProp
import com.heinika.pokeg.module.gameprops.props.evolutionPropList
import com.heinika.pokeg.ui.theme.fireColor

@Preview
@Composable
fun EvolutionPropsCard(props: EvolutionProp =  EvolutionProp(
  fireColor,
  R.string.Fire_Stone,
  R.string.Fire_Stone_flavor,
  R.drawable.evolution_prop_1
)
) {
  GamePropCard(
    painterResource(id = props.imageResId), stringResource(props.nameResId),
    stringResource(props.flavorResId), props.color, Color.Black
  )
}

@Preview
@Composable
fun EvolutionPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(evolutionPropList) {
      EvolutionPropsCard(it)
    }
  }
}


