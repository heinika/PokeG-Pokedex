package com.heinika.pokeg.repository.res

import android.app.Application
import androidx.annotation.WorkerThread
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.model.PokemonAbility
import com.heinika.pokeg.model.PokemonMove
import com.heinika.pokeg.model.PokemonName
import com.heinika.pokeg.model.PokemonSpecie
import com.heinika.pokeg.model.SpecieFlavorBaseText
import com.heinika.pokeg.model.SpecieFlavorText
import com.heinika.pokeg.model.SpeciesEggGroup
import com.heinika.pokeg.model.SpeciesEvolutionChain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRes @Inject constructor(
  private val context: Application,
  private val jsonRes: PokemonJsonRes
) {
  fun getTypeColor(type: Int): Int = ResUtils.getTypeColor(type, context)

  fun getMoveName(id: Int) = ResUtils.getMoveName(id, context)

  fun getTypeString(id: Int): String = ResUtils.getTypeString(id, context)

  fun getDamageClassName(id: Int) = ResUtils.getDamageClassName(id, context)

  fun getNameById(id: Int, name: String, form: Int = 0): String =
    ResUtils.getNameById(id, name, form,context)

  @WorkerThread
  fun fetchPokemonMoveVersionList(id: Int, speciesId: Int): List<Int> =
    jsonRes.fetchPokemonMoveVersionList(id, speciesId)

  @WorkerThread
  fun fetchPokemonMoveList(pokemonId: Int, speciesId: Int, version: Int): List<PokemonMove> =
    jsonRes.fetchPokemonMoveList(pokemonId, speciesId, version)

  @WorkerThread
  fun fetchSpeciesEggGroup(specieId: Int): List<SpeciesEggGroup> =
    jsonRes.fetchSpeciesEggGroup(specieId)

  @WorkerThread
  fun fetchSpeciesEvolutionChain(): List<SpeciesEvolutionChain> =
    jsonRes.fetchSpeciesEvolutionChain()

  @WorkerThread
  fun fetchSpecieFlavorText(specieId: Int): List<SpecieFlavorText> =
    jsonRes.fetchSpecieFlavorText(specieId)

  @WorkerThread
  fun fetchSpecieFlavorBaseText(specieId: Int): SpecieFlavorBaseText =
    jsonRes.fetchSpecieFlavorBaseText(specieId)

  @WorkerThread
  fun fetchPokemonName(): List<PokemonName> = jsonRes.fetchPokemonName()

  @WorkerThread
  fun fetchPokemonSpecies(): List<PokemonSpecie> = jsonRes.fetchPokemonSpecie()

  @WorkerThread
  fun fetchPokemon(): List<Pokemon> = jsonRes.fetchPokemon()

  @WorkerThread
  fun fetchPokemonAbilities(): List<PokemonAbility> = jsonRes.fetchPokemonAbility()

}
