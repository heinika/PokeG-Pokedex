package com.heinika.pokeg.module.mypokemon

import com.heinika.pokeg.database.MyPokemonDao
import com.heinika.pokeg.model.Ability
import com.heinika.pokeg.model.MyPokemon
import com.heinika.pokeg.repository.Repository
import com.heinika.pokeg.repository.res.PokemonRes
import javax.inject.Inject

class MyPokemonRepository @Inject constructor(
  val pokemonRes: PokemonRes,
  val myPokemonDao: MyPokemonDao
) : Repository {

  fun fetchPokemonAbilities(id: Int) = arrayListOf<Ability>().apply {
    pokemonRes.fetchPokemonAbilities().filter { it.pokemonId == id }.forEach { pokemonAbility ->
      add(pokemonRes.fetchAbilities().first { it.num == pokemonAbility.abilityId })
    }
  }.toList()

  fun fetchMoveList(id: Int, speciesId: Int, version: Int) =
    pokemonRes.fetchPokemonMoveList(id, speciesId, version)

  fun fetchPokemonMoveVersionList(id: Int, speciesId: Int) =
    pokemonRes.fetchPokemonMoveVersionList(id, speciesId)

  fun insertMyPokemon(myPokemon: MyPokemon) {
    myPokemonDao.insertMyPokemon(myPokemon)
  }

  fun fetchAllMyPokemonList() = myPokemonDao.getAlMyPokemon()

}