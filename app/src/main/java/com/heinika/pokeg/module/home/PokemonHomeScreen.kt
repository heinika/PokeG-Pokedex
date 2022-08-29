package com.heinika.pokeg.module.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.heinika.pokeg.*
import com.heinika.pokeg.R
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.module.main.MainViewModel
import com.heinika.pokeg.module.mypokemon.compose.PokemonCard
import com.heinika.pokeg.ui.theme.*
import com.heinika.pokeg.utils.SystemBar

enum class DrawerScreens(val nameStringId: Int, val screenName: String, val color: Color) {
  VersionsScreen(R.string.version_list, VERSION_LIST_SCREEN, RelaxedColor),
  TypesDetailScreen(R.string.type_detail, TYPE_DETAIL_SCREEN, MildColor),
  AbilitiesScreen(R.string.abilities_detail, ABILITIES_SCREEN, HardyColor),
  MovesScreen(R.string.move_list, MOVES_SCREEN, BraveColor),
  GamePropsScreen(R.string.props_list, GAME_PROPS_SCREEN, QuietColor),
  NaturesScreen(R.string.nature_list, NATURE_SCREEN, CalmColor),
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun PokemonHomeScreen(mainViewModel: MainViewModel, onDrawerItemClick: (screenName: String) -> Unit, onPokemonItemClick: (Pokemon) -> Unit) {
  val sortedPokemonList = mainViewModel.pokemonSortListLiveData.observeAsState()
  Scaffold(drawerContent = {
    LazyColumn(modifier = Modifier.padding(top = SystemBar.statusBarHeightDp.dp)) {
      items(DrawerScreens.values()) {
        Card(modifier = Modifier
          .padding(12.dp, 6.dp)
          .height(42.dp)
          .fillMaxWidth(),
          shape = MaterialTheme.shapes.small,
          backgroundColor = it.color,
          onClick = { onDrawerItemClick(it.screenName) }) {
          Box {
            Text(
              text = stringResource(id = it.nameStringId),
              modifier = Modifier.align(Alignment.Center),
              textAlign = TextAlign.Center,
              style = MaterialTheme.typography.body1
            )
          }
        }
      }
    }
  }) { paddingValues ->
    paddingValues.calculateTopPadding()
    sortedPokemonList.value?.let {
      LazyColumn() {
        item {
          Row(modifier = Modifier.padding(top = SystemBar.statusBarHeightDp.dp)) {

          }
        }
        items(it) {
          PokemonCard(pokemon = it, onclick = { onPokemonItemClick(it) })
        }
      }
    }

  }
}