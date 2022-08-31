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
import com.heinika.pokeg.utils.SystemBar

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun TypeDetailScreen(types: List<Type>, onPokemonClick: (pokemonId: Int) -> Unit, onTypesChange: (List<Type>) -> Unit) {
  val typeChipsStatus = remember {
    mutableStateListOf<ChipStatus>().apply {
      Type.values().dropLast(1).forEach {
        add(if (types.contains(it)) ChipStatus.Selected else ChipStatus.UnSelected)
      }
    }
  }

  LazyColumn(modifier = Modifier.padding(top = Dp(SystemBar.statusBarHeightDp))) {
    item {
      SelectTwoTypeClipList(typeChipsStatus) { types ->
        onTypesChange(types)
      }
    }

    if (types.isNotEmpty()) {
      item {
        TypeDetailTable(types)
      }

      items(pokemonList.filter {
        it.types.contains(types.first().typeId) && it.types.contains(types.last().typeId)
      }) { pokemon ->
        PokemonCard(pokemon = pokemon, onClick = {
          onPokemonClick(it.globalId)
        })
      }
    }

  }
}
