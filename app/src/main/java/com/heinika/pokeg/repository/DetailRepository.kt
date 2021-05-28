package com.heinika.pokeg.repository

import com.heinika.pokeg.model.Ability
import com.heinika.pokeg.ui.detail.itemdelegate.model.MoveItem
import com.heinika.pokeg.utils.PokemonRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(
  private val pokemonRes: PokemonRes
) : Repository {

  fun pokemonMoveVersionsFlow(id: Int) = flow {
    emit(pokemonRes.fetchPokemonMoveVersionList(id))
  }.flowOn(Dispatchers.IO)

  fun pokemonMovesFlow(id: Int, version: Int) = flow {
    val pokemonMoveList = pokemonRes.fetchPokemonMoveList(id, version)
    val moveList = pokemonRes.fetchMovesDetail(pokemonMoveList.map { it.moveId })
    emit(
      pokemonMoveList.map { pokemonMove ->
        val move = moveList.first { it.id == pokemonMove.moveId }
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
  }.flowOn(Dispatchers.IO)

  fun pokemonNameFlow(id: Int) = flow {
    emit(pokemonRes.fetchPokemonName().filter { it.pokemonSpeciesId == id })
  }.flowOn(Dispatchers.IO)

  fun pokemonTypeFlow(id: Int) = flow {
    emit(pokemonRes.fetchPokemonType().filter { it.pokemonId == id })
  }.flowOn(Dispatchers.IO)

  fun pokemonBaseStatFlow(id: Int) = flow {
    emit(pokemonRes.fetchPokemonBaseStat().filter { it.pokemonId == id })
  }.flowOn(Dispatchers.IO)

  fun pokemonSpecieFlow(id: Int) = flow {
    emit(pokemonRes.fetchPokemonSpecie().first { it.id == id })
  }.flowOn(Dispatchers.IO)

  fun pokemonNewFlow(id: Int) = flow {
    emit(pokemonRes.fetchPokemonNew().first { it.id == id })
  }.flowOn(Dispatchers.IO)

  fun pokemonAbilitiesFlow(id: Int) = flow {
    emit(
      arrayListOf<Ability>().apply {
        pokemonRes.fetchPokemonAbilities().filter { it.pokemonId == id }.forEach { pokemonAbility ->
          add(pokemonRes.fetchAbilities().first { it.num == pokemonAbility.abilityId })
        }
      }.toList()
    )
  }.flowOn(Dispatchers.IO)

  fun pokemonSpecieEggGroup(id: Int) = flow {
    emit(pokemonRes.fetchSpeciesEggGroup(id))
  }.flowOn(Dispatchers.IO)

  fun specieFlavorTextsFlow(id: Int) = flow {
    if (pokemonRes.fetchSpecieFlavorText(id).isEmpty()) {
      emit(pokemonRes.fetchSpecieFlavorBaseText(id).description)
    } else {
      emit(pokemonRes.fetchSpecieFlavorText(id).last().flavorText)
    }
  }.flowOn(Dispatchers.IO)
}