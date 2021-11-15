package com.heinika.pokeg.repository.res

import androidx.annotation.WorkerThread
import com.heinika.pokeg.model.*
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("BlockingMethodInNonBlockingContext")
@Singleton
class PokemonRes @Inject constructor(
  private val stringRes: PokemonStringRes,
  private val colorRes: PokemonColorRes,
  private val jsonRes: PokemonJsonRes
) {
  fun getTypeColor(type: Int): Int = colorRes.getTypeColor(type)

  fun getVersionName(id: Int) = stringRes.getVersionName(id)

  fun getEggGroupName(name: Int) = stringRes.getEggGroupName(name)

  fun getGrowRate(id: Int) = stringRes.getGrowRate(id)

  fun getGeneration(id: Int) = stringRes.getGeneration(id)

  fun getShape(id: Int) = stringRes.getShape(id)

  fun getHabitat(id: Int) = stringRes.getHabitat(id)

  fun getMoveMethodName(id: Int) = stringRes.getMoveMethodName(id)

  fun getMoveName(id: Int) = stringRes.getMoveName(id)

  fun getTypeString(id: Int): String = stringRes.getTypeString(id)

  fun getDamageClassName(id: Int) = stringRes.getDamageClassName(id)

  fun getNameById(id: Int, name: String): String = stringRes.getNameById(id, name)

  fun getItemById(id: Int): String = stringRes.getItemById(id)

  @WorkerThread
  fun fetchPokemonMoveVersionList(id: Int,speciesId:Int): List<Int> = jsonRes.fetchPokemonMoveVersionList(id,speciesId)

  @WorkerThread
  fun fetchPokemonMoveList(pokemonId: Int, speciesId:Int,version: Int): List<PokemonMove> =
    jsonRes.fetchPokemonMoveList(pokemonId,speciesId, version)

  @WorkerThread
  fun fetchMovesDetail(moveIds: List<Int>): List<Move> = jsonRes.fetchMovesDetail(moveIds)

  @WorkerThread
  fun fetchAllMoves(): List<Move> = jsonRes.fetchAllMoves()

  @WorkerThread
  fun fetchSpeciesEggGroup(specieId: Int): List<SpeciesEggGroup> = jsonRes.fetchSpeciesEggGroup(specieId)

  @WorkerThread
  fun fetchSpeciesEvolutionChain(): List<SpeciesEvolutionChain> = jsonRes.fetchSpeciesEvolutionChain()

  @WorkerThread
  fun fetchSpecieFlavorText(specieId: Int): List<SpecieFlavorText> = jsonRes.fetchSpecieFlavorText(specieId)

  @WorkerThread
  fun fetchSpecieFlavorBaseText(specieId: Int): SpecieFlavorBaseText = jsonRes.fetchSpecieFlavorBaseText(specieId)

  @WorkerThread
  fun fetchPokemonName(): List<PokemonName> = jsonRes.fetchPokemonName()

  @WorkerThread
  fun fetchPokemonType(): List<PokemonType> = jsonRes.fetchPokemonType()

  @WorkerThread
  fun fetchPokemonBaseStat(): List<PokemonBaseStat> = jsonRes.fetchPokemonBaseStat()

  @WorkerThread
  fun fetchPokemonSpecies(): List<PokemonSpecie> = jsonRes.fetchPokemonSpecie()

  @WorkerThread
  fun fetchPokemonNew(): List<PokemonNew> = jsonRes.fetchPokemonNew()

  @WorkerThread
  fun fetchPokemonAbilities(): List<PokemonAbility> = jsonRes.fetchPokemonAbility()

  @WorkerThread
  fun fetchAbilities(): List<Ability> = jsonRes.fetchAbilities()
}
