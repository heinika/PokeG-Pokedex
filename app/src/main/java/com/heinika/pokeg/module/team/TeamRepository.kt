package com.heinika.pokeg.module.team

import com.heinika.pokeg.database.MyPokemonDao
import com.heinika.pokeg.model.MyPokemonInfo
import com.heinika.pokeg.repository.Repository
import com.heinika.pokeg.repository.res.PokemonRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TeamRepository @Inject constructor(
  private val pokemonRes: PokemonRes,
  private val myPokemonDao: MyPokemonDao
) : Repository {

  fun teamListMap() = flow {

    val allAbilityList = pokemonRes.fetchAbilities()

    val teamPokemonInfoList = mutableListOf<MyPokemonInfo>()
    myPokemonDao.getAlMyPokemon().forEach {myPokemon ->
      if (myPokemon.teamName.isNotEmpty()){
        myPokemon.teamName.split(";").forEach {
          val myPokemonInfo = myPokemon.toMyPokemonInfo(allAbilityList).apply { teamName = it }
          teamPokemonInfoList.add(myPokemonInfo)
        }
      }
    }

    emit(teamPokemonInfoList.groupBy { it.teamName })
  }.flowOn(Dispatchers.IO)
}