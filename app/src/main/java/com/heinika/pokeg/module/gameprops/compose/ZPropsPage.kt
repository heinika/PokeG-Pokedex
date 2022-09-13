package com.heinika.pokeg.module.gameprops.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.heinika.pokeg.module.gameprops.props.zMasterList
import com.heinika.pokeg.module.gameprops.props.zPokemonList
import com.heinika.pokeg.ui.theme.Purple500
import com.heinika.pokeg.ui.theme.Red200

@Composable
fun ZPropsPage() {
  ZPropsColumn()
}

@Preview
@Composable
fun ZPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    item {
      CategoryCard("训练家使用的Ｚ纯晶", Red200)
    }
    items(zMasterList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }

    item {
      CategoryCard("宝可梦使用的Ｚ纯晶", Purple500)
    }
    items(zPokemonList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }
  }
}

