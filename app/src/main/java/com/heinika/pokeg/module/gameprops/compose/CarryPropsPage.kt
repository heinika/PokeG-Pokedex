package com.heinika.pokeg.module.gameprops.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.heinika.pokeg.info.Generation
import com.heinika.pokeg.module.gameprops.props.*


@Composable
fun CarryPropsPage() {
  CarryPropsColumn()
}

@Preview
@Composable
fun CarryPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    item {
      GenerationCard(Generation.GenerationVIII)
    }
    items(carryVIIIPropsList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationVII)
    }
    items(carryVIIPropsList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationVI)
    }
    items(carryVIPropsList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationV)
    }
    items(carryVPropsList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationIV)
    }
    items(carryIVPropsList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationIII)
    }
    items(carryIIIPropsList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationII)
    }
    items(carryIIPropsList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }
  }
}

