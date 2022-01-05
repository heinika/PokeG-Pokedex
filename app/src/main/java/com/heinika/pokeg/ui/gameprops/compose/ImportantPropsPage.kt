package com.heinika.pokeg.ui.gameprops.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.heinika.pokeg.ui.gameprops.*
import com.heinika.pokeg.utils.*

@ExperimentalMaterialApi
@Composable
fun ImportantPropsPage() {
  ImportantPropsColumn()
}

@Preview
@ExperimentalMaterialApi
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
  }
}