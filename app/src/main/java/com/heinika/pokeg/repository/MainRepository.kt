package com.heinika.pokeg.repository

import androidx.annotation.WorkerThread
import com.heinika.pokeg.mapper.ErrorResponseMapper
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.model.PokemonInfo
import com.heinika.pokeg.network.PokeGClient
import com.heinika.pokeg.persistence.PokemonDao
import com.heinika.pokeg.persistence.PokemonInfoDao
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
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
    try {
      val pokemonList = pokemonDao.getAllPokemonList(page)
      if (pokemonList.isNotEmpty()){
        onSuccess()
        emit(pokemonList)
      }

      val response = pokeGClient.fetchPokemonList()
      response.suspendOnSuccess {
        data.whatIfNotNull { response ->
          pokemonDao.insertPokemonList(response.results)
          onSuccess()
          emit(pokemonList)
//          if (pokemonList.size < response.results.size) {
//            pokemonDao.insertPokemonList(response.results.subList(pokemonList.size, response.results.size))
//          }
        }
      }
        // handles the case when the API request gets an error response.
        // e.g., internal server error.
        .onError {
          map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
        }
        // handles the case when the API request gets an exception response.
        // e.g., network connection error.
        .onException { onError(message) }
    }catch (e:Exception){
      Timber.i(e)
    }

  }
    .onStart { onStart() }.flowOn(Dispatchers.IO)


  fun fetchUpdatePokemon(
    pokemon: Pokemon,
    onError: (String?) -> Unit
  ) = flow {
    val pokemonInfo = pokemonInfoDao.getPokemonInfo(pokemon.name)!!
    if (pokemonInfo == null) {
      val response = pokeGClient.fetchPokemonInfo(pokemon.name)
      response.suspendOnSuccess {
        data.whatIfNotNull { response ->
          pokemonInfoDao.insertPokemonInfo(response)
          updatePokemon(pokemon, response)
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

