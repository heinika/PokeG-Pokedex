package com.heinika.pokeg.repository

import androidx.annotation.WorkerThread
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.model.PokemonNew
import com.heinika.pokeg.repository.res.PokemonRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
  private val pokemonRes: PokemonRes
) : Repository {

  @WorkerThread
  fun fetchPokemonList(
    onStart: () -> Unit,
    onSuccess: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {
    try {
      val pokemonList = pokemonRes.fetchPokemonNew().map { pokemon ->
        toPokemon(pokemon)
      }
      onSuccess()
      emit(pokemonList)
    } catch (e: Exception) {
      onError(e.message)
      Timber.i(e)
    }

  }.onStart { onStart() }.flowOn(Dispatchers.IO)

  private fun toPokemon(pokemon: PokemonNew): Pokemon {
    var totalBaseStat = 0
    pokemonRes.fetchPokemonBaseStat().filter { it.pokemonId == pokemon.id }.forEach {
      totalBaseStat += it.baseStat
    }
    return Pokemon(
      id = pokemon.id,
      speciesId = pokemon.speciesId,
      name = pokemon.identifier,
      types = pokemonRes.fetchPokemonType().filter { it.pokemonId == pokemon.id },
      totalBaseStat = totalBaseStat
    )
  }

}

