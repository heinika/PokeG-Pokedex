package com.heinika.pokeg.ui.gameprops.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.heinika.pokeg.ui.gameprops.mailIIIList
import com.heinika.pokeg.ui.gameprops.mailIIList
import com.heinika.pokeg.ui.gameprops.mailIVList
import com.heinika.pokeg.ui.gameprops.mailVList
import com.heinika.pokeg.utils.*

@ExperimentalMaterialApi
@Composable
fun MailPropsPage() {
  MailPropsColumn()
}

@Preview
@ExperimentalMaterialApi
@Composable
fun MailPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    item {
      GenerationCard(Generation.GenerationII)
    }
    items(mailIIList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }
    item {
      GenerationCard(Generation.GenerationIII)
    }
    items(mailIIIList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationIV)
    }
    items(mailIVList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      GenerationCard(Generation.GenerationV)
    }
    items(mailVList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }
  }
}