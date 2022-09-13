package com.heinika.pokeg.module.gameprops.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.heinika.pokeg.info.Generation
import com.heinika.pokeg.module.gameprops.props.*
import com.heinika.pokeg.ui.theme.Red200

@Composable
fun BerryPropsPage() {
  BerryPropsColumn()
}

@Preview
@Composable
fun BerryPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    item {
      GenerationCard(Generation.GenerationVII)
    }
    items(berry7PropList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationVI)
    }
    items(berry6PropList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationIV)
    }
    items(berry4PropList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationIII)
    }
    items(berry3PropList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      CategoryCard("只在《红宝石／蓝宝石》", Red200)
    }
    items(berry3OnlyRedBluePropList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }
  }
}

