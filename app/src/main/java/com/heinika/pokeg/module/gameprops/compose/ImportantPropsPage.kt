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

@Composable
fun ImportantPropsPage() {
  ImportantPropsColumn()
}

@Preview
@Composable
fun ImportantPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    item {
      GenerationCard(Generation.GenerationVIII)
    }
    items(importantVIIIList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationVII)
    }
    items(importantVIIList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationVI)
    }
    items(importantVIList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationV)
    }
    items(importantVList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationIV)
    }
    items(importantIVList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationIII)
    }
    items(importantIIIList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationII)
    }
    items(importantIIList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationI)
    }
    items(importantIList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }


  }
}