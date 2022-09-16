package com.heinika.pokeg.module.mypokemon.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import coil.annotation.ExperimentalCoilApi
import com.heinika.pokeg.ConfigMMKV
import com.heinika.pokeg.PokemonDataCache
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.repository.res.ResUtils
import com.heinika.pokeg.ui.compose.PokemonCard


@ExperimentalCoilApi
@Composable
fun AddMyPokemonDialog(
  modifier: Modifier = Modifier,
  dialogState: Boolean = false,
  onDialogStateChange: ((Boolean) -> Unit)? = null,
  onDismissRequest: (() -> Unit)? = null,
  pokemonList: List<Pokemon> = PokemonDataCache.pokemonList,
  onPokemonItemClick: (Pokemon) -> Unit
) {
  val dialogShape = RoundedCornerShape(16.dp)

  var searchText by remember { mutableStateOf("") }
  val favouritePokemonsState = remember { mutableStateListOf<String>().apply { addAll(ConfigMMKV.favoritePokemons) } }
  val context = LocalContext.current

  if (dialogState) {
    AlertDialog(
      onDismissRequest = {
        onDialogStateChange?.invoke(false)
        onDismissRequest?.invoke()
      },
      title = null,
      text = null,
      confirmButton = {
        Column {
          TextField(modifier = Modifier
            .background(
              Color.White
            )
            .fillMaxWidth(), leadingIcon = {
            Image(imageVector = Icons.Default.Search, contentDescription = "")
          }, value = searchText,
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
            onValueChange = {
              searchText = it
            })

          LazyColumn {
            items(pokemonList.filter {
              it.globalId.toString().contains(searchText) ||
                  ResUtils.getNameById(it.id, context = context).contains(searchText)
            }) { pokemon ->
              PokemonCard(
                pokemon = pokemon,
                onClick = {
                  onPokemonItemClick(it)
                },
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
      },
      properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
      modifier = modifier,
      shape = dialogShape
    )
  }
}


