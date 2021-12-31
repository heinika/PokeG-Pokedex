package com.heinika.pokeg.ui.gameprops.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.utils.*

@ExperimentalMaterialApi
@Composable
fun CarryPropsPage() {
  CarryPropsColumn()
}

@Preview
@ExperimentalMaterialApi
@Composable
fun CarryPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    item {
      GenCard(Generation.GenerationII)
    }
    items(carryIIPropsList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }
    item {
      GenCard(Generation.GenerationIII)
    }
    items(carryIIIPropsList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenCard(Generation.GenerationIV)
    }
    items(carryIVPropsList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenCard(Generation.GenerationV)
    }
    items(carryVPropsList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenCard(Generation.GenerationVI)
    }
    items(carryVIPropsList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenCard(Generation.GenerationVII)
    }
    items(carryVIIPropsList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenCard(Generation.GenerationVIII)
    }
    items(carryVIIIPropsList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }
  }
}

@Composable
private fun GenCard(gen: Generation) {
  Card(Modifier.padding(12.dp),backgroundColor = colorResource(gen.resColor)) {
    Text(text = stringResource(gen.resId) + "引入",Modifier.padding(8.dp))
  }
}