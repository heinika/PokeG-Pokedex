package com.heinika.pokeg.module.team

import com.heinika.pokeg.info.Move
import com.heinika.pokeg.model.MyPokemonInfo
import com.heinika.pokeg.repository.Repository
import com.heinika.pokeg.repository.res.PokemonRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TeamRepository @Inject constructor(
  private val pokemonRes: PokemonRes,
) : Repository {

  fun teamListMap(moveList: List<Move>) = flow {
    val map = linkedMapOf<String,List<MyPokemonInfo>>()
    val allAbilityList = pokemonRes.fetchAbilities()
    pokemonRes.fetchTeamList().forEach { team ->
      map[team.teamName] = team.teamNumbers.map {
        it.toMyPokemonInfo(allAbilityList)
      }
    }
    emit(map.toMap())
  }.flowOn(Dispatchers.IO)
}