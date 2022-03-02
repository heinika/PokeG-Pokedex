package com.heinika.pokeg.repository.res

import android.app.Application
import androidx.annotation.WorkerThread
import com.heinika.pokeg.model.*
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("BlockingMethodInNonBlockingContext")
@Singleton
class PokemonRes @Inject constructor(
  private val context: Application,
  private val jsonRes: PokemonJsonRes
) {
  fun getTypeColor(type: Int): Int = ResUtils.getTypeColor(type, context)

  fun getVersionName(id: Int) = ResUtils.getVersionName(id, context)

  fun getEggGroupName(name: Int) = ResUtils.getEggGroupName(name, context)

  fun getGrowRate(id: Int) = ResUtils.getGrowRate(id, context)

  fun getGeneration(id: Int) = ResUtils.getGeneration(id, context)

  fun getShape(id: Int) = ResUtils.getShape(id, context)

  fun getHabitat(id: Int) = ResUtils.getHabitat(id, context)

  fun getMoveMethodName(id: Int) = ResUtils.getMoveMethodName(id, context)

  fun getMoveName(id: Int) = ResUtils.getMoveName(id, context)

  fun getTypeString(id: Int): String = ResUtils.getTypeString(id, context)

  fun getDamageClassName(id: Int) = ResUtils.getDamageClassName(id, context)

  fun getNameById(id: Int, name: String, form: Int = 0): String =
    ResUtils.getNameById(id, name, form,context)

  fun getItemById(id: Int): String = ResUtils.getItemById(id, context)

  @WorkerThread
  fun fetchPokemonMoveVersionList(id: Int, speciesId: Int): List<Int> =
    jsonRes.fetchPokemonMoveVersionList(id, speciesId)

  @WorkerThread
  fun fetchPokemonMoveList(pokemonId: Int, speciesId: Int, version: Int): List<PokemonMove> =
    jsonRes.fetchPokemonMoveList(pokemonId, speciesId, version)

  @WorkerThread
  fun fetchSpeciesEggGroup(specieId: Int): List<SpeciesEggGroup> =
    jsonRes.fetchSpeciesEggGroup(specieId)

  @WorkerThread
  fun fetchSpeciesEvolutionChain(): List<SpeciesEvolutionChain> =
    jsonRes.fetchSpeciesEvolutionChain()

  @WorkerThread
  fun fetchSpecieFlavorText(specieId: Int): List<SpecieFlavorText> =
    jsonRes.fetchSpecieFlavorText(specieId)

  @WorkerThread
  fun fetchSpecieFlavorBaseText(specieId: Int): SpecieFlavorBaseText =
    jsonRes.fetchSpecieFlavorBaseText(specieId)

  @WorkerThread
  fun fetchPokemonName(): List<PokemonName> = jsonRes.fetchPokemonName()

  @WorkerThread
  fun fetchPokemonType(): List<PokemonType> = jsonRes.fetchPokemonType()

  @WorkerThread
  fun fetchPokemonBaseStat(): List<PokemonBaseStat> = jsonRes.fetchPokemonBaseStat()

  @WorkerThread
  fun fetchPokemonSpecies(): List<PokemonSpecie> = jsonRes.fetchPokemonSpecie()

  @WorkerThread
  fun fetchPokemon(): List<Pokemon> = jsonRes.fetchPokemon()

  @WorkerThread
  fun fetchPokemonAbilities(): List<PokemonAbility> = jsonRes.fetchPokemonAbility()

}
