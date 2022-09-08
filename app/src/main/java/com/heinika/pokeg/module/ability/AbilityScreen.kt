package com.heinika.pokeg.module.ability

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.heinika.pokeg.ConfigMMKV
import com.heinika.pokeg.PokemonDataCache
import com.heinika.pokeg.info.Ability
import com.heinika.pokeg.module.mypokemon.compose.PokemonCard
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.SystemBar
import com.heinika.pokeg.utils.formatId

@ExperimentalMaterialApi
@OptIn(ExperimentalCoilApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AbilityScreen(ability: Ability, onBack: () -> Unit, onPokemonClick: (pokemonId: Int) -> Unit, abilityViewModel: AbilityViewModel) {
  val pokemonList = abilityViewModel.findPokemonListByAbilityId(ability.id).map { pokemonId ->
    PokemonDataCache.pokemonList.first { it.globalId == pokemonId }
  }
  val favouritePokemonsState = remember { mutableStateListOf<String>().apply { addAll(ConfigMMKV.favoritePokemons) } }

  LazyColumn {
    item {
      Column {
        SmallTopAppBar(
          navigationIcon = {
            IconButton(onClick = {
              onBack()
            }) {
              Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
          },
          title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(text = stringResource(id = ability.nameResId), style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
              Text(text = ability.id.formatId, style = MaterialTheme.typography.titleSmall, modifier = Modifier.padding(end = 24.dp))
            }
          },
          colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent),
          modifier = Modifier
            .padding(top = SystemBar.statusBarHeightDp.dp, start = 12.dp, end = 12.dp)
            .shadow(4.dp, MaterialTheme.shapes.small)
        )
        Text(
          text = stringResource(id = ability.flavorResId),
          style = MaterialTheme.typography.bodyMedium,
          modifier = Modifier.padding(24.dp, 12.dp)
        )
        Text(
          text = stringResource(id = ability.effectResId),
          style = MaterialTheme.typography.bodyMedium,
          modifier = Modifier.padding(24.dp, 0.dp)
        )
      }
    }

    itemsIndexed(pokemonList) { index, pokemon ->
      PokemonCard(
        pokemon = pokemon,
        isPaddingBottom = pokemonList.size - 1 == index,
        onClick = { onPokemonClick(pokemon.id) },
        isFavourite = favouritePokemonsState.contains(pokemon.globalId.toString()),
        onFavouriteClick = {
          ConfigMMKV.favoritePokemons = if (ConfigMMKV.favoritePokemons.contains(it.globalId.toString())) {
            favouritePokemonsState.remove(it.globalId.toString())
            ConfigMMKV.favoritePokemons - it.globalId.toString()
          } else {
            favouritePokemonsState.add(it.globalId.toString())
            ConfigMMKV.favoritePokemons + it.globalId.toString()
          }
        })
    }
  }
}

@Preview(locale = "zh")
@Composable
fun AbilityScreenPreview() {
  PokeGTheme {
//    AbilityScreen(Ability.Ability236, {}, abilityViewModel)
  }
}