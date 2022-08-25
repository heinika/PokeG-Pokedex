package com.heinika.pokeg.repository

import com.heinika.pokeg.repository.res.PokemonRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AbilityRepository @Inject constructor(
  private val pokemonRes: PokemonRes
) : Repository {

  fun allPokemonAbilitiesFlow() = flow {
    emit(
      pokemonRes.fetchPokemonAbilities()
    )
  }.flowOn(Dispatchers.IO)
}