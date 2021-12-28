package com.heinika.pokeg.repository

import com.heinika.pokeg.repository.res.PokemonRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NatureRepository @Inject constructor(
  private val pokemonRes: PokemonRes
) : Repository {

  fun natureListFlow() = flow {
    emit(pokemonRes.fetchNatureList())
  }.flowOn(Dispatchers.IO)
}