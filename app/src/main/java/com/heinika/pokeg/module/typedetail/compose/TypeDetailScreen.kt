package com.heinika.pokeg.module.typedetail.compose


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import coil.annotation.ExperimentalCoilApi
import com.heinika.pokeg.PokemonDataCache.pokemonList
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.module.moves.compose.ChipStatus
import com.heinika.pokeg.module.moves.compose.SelectTwoTypeClipList
import com.heinika.pokeg.module.mypokemon.compose.PokemonCard
import com.heinika.pokeg.module.typedetail.TypeDetailScreenViewModel
import com.heinika.pokeg.utils.SystemBar

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun TypeDetailScreen(typeDetailScreenViewModel: TypeDetailScreenViewModel, onPokemonClick: (pokemonId:Int) -> Unit) {
  val typeChipsStatus = remember {
    mutableStateListOf<ChipStatus>().apply {
      Type.values().dropLast(1).forEach {
        add(if (typeDetailScreenViewModel.curTypes.contains(it)) ChipStatus.Selected else ChipStatus.UnSelected)
      }
    }
  }

  LazyColumn(modifier = Modifier.padding(top = Dp(SystemBar.statusBarHeightDp))) {
    item {
      SelectTwoTypeClipList(typeChipsStatus) { types ->
        typeDetailScreenViewModel.curTypes = types
      }
    }

    if (typeDetailScreenViewModel.curTypes.isNotEmpty()) {
      item {
        TypeDetailTable(typeDetailScreenViewModel.curTypes)
      }

      items(pokemonList.filter {
        it.types.contains(typeDetailScreenViewModel.curTypes.first().typeId) && it.types.contains(typeDetailScreenViewModel.curTypes.last().typeId)
      }) { pokemon ->
        PokemonCard(pokemon = pokemon, onclick = {
          onPokemonClick(it.globalId)
        })
      }
    }

  }
}
