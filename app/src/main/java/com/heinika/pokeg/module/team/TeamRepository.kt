package com.heinika.pokeg.module.team

import com.heinika.pokeg.database.MyPokemonDao
import com.heinika.pokeg.model.Ability
import com.heinika.pokeg.model.MyPokemon
import com.heinika.pokeg.model.MyPokemonInfo
import com.heinika.pokeg.repository.Repository
import com.heinika.pokeg.repository.res.PokemonRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class TeamRepository @Inject constructor(
  private val pokemonRes: PokemonRes,
  private val myPokemonDao: MyPokemonDao
) : Repository {
  private var allAbilityList:List<Ability> = emptyList()
  var allMyPokemonList : List<MyPokemon> = emptyList()

  fun teamListMap() = myPokemonDao.allMyPokemonListFlow().map {

    allMyPokemonList = it

    if (allAbilityList.isEmpty()){
      allAbilityList = pokemonRes.fetchAbilities()
    }

    val teamPokemonInfoList = mutableListOf<MyPokemonInfo>()

    it.forEach { myPokemon ->
      if (myPokemon.teamName.isNotEmpty()){
        myPokemon.teamName.split(";").forEach {
          val myPokemonInfo = myPokemon.toMyPokemonInfo(allAbilityList).apply { teamName = it }
          teamPokemonInfoList.add(myPokemonInfo)
        }
      }
    }

    Timber.i("teamPokemonInfoList ${teamPokemonInfoList.joinToString()}")
    teamPokemonInfoList.groupBy { it.teamName }
  }.flowOn(Dispatchers.IO)

  suspend fun updateMyPokemon(myPokemon: MyPokemon) {
    myPokemonDao.insertMyPokemon(myPokemon)
  }
}