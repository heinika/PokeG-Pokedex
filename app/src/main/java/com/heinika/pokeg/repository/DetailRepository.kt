package com.heinika.pokeg.repository

import androidx.annotation.WorkerThread
import com.heinika.pokeg.mapper.ErrorResponseMapper
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.network.PokeGClient
import com.heinika.pokeg.persistence.PokemonDao
import com.heinika.pokeg.persistence.PokemonInfoDao
import com.heinika.pokeg.ui.detail.itemdelegate.model.MoveItem
import com.heinika.pokeg.utils.PokemonRes
import com.skydoves.sandwich.map
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class DetailRepository @Inject constructor(
  private val pokeGClient: PokeGClient,
  private val pokemonInfoDao: PokemonInfoDao,
  private val pokemonDao: PokemonDao,
  private val pokemonRes: PokemonRes
) : Repository {

  @WorkerThread
  fun fetchPokemonInfo(
    pokemon: Pokemon,
    onSuccess: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {
    val pokemonInfo = pokemonInfoDao.getPokemonInfo(pokemon.name)
    if (pokemonInfo == null) {
      val response = pokeGClient.fetchPokemonInfo(pokemon.name)
      response.suspendOnSuccess {
        data.whatIfNotNull { response ->
          pokemonInfoDao.insertPokemonInfo(response)
          pokemonDao.updatePokemon(pokemon.updatePokemonInfo(response))
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
  }.flowOn(Dispatchers.IO)

  fun pokemonMoveVersionsFlow(id: Int) = flow {
    emit(pokemonRes.fetchPokemonMoveVersionList(id))
  }.flowOn(Dispatchers.IO)

  fun pokemonMovesFlow(id: Int, version: Int) = flow {
    val pokemonMoveList = pokemonRes.fetchPokemonMoveList(id, version)
    val moveList = pokemonRes.fetchMovesDetail(pokemonMoveList.map { it.moveId })
    emit(
      pokemonMoveList.map { pokemonMove ->
        val move = moveList.first { it.id == pokemonMove.moveId }
        MoveItem(
          id = pokemonMove.moveId,
          methodId = pokemonMove.methodId,
          name = pokemonRes.getMoveName(pokemonMove.moveId),
          level = pokemonMove.level,
          type = pokemonRes.getTypeString(move.typeId),
          pp = move.pp,
          power = move.power,
          accuracy = move.accuracy,
          damageClass = pokemonRes.getDamageClassName(move.damageClassId),
          typeColor = pokemonRes.getTypeColor(move.typeId),
        )
      }.sortedBy { it.level }.groupBy { it.methodId }
    )
  }.flowOn(Dispatchers.IO)

  fun pokemonNameFlow(id: Int) = flow {
    emit(pokemonRes.fetchPokemonName().filter { it.pokemonSpeciesId == id })
  }.flowOn(Dispatchers.IO)

  fun pokemonTypeFlow(id: Int) = flow {
    emit(pokemonRes.fetchPokemonType().filter { it.pokemonId == id })
  }.flowOn(Dispatchers.IO)

  fun pokemonSpecieFlow(id: Int) = flow {
    emit(pokemonRes.fetchPokemonSpecie().first { it.id == id })
  }.flowOn(Dispatchers.IO)

  fun pokemonSpecieEggGroup(id: Int) = flow {
    try {
      emit(pokemonRes.fetchSpeciesEggGroup(id))
    } catch (e : Exception){
      Timber.e(e)
    }

  }.flowOn(Dispatchers.IO)
}