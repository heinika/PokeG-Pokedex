package com.heinika.pokeg.repository

import androidx.annotation.WorkerThread
import com.heinika.pokeg.mapper.ErrorResponseMapper
import com.heinika.pokeg.network.PokeGClient
import com.heinika.pokeg.persistence.PokemonInfoDao
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val pokeGClient: PokeGClient,
    private val pokemonInfoDao: PokemonInfoDao
) : Repository {

    @WorkerThread
    fun fetchPokemonList(
        name: String,
        onSuccess: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val pokemonInfo = pokemonInfoDao.getPokemonInfo(name)
        if (pokemonInfo == null) {
            val response = pokeGClient.fetchPokemonInfo(name)
            response.suspendOnSuccess {
                data.whatIfNotNull { response ->
                    pokemonInfoDao.insertPokemonInfo(response)
                    emit(response)
                    onSuccess()
                }
            }
                .onError {
                    map(ErrorResponseMapper) { onError("[Code: $code]: $message") }
                }
                .onException { onError(message) }
        } else {
            emit(pokemonInfo)
            onSuccess()
        }
    }

}