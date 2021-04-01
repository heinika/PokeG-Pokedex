package com.heinika.pokeg.network

import com.heinika.pokeg.model.PokemonInfo
import com.heinika.pokeg.model.PokemonResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeGService {
  @GET("pokemon")
  suspend fun fetchPokemonList(
    @Query("limit") limit: Int = 2000,
    @Query("offset") offset: Int = 0
  ): ApiResponse<PokemonResponse>

  @GET("pokemon/{name}")
  suspend fun fetchPokemonInfo(@Path("name") name: String): ApiResponse<PokemonInfo>
}