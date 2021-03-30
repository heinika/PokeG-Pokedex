package com.heinika.pokeg.repository

import androidx.annotation.WorkerThread
import com.heinika.pokeg.mapper.ErrorResponseMapper
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.model.PokemonInfo
import com.heinika.pokeg.network.PokeGClient
import com.heinika.pokeg.persistence.PokemonDao
import com.heinika.pokeg.persistence.PokemonInfoDao
import com.skydoves.sandwich.*
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
  private val pokeGClient: PokeGClient,
  private val pokemonDao: PokemonDao,
  private val pokemonInfoDao: PokemonInfoDao
) : Repository {

  @WorkerThread
  fun fetchPokemonList(
    page: Int,
    onStart: () -> Unit,
    onSuccess: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {
    var pokemons = pokemonDao.getPokemonList(page)
    if (pokemons.isEmpty()) {
      /**
       * fetches a list of [Pokemon] from the network and getting [ApiResponse] asynchronously.
       * @see [suspendOnSuccess](https://github.com/skydoves/sandwich#suspendonsuccess-suspendonerror-suspendonexception)
       */
      val response = pokeGClient.fetchPokemonList(page = page)
      response.suspendOnSuccess {
        data.whatIfNotNull { response ->
          pokemons = response.results
          pokemons.forEach { pokemon ->
            pokemon.page = page
            pokemonDao.insertPokemonList(pokemons)
            onSuccess()
            emit(pokemonDao.getAllPokemonList(page))
          }
        }
      }
        // handles the case when the API request gets an error response.
        // e.g., internal server error.
        .onError {
          /** maps the [ApiResponse.Failure.Error] to the [PokemonErrorResponse] using the mapper. */
          map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
        }
        // handles the case when the API request gets an exception response.
        // e.g., network connection error.
        .onException { onError(message) }
    } else {
      val pokemonList = pokemonDao.getAllPokemonList(page)
      onSuccess()
      emit(pokemonList)
    }
  }.onStart { onStart() }.flowOn(Dispatchers.IO)

  @WorkerThread
  fun fetchUpdatePokemon(
    pokemon: Pokemon,
    onError: (String?) -> Unit
  ) = flow {
    val pokemonInfo = pokemonInfoDao.getPokemonInfo(pokemon.name)
    if (pokemonInfo == null) {
      val response = pokeGClient.fetchPokemonInfo(pokemon.name)
      response.suspendOnSuccess {
        data.whatIfNotNull { response ->
          pokemonInfoDao.insertPokemonInfo(response)
          updatePokemon(pokemon,response)
          emit(pokemon)
        }
      }
        .onError {
          map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
        }
        .onException { onError(message) }
    } else {
      updatePokemon(pokemon, pokemonInfo)
      emit(pokemon)
    }
  }.flowOn(Dispatchers.IO)

  private suspend fun updatePokemon(pokemon: Pokemon, pokemonInfo: PokemonInfo) {
    pokemonDao.updatePokemon(pokemon.updatePokemonInfo(pokemonInfo))
  }
}

