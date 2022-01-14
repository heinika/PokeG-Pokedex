package com.heinika.pokeg.module.team

import android.app.Application
import com.heinika.pokeg.PokeGApp
import com.heinika.pokeg.model.Move
import com.heinika.pokeg.repository.Repository
import com.heinika.pokeg.repository.res.PokemonRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TeamRepository @Inject constructor(
  private val pokemonRes: PokemonRes,
  private val pokeGApp: Application
) : Repository {

  fun teamNumberInfoListFlow(moveList: List<Move>) = flow {
    emit(pokemonRes.fetchTeamNumberList().map { it.toTeamNumberInfo(pokeGApp, moveList) })
  }.flowOn(Dispatchers.IO)

  fun allMovesFlow() = flow {
    emit(pokemonRes.fetchAllMoves())
  }.flowOn(Dispatchers.IO)
}