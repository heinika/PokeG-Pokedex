package com.heinika.pokeg.network

import com.heinika.pokeg.model.PokemonInfo
import com.heinika.pokeg.model.PokemonResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class PokeGClient @Inject constructor(private val pokeGService: PokeGService) {
  suspend fun fetchPokemonList(
    page: Int
  ): ApiResponse<PokemonResponse> =
    pokeGService.fetchPokemonList(
      limit = PAGING_SIZE,
      offset = page * PAGING_SIZE
    )

  suspend fun fetchPokemonInfo(
    name: String
  ): ApiResponse<PokemonInfo> =
    pokeGService.fetchPokemonInfo(
      name = name
    )

  companion object {
    private const val PAGING_SIZE = 150
  }
}