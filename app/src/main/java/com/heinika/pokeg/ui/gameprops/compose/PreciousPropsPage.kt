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
import com.heinika.pokeg.R
import com.heinika.pokeg.utils.fossilList
import com.heinika.pokeg.utils.preciousPropList

@Preview
@ExperimentalMaterialApi
@Composable
fun FossilPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(fossilList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }
  }
}

@Preview
@ExperimentalMaterialApi
@Composable
fun PreciousPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(preciousPropList) {
      GamePropCard(
        painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId)
      )
    }
  }
}

@Preview
@ExperimentalMaterialApi
@Composable
fun PreciousPropsPage(){
  Text(text = stringResource(id = R.string.game_props_fossil),Modifier.padding(12.dp))
  FossilPropsColumn()
  Text(text = stringResource(id = R.string.game_props_precious_thing),Modifier.padding(12.dp))
  PreciousPropsColumn()
}
