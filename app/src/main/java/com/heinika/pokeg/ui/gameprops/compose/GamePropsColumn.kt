package com.heinika.pokeg.ui.gameprops.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.utils.*


@Preview
@ExperimentalMaterialApi
@Composable
fun PokeballPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(Pokeball.values()) {
      GamePropCard(painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId))
    }
  }
}

@Preview
@ExperimentalMaterialApi
@Composable
fun FossilPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(Fossil.values()) {
      GamePropCard(painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId))
    }
  }
}

@Preview
@ExperimentalMaterialApi
@Composable
fun SwapPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(SwapProps.values()) {
      GamePropCard(painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId))
    }
  }
}

