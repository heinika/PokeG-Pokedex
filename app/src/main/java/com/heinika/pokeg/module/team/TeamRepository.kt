package com.heinika.pokeg.module.team

import com.heinika.pokeg.database.MyPokemonDao
import com.heinika.pokeg.model.MyPokemon
import com.heinika.pokeg.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class TeamRepository @Inject constructor(
  private val myPokemonDao: MyPokemonDao
) : Repository {

  var allMyPokemonList : List<MyPokemon> = emptyList()

  fun teamListMap() = myPokemonDao.allMyPokemonListFlow().map {

    allMyPokemonList = it

    val teamPokemonInfoList = mutableListOf<MyPokemon>()

    it.forEach { myPokemon ->
      if (myPokemon.teamName.isNotEmpty()){
        myPokemon.teamName.split(";").forEach {
          val myPokemonInfo = myPokemon.apply { teamName = it }
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