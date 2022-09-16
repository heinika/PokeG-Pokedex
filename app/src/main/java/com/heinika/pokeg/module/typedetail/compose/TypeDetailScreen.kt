package com.heinika.pokeg.module.typedetail.compose


import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import coil.annotation.ExperimentalCoilApi
import com.heinika.pokeg.ConfigMMKV
import com.heinika.pokeg.PokemonDataCache.pokemonList
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.ui.compose.ChipStatus
import com.heinika.pokeg.ui.compose.PokemonCard
import com.heinika.pokeg.ui.compose.SelectTwoTypeClipList
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
  val favouritePokemonsState = remember { mutableStateListOf<String>().apply { addAll(ConfigMMKV.favoritePokemons) } }

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

      val filterList = pokemonList.filter {
        it.types.contains(types.first().typeId) && it.types.contains(types.last().typeId)
      }
      itemsIndexed(filterList) { index,pokemon ->
        PokemonCard(
          pokemon = pokemon,
          onClick = {
            onPokemonClick(it.globalId)
          },
          isPaddingBottom = index == filterList.size - 1,
          isFavourite = favouritePokemonsState.contains(pokemon.globalId.toString()),
          onFavouriteClick = {
            ConfigMMKV.favoritePokemons = if (ConfigMMKV.isFavoritePokemon(it.globalId)) {
              favouritePokemonsState.remove(it.globalId.toString())
              ConfigMMKV.favoritePokemons - it.globalId.toString()
            } else {
              favouritePokemonsState.add(it.globalId.toString())
              ConfigMMKV.favoritePokemons + it.globalId.toString()
            }
          }
        )
      }
    }

  }
}
