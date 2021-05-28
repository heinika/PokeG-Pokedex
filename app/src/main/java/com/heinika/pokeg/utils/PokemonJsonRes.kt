package com.heinika.pokeg.utils

import android.app.Application
import androidx.annotation.WorkerThread
import com.heinika.pokeg.model.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("BlockingMethodInNonBlockingContext")
@Singleton
class PokemonJsonRes @Inject constructor(
  private val context: Application,
  private val moshi: Moshi
) {
  private val pokemonMoveVersionList: MutableList<PokemonMoveVersion> = mutableListOf()
  private val pokemonSpecieList: MutableList<PokemonSpecie> = mutableListOf()
  private val pokemonNameList: MutableList<PokemonName> = mutableListOf()
  private val pokemonTypeList: MutableList<PokemonType> = mutableListOf()
  private val pokemonNewList: MutableList<PokemonNew> = mutableListOf()
  private val pokemonAbilityList: MutableList<PokemonAbility> = mutableListOf()
  private val abilityList: MutableList<Ability> = mutableListOf()


  fun fetchPokemonMoveVersionList(id: Int): List<Int> {
    if (pokemonMoveVersionList.isEmpty()) {
      pokemonMoveVersionList.addAll(fetchListByJson("pokemon_move_version_list.json"))
    }
    return pokemonMoveVersionList.first { it.id == id }.versionList
  }


  @WorkerThread
  fun fetchPokemonMoveList(pokemonId: Int, version: Int): List<PokemonMove> {
    return fetchListByJson<PokemonMoveResult>("pokemon_move_$version.json")
      .first { it.id == pokemonId }.moves.toList()
  }


  @WorkerThread
  fun fetchMovesDetail(moveIds: List<Int>): List<Move> {
    return fetchListByJson<Move>("moves.json").filter { moveIds.contains(it.id) }
  }

  @WorkerThread
  fun fetchSpeciesEggGroup(id: Int): List<SpeciesEggGroup> {
    return fetchListByJson<SpeciesEggGroup>("pokemon_egg_group.json").filter { it.speciesId == id }
  }

  @WorkerThread
  fun fetchPokemonSpecie(): List<PokemonSpecie> {
    if (pokemonSpecieList.isEmpty()) {
      pokemonSpecieList.addAll(fetchListByJson("pokemon_species.json"))
    }
    return pokemonSpecieList
  }

  @WorkerThread
  fun fetchPokemonNew(): List<PokemonNew> {
    if (pokemonNewList.isEmpty()) {
      pokemonNewList.addAll(fetchListByJson("pokemon.json"))
    }
    return pokemonNewList
  }

  @WorkerThread
  fun fetchPokemonAbility(): List<PokemonAbility> {
    if (pokemonAbilityList.isEmpty()) {
      pokemonAbilityList.addAll(fetchListByJson("pokemon_abilities.json"))
    }
    return pokemonAbilityList
  }

  @WorkerThread
  fun fetchPokemonName(): List<PokemonName> {
    if (pokemonNameList.isEmpty()) {
      pokemonNameList.addAll(fetchListByJson("pokemon_names.json"))
    }
    return pokemonNameList
  }

  @WorkerThread
  fun fetchPokemonType(): List<PokemonType> {
    if (pokemonTypeList.isEmpty()) {
      pokemonTypeList.addAll(fetchListByJson("pokemon_types.json"))
    }
    return pokemonTypeList
  }

  @WorkerThread
  fun fetchAbilities(): List<Ability> {
    if (abilityList.isEmpty()) {
      abilityList.addAll(fetchListByJson("abilities.json"))
    }
    return abilityList
  }

  @WorkerThread
  private inline fun <reified T> fetchListByJson(jsonFileName: String): List<T> {
    val type = Types.newParameterizedType(MutableList::class.java, T::class.java)
    val adapter: JsonAdapter<List<T>> = moshi.adapter(type)
    return adapter.fromJson(
      context.assets.open(jsonFileName).bufferedReader().use { it.readText() })!!
  }

}
