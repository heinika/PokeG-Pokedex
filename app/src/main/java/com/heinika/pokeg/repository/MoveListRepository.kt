package com.heinika.pokeg.repository

import com.heinika.pokeg.repository.res.PokemonRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoveListRepository @Inject constructor(
  private val pokemonRes: PokemonRes
) : Repository {

  fun allMovesFlow(id: Int) = flow {
    emit(pokemonRes.fetchAllMoves())
  }.flowOn(Dispatchers.IO)
}