package com.heinika.pokeg.ui.gameprops.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.heinika.pokeg.R
import com.heinika.pokeg.ui.gameprops.props.fossilList
import com.heinika.pokeg.ui.gameprops.props.preciousPropList
import com.heinika.pokeg.ui.theme.grassColor
import com.heinika.pokeg.ui.theme.groundColor

@Preview
@ExperimentalMaterialApi
@Composable
fun PreciousPropsPage() {
  LazyColumn() {
    item {
      CategoryCard(text = stringResource(id = R.string.game_props_fossil), color = groundColor, textColor = Color.Black)
    }
    items(fossilList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }
    item {
      CategoryCard(text = stringResource(id = R.string.game_props_precious_thing), color = grassColor, textColor = Color.Black)
    }
    items(preciousPropList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }
  }
}
