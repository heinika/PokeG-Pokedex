package com.heinika.pokeg.repository

import androidx.annotation.WorkerThread
import com.heinika.pokeg.PokemonDataCache
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.model.PokemonNew
import com.heinika.pokeg.repository.res.PokemonRes
import com.heinika.pokeg.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import javax.inject.Inject

class MainRepository @Inject constructor(
  private val pokemonRes: PokemonRes
) : Repository {

  @WorkerThread
  fun fetchPokemonList(
    onStart: () -> Unit,
    onSuccess: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {
    try {
      val pokemonList = pokemonRes.fetchPokemonNew().map { pokemon ->
        toPokemon(pokemon)
      }
      PokemonDataCache.pokemonList = pokemonList
      onSuccess()
      emit(pokemonList)
    } catch (e: Exception) {
      onError(e.message)
      Timber.i(e)
    }

  }.onStart { onStart() }.flowOn(Dispatchers.IO)

  private fun toPokemon(pokemon: PokemonNew): Pokemon {
    var totalBaseStat = 0
    var hp = 0
    var atk = 0
    var def = 0
    var spAtk = 0
    var spDef = 0
    var speed = 0
    pokemonRes.fetchPokemonBaseStat().filter { it.pokemonId == pokemon.id }.apply {
      hp = first { it.statId.isHPStat }.baseStat
      atk = first { it.statId.isAttackStat }.baseStat
      def = first { it.statId.isDefenseStat }.baseStat
      spAtk = first { it.statId.isSAttackStat }.baseStat
      spDef = first { it.statId.isSDefenseStat }.baseStat
      speed = first { it.statId.isSPeedStat }.baseStat
    }.forEach {
      totalBaseStat += it.baseStat
    }
    return Pokemon(
      id = pokemon.id,
      speciesId = pokemon.speciesId,
      name = pokemon.identifier,
      types = pokemonRes.fetchPokemonType().filter { it.pokemonId == pokemon.id },
      totalBaseStat = totalBaseStat,
      hp, atk, def, spAtk, spDef, speed
    )
  }
}

