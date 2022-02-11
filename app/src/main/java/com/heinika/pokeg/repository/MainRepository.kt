package com.heinika.pokeg.repository

import android.app.Application
import androidx.annotation.WorkerThread
import com.heinika.pokeg.PokemonDataCache
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.repository.res.PokemonRes
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import timber.log.Timber
import java.io.File
import java.lang.reflect.Type
import javax.inject.Inject

class MainRepository @Inject constructor(
  private val pokemonRes: PokemonRes,
  private val moshi: Moshi,
  private val pokeGApp: Application
) : Repository {

  @WorkerThread
  fun fetchPokemonList(
    onStart: () -> Unit,
    onSuccess: () -> Unit,
    onError: (String?) -> Unit
  ) = flow {
    try {
      val pokemonList = pokemonRes.fetchPokemon()
      PokemonDataCache.pokemonList = pokemonList
      val listMyData: Type = Types.newParameterizedType(MutableList::class.java, Pokemon::class.java)
      val adapter: JsonAdapter<List<Pokemon>> = moshi.adapter(listMyData)
      val json = adapter.toJson(pokemonList)
      File("${pokeGApp.cacheDir}/pokemon.json").writeText(json)
      onSuccess()
      emit(pokemonList)
    } catch (e: Exception) {
      onError(e.message)
      Timber.i(e)
    }

  }.onStart { onStart() }.flowOn(Dispatchers.IO)
}

