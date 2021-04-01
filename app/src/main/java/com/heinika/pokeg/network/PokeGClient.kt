package com.heinika.pokeg.network

import com.heinika.pokeg.model.PokemonInfo
import com.heinika.pokeg.model.PokemonResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class PokeGClient @Inject constructor(private val pokeGService: PokeGService) {
  suspend fun fetchPokemonList(): ApiResponse<PokemonResponse> =
    pokeGService.fetchPokemonList()

  suspend fun fetchPokemonInfo(
    name: String
  ): ApiResponse<PokemonInfo> =
    pokeGService.fetchPokemonInfo(
      name = name
    )
}