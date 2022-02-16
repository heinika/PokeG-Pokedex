package com.heinika.pokeg

import com.heinika.pokeg.info.DexType
import com.heinika.pokeg.model.Pokemon

var curDexType = DexType.Global

object PokemonDataCache {
  lateinit var pokemonList: List<Pokemon>
}