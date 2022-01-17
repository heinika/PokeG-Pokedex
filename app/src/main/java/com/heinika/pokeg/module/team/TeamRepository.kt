package com.heinika.pokeg.module.team

import android.app.Application
import com.heinika.pokeg.model.Move
import com.heinika.pokeg.model.TeamNumberInfo
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

  fun teamListMap(moveList: List<Move>) = flow {
    val map = linkedMapOf<String,List<TeamNumberInfo>>()
    pokemonRes.fetchTeamList().forEach { team ->
      map[team.teamName] = team.teamNumbers.map {
        it.toTeamNumberInfo(pokeGApp, moveList)
      }
    }
    emit(map.toMap())
  }.flowOn(Dispatchers.IO)

  fun allMovesFlow() = flow {
    emit(pokemonRes.fetchAllMoves())
  }.flowOn(Dispatchers.IO)
}