package com.heinika.pokeg.repository.res

import android.app.Application
import androidx.annotation.WorkerThread
import com.heinika.pokeg.model.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.util.concurrent.CopyOnWriteArrayList
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("BlockingMethodInNonBlockingContext")
@Singleton
class PokemonJsonRes @Inject constructor(
  private val context: Application,
  private val moshi: Moshi
) {
  private val pokemonMoveVersionList: MutableList<PokemonMoveVersion> = mutableListOf()
  private val pokemonSpecieList: CopyOnWriteArrayList<PokemonSpecie> = CopyOnWriteArrayList()
  private val pokemonNameList: MutableList<PokemonName> = mutableListOf()
  private val pokemonTypeList: MutableList<PokemonType> = mutableListOf()
  private val pokemonNewList: MutableList<PokemonNew> = mutableListOf()
  private val pokemonAbilityList: MutableList<PokemonAbility> = mutableListOf()
  private val abilityList: MutableList<Ability> = mutableListOf()
  private val pokemonBaseStatList: MutableList<PokemonBaseStat> = mutableListOf()
  private val natureList: MutableList<Nature> = mutableListOf()


  fun fetchPokemonMoveVersionList(id: Int, speciesId: Int): List<Int> {
    if (pokemonMoveVersionList.isEmpty()) {
      pokemonMoveVersionList.addAll(fetchListByJson("pokemon_move_version_group_list.json"))
    }
    if (pokemonMoveVersionList.any { it.id == id }) {
      return pokemonMoveVersionList.first { it.id == id }.versionList
    } else {
      return pokemonMoveVersionList.first { it.id == speciesId }.versionList
    }
  }


  @WorkerThread
  fun fetchPokemonMoveList(pokemonId: Int,speciesId: Int, version: Int): List<PokemonMove> {
    return if (fetchListByJson<PokemonMoveResult>("pokemon_move_$version.json")
        .any() { it.id == pokemonId }){
      fetchListByJson<PokemonMoveResult>("pokemon_move_$version.json")
        .first { it.id == pokemonId }.moves.toList()
    }else {
      fetchListByJson<PokemonMoveResult>("pokemon_move_$version.json")
        .first { it.id == speciesId }.moves.toList()
    }
  }

  @WorkerThread
  fun fetchMovesDetail(moveIds: List<Int>): List<Move> {
    return fetchListByJson<Move>("all_moves.json").filter { moveIds.contains(it.id) }
  }

  @WorkerThread
  fun fetchAllMoves(): List<Move> {
    return fetchListByJson<Move>("all_moves.json")
  }

  @WorkerThread
  fun fetchNatureList(): List<Nature> {
    return fetchListByJson<Nature>("nature.json")
  }

  @WorkerThread
  fun fetchSpeciesEggGroup(id: Int): List<SpeciesEggGroup> {
    return fetchListByJson<SpeciesEggGroup>("pokemon_egg_group.json").filter { it.speciesId == id }
  }

  @WorkerThread
  fun fetchSpeciesEvolutionChain(): List<SpeciesEvolutionChain> {
    return fetchListByJson("species_evolution_chain.json")
  }

  @WorkerThread
  fun fetchSpecieFlavorText(id: Int): List<SpecieFlavorText> {
    return fetchListByJson<SpecieFlavorText>("pokemon_flavor_text.json").filter { it.speciesId == id }
  }

  @WorkerThread
  fun fetchSpecieFlavorBaseText(id: Int): SpecieFlavorBaseText {
    return fetchListByJson<SpecieFlavorBaseText>("species_flavor_base_text.json").first() { it.id == id }
  }

  @WorkerThread
  fun fetchPokemonSpecie(): List<PokemonSpecie> {
    if (pokemonSpecieList.isEmpty()) {
      val list = fetchListByJson<PokemonSpecie>("pokemon_species.json")
      if (pokemonSpecieList.isEmpty()) {
        pokemonSpecieList.addAll(list)
      }
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
  fun fetchPokemonBaseStat(): List<PokemonBaseStat> {
    if (pokemonBaseStatList.isEmpty()) {
      pokemonBaseStatList.addAll(fetchListByJson("pokemon_base_stats.json"))
    }
    return pokemonBaseStatList
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
