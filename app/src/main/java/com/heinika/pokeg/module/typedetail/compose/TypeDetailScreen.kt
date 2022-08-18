package com.heinika.pokeg.module.typedetail.compose


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import coil.annotation.ExperimentalCoilApi
import com.heinika.pokeg.PokemonDataCache.pokemonList
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.module.moves.compose.ChipStatus
import com.heinika.pokeg.module.moves.compose.SelectTwoTypeClipList
import com.heinika.pokeg.module.mypokemon.compose.PokemonCard
import com.heinika.pokeg.utils.SystemBar

@ExperimentalCoilApi
@ExperimentalMaterialApi

@Composable
fun TypeDetailScreen() {
  var curTypes by remember {
    mutableStateOf<List<Type>>(emptyList())
  }
  val typeChipsStatus = remember {
    mutableListOf<ChipStatus>().apply {
      repeat(Type.values().size) {
        add(ChipStatus.UnSelected)
      }
    }.toMutableStateList()
  }

  LazyColumn(modifier = Modifier.padding(top = Dp(SystemBar.statusBarHeightDp))) {
    item {
      SelectTwoTypeClipList(typeChipsStatus) {
        curTypes = it
      }
    }

    if (curTypes.isNotEmpty()) {
      item {
        TypeDetailTable(curTypes)
      }

      items(pokemonList.filter {
        it.types.contains(curTypes.first().typeId) && it.types.contains(curTypes.last().typeId)
      }) { pokemon ->
        PokemonCard(pokemon = pokemon, onclick = {

        })
      }

    }
  }
}



@ExperimentalCoilApi
@ExperimentalMaterialApi
@Preview
@Composable
fun TypeDetailScreenPreView() {
  TypeDetailScreen()
}
