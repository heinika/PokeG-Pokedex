package com.heinika.pokeg.module.ability

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.heinika.pokeg.PokemonDataCache
import com.heinika.pokeg.info.Ability
import com.heinika.pokeg.module.mypokemon.compose.PokemonCard
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.SystemBar
import com.heinika.pokeg.utils.formatId

@OptIn(ExperimentalCoilApi::class)
@Composable
fun AbilityScreen(ability: Ability, onBack: () -> Unit, onPokemonClick: (pokemonId: Int) -> Unit, abilityViewModel: AbilityViewModel) {
  val pokemonList = abilityViewModel.findPokemonListByAbilityId(ability.id).map { pokemonId ->
    PokemonDataCache.pokemonList.first { it.globalId == pokemonId }
  }
  LazyColumn {
    item {
      Column {
        TopAppBar(
          navigationIcon = {
            IconButton(onClick = {
              onBack()
            }) {
              Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
          },
          title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(text = stringResource(id = ability.nameResId), style = MaterialTheme.typography.h5, modifier = Modifier.weight(1f))
              Text(text = ability.id.formatId, style = MaterialTheme.typography.subtitle1, modifier = Modifier.padding(end = 24.dp))
            }
          },
          backgroundColor = Color.Transparent,
          modifier = Modifier.padding(top = SystemBar.statusBarHeightDp.dp)
        )
        Text(text = stringResource(id = ability.flavorResId), style = MaterialTheme.typography.body1, modifier = Modifier.padding(12.dp))
        Text(
          text = stringResource(id = ability.effectResId),
          style = MaterialTheme.typography.body1,
          modifier = Modifier.padding(12.dp, 0.dp)
        )
      }
    }

    itemsIndexed(pokemonList) { index, pokemon ->
      PokemonCard(pokemon = pokemon, isPaddingBottom = pokemonList.size - 1 == index, onclick = { onPokemonClick(pokemon.id) })
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