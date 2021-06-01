package com.heinika.pokeg.network

import com.heinika.pokeg.model.PokemonInfo
import com.heinika.pokeg.model.PokemonResponse
import retrofit2.Call
import javax.inject.Inject

class PokeGClient @Inject constructor(private val pokeGService: PokeGService) {
  suspend fun fetchPokemonList(): Call<PokemonResponse> =
    pokeGService.fetchPokemonList()

  suspend fun fetchPokemonInfo(
    name: String
  ): Call<PokemonInfo> =
    pokeGService.fetchPokemonInfo(
      name = name
    )
}