package com.heinika.pokeg.repository

import com.heinika.pokeg.info.Ability
import com.heinika.pokeg.info.Move
import com.heinika.pokeg.model.PokemonSpecie
import com.heinika.pokeg.model.SpeciesEvolutionChain
import com.heinika.pokeg.module.detail.MoveItem
import com.heinika.pokeg.repository.res.PokemonRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepository @Inject constructor(
  private val pokemonRes: PokemonRes
) : Repository {

  fun pokemonMoveVersionsFlow(id: Int, speciesId: Int) = flow {
    try {
      emit(pokemonRes.fetchPokemonMoveVersionList(id, speciesId))
    } catch (e: Exception) {
      Timber.e(e)
    }
  }.flowOn(Dispatchers.IO)

  fun pokemonMovesFlow(id: Int, speciesId: Int, version: Int) = flow {
    try {
      val pokemonMoveList = pokemonRes.fetchPokemonMoveList(id, speciesId, version)
      emit(
        pokemonMoveList.map { pokemonMove ->
          val move = Move.values().first { it.id == pokemonMove.moveId }
          MoveItem(
            id = pokemonMove.moveId,
            methodId = pokemonMove.methodId,
            name = pokemonRes.getMoveName(pokemonMove.moveId),
            level = pokemonMove.level,
            type = pokemonRes.getTypeString(move.typeId),
            pp = move.pp,
            power = move.power,
            accuracy = move.accuracy,
            damageClass = pokemonRes.getDamageClassName(move.damageClassId),
            typeColor = pokemonRes.getTypeColor(move.typeId),
          )
        }.sortedBy { it.level }.groupBy { it.methodId }
      )
    } catch (e: Exception) {
      Timber.e(e)
    }
  }.flowOn(Dispatchers.IO)

  fun pokemonNameFlow(id: Int) = flow {
    emit(pokemonRes.fetchPokemonName().filter { it.pokemonSpeciesId == id })
  }.flowOn(Dispatchers.IO)

  fun pokemonSpecieFlow(id: Int) = flow {
    val list = pokemonRes.fetchPokemonSpecies()
    if (list.any { it.id == id }) {
      emit(list.first { it.id == id })
    } else {
      emit(
        PokemonSpecie(
          baseHappiness = 0,
          captureRate = 1,
          colorId = 1,
          conquestOrder = "",
          evolutionChainId = 0,
          evolvesFromSpeciesId = 0,
          formsSwitchable = 1,
          genderRate = 1,
          generationId = 1,
          growthRateId = 1,
          habitatId = "",
          hasGenderDifferences = 1,
          hatchCounter = 1,
          id = 1,
          identifier = "",
          isBaby = 0,
          isLegendary = 0,
          isMythical = 0,
          order = 0,
          shapeId = 0,
        )
      )
    }
  }.flowOn(Dispatchers.IO)

  fun speciesAllOtherFormsFlow(specieId: Int, globalId: Int) = flow {
    Timber.i("speciesId ${specieId},globalId ${globalId}")
    emit(pokemonRes.fetchPokemon().filter { it.speciesId == specieId && it.globalId != globalId })
  }.flowOn(Dispatchers.IO)

  fun pokemonAbilitiesFlow(id: Int) = flow {
    emit(
      arrayListOf<Ability>().apply {
        pokemonRes.fetchPokemonAbilities().filter { it.pokemonId == id }.forEach { pokemonAbility ->
          val ability = Ability.values().first { it.id == pokemonAbility.abilityId }
          if (!contains(ability)) {
            add(ability)
          }
        }
      }.toList()
    )
  }.flowOn(Dispatchers.IO)

  fun specieEggGroupFlow(id: Int) = flow {
    emit(pokemonRes.fetchSpeciesEggGroup(id))
  }.flowOn(Dispatchers.IO)

  fun specieEvolutionChainFlow(id: Int) = flow {
    try {
      val allSpecieList = pokemonRes.fetchPokemonSpecies()
      val allChainList = pokemonRes.fetchSpeciesEvolutionChain()
      val thisSpecie = allSpecieList.first { it.id == id }
      val thisChainSpecies =
        allSpecieList.filter { it.evolutionChainId == thisSpecie.evolutionChainId }
      val thisChainList = arrayListOf<SpeciesEvolutionChain>().apply {
        thisChainSpecies.forEach { specie ->
          if (specie.evolvesFromSpeciesId != -1) {
            add(allChainList.first { it.evolvedToSpeciesId == specie.id }.apply {
              evolvedFromSpeciesId = specie.evolvesFromSpeciesId
              evolvedFromName = thisChainSpecies.first { it.id == evolvedFromSpeciesId }.identifier
              evolvedToName = thisChainSpecies.first { it.id == this.evolvedToSpeciesId }.identifier
            })
          }
        }
      }

      emit(thisChainList)
    } catch (e: Exception) {
      Timber.e(e)
    }

  }.flowOn(Dispatchers.IO)

  fun specieFlavorTextsFlow(id: Int) = flow {
    if (pokemonRes.fetchSpecieFlavorText(id).isEmpty()) {
      emit(pokemonRes.fetchSpecieFlavorBaseText(id).description)
    } else {
      emit(pokemonRes.fetchSpecieFlavorText(id).last().flavorText)
    }
  }.flowOn(Dispatchers.IO)
}