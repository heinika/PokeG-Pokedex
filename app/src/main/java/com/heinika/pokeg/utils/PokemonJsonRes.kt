package com.heinika.pokeg.utils

import android.app.Application
import androidx.annotation.WorkerThread
import com.heinika.pokeg.model.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import timber.log.Timber
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("BlockingMethodInNonBlockingContext")
@Singleton
class PokemonJsonRes @Inject constructor(
  private val context: Application,
  private val moshi: Moshi
) {
  private val pokemonMoveVersionList: MutableList<PokemonMoveVersion> = mutableListOf()
  private val pokemonSpecieList: MutableList<PokemonSpecie> = mutableListOf()

  private val abilityList: List<Ability> by lazy {
    val abilityList: MutableList<Ability> = mutableListOf()
    try {
      val moshi = Moshi.Builder().build()
      val listMyData: Type =
        Types.newParameterizedType(MutableList::class.java, Ability::class.java)
      val adapter: JsonAdapter<List<Ability>> = moshi.adapter(listMyData)

      abilityList.addAll(
        adapter.fromJson(
          context.assets.open("abilitiesList.json").bufferedReader().use { it.readText() })!!
      )
    } catch (e: java.lang.Exception) {
      Timber.i(e)
    }
    abilityList
  }

  fun getAbilityName(id: Int): String {
    return if (id == 0) {
      ""
    } else {
      abilityList[id - 1].cname
    }
  }

  fun getAbilityDesc(id: Int): String {
    return if (id == 0) {
      ""
    } else {
      abilityList[id - 1].synopsis
    }
  }


  fun fetchPokemonMoveVersionList(id: Int): List<Int> {
    if (pokemonMoveVersionList.isEmpty()) {
      pokemonMoveVersionList.addAll(fetchListByJson("pokemon_move_version_list.json"))
    }
    return pokemonMoveVersionList.first { it.id == id }.versionList
  }


  @WorkerThread
  fun fetchPokemonMoveList(pokemonId: Int, version: Int): List<PokemonMove> {
    return fetchListByJson<PokemonMoveResult>("pokemon_move_$version.json")
      .first { it.id == pokemonId }.moves.toList()
  }


  @WorkerThread
  fun fetchMovesDetail(moveIds: List<Int>): List<Move> {
    return fetchListByJson<Move>("moves.json").filter { moveIds.contains(it.id) }
  }

  @WorkerThread
  fun fetchPokemonSpecie(): List<PokemonSpecie> {
    if (pokemonSpecieList.isEmpty()) {
      pokemonSpecieList.addAll(fetchListByJson("pokemon_species.json"))
    }
    return pokemonSpecieList
  }

  @WorkerThread
  private inline fun <reified T> fetchListByJson(jsonFileName: String): List<T> {
    val type = Types.newParameterizedType(MutableList::class.java, T::class.java)
    val adapter: JsonAdapter<List<T>> = moshi.adapter(type)
    return adapter.fromJson(
      context.assets.open(jsonFileName).bufferedReader().use { it.readText() })!!
  }

}
