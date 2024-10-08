package com.heinika.pokeg.module.gameprops.compose

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.heinika.pokeg.module.gameprops.props.*


@Preview
@Composable
fun PokeballPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(pokeballList) {
      GamePropCard(painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId))
    }
  }
}

@Preview
@Composable
fun SwapPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(swapPropList) {
      GamePropCard(painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId))
    }
  }
}

@Preview
@Composable
fun ApricornPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(apricornList) {
      GamePropCard(painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId))
    }
  }
}

@Preview
@Composable
fun BattlePropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(battlePropList) {
      GamePropCard(painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId))
    }
  }
}


@Preview
@Composable
fun RecoveryPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(recoveryPropList) {
      GamePropCard(painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId))
    }
  }
}


@Preview
@Composable
fun RotoPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(rotoList) {
      GamePropCard(painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId))
    }
  }
}

@Preview
@Composable
fun CandyPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(candyPropList) {
      GamePropCard(painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId))
    }
  }
}

@Preview
@Composable
fun TCGPropsColumn(modifier: Modifier = Modifier) {
    LazyColumn(modifier.fillMaxHeight()) {
      items(tcgPropList) {
        GamePropCard(painterResource(id = it.imageResId), stringResource(it.nameResId),
          stringResource(it.flavorResId))
      }
    }
}

@Preview
@Composable
fun FoodPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(foodList) {
      GamePropCard(painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId))
    }
  }
}

@Preview
@Composable
fun FieldPropsColumn(modifier: Modifier = Modifier) {
  LazyColumn(modifier) {
    items(fieldPropList) {
      GamePropCard(painterResource(id = it.imageResId), stringResource(it.nameResId),
        stringResource(it.flavorResId))
    }
  }
}