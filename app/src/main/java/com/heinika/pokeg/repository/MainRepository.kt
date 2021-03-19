package com.heinika.pokeg.repository

import androidx.annotation.WorkerThread
import com.heinika.pokeg.mapper.ErrorResponseMapper
import com.heinika.pokeg.network.PokeGClient
import com.heinika.pokeg.persistence.PokemonDao
import com.skydoves.sandwich.*
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val pokedexClient: PokeGClient,
    private val pokemonDao: PokemonDao
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
            val response = pokedexClient.fetchPokemonList(page = page)
            response.suspendOnSuccess {
                data.whatIfNotNull { response ->
                    pokemons = response.results
                    pokemons.forEach { pokemon ->
                        pokemon.page = page
                    }
                    pokemonDao.insertPokemonList(pokemons)
                    emit(pokemonDao.getAllPokemonList(page))
                    onSuccess()
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
            emit(pokemonDao.getAllPokemonList(page))
            onSuccess()
        }
    }.onStart { onStart() }.flowOn(Dispatchers.IO)
}

