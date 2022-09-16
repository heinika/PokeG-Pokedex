package com.heinika.pokeg

import com.heinika.pokeg.ui.theme.ColorTheme
import com.tencent.mmkv.MMKV

object ConfigMMKV {
  private var kv = MMKV.defaultMMKV()
  private const val DEFAULT_VERSION_KEY = "DEFAULT_VERSION_KEY"
  private const val FAVORITE_POKEMONS = "FAVORITE_POKEMONS"
  private const val COLOR_THEME_KEY = "COLOR_THEME_KEY"

  var defaultVersion: Int
    set(value) {
      kv.putInt(DEFAULT_VERSION_KEY, value)
    }
    get() {
      return kv.getInt(DEFAULT_VERSION_KEY, 20)
    }

  var favoritePokemons: Set<String>
    set(value) {
      kv.putStringSet(FAVORITE_POKEMONS, value)
    }
    get() {
      return kv.getStringSet(FAVORITE_POKEMONS, emptySet())!!
    }

  fun isFavoritePokemon(pokemonId: Int) = favoritePokemons.contains(pokemonId.toString())

  var colorTheme: ColorTheme
    set(value){
      kv.putString(COLOR_THEME_KEY, value.name)
    }
    get() = when(kv.getString(COLOR_THEME_KEY,ColorTheme.DARK.name)){
      ColorTheme.DARK.name -> ColorTheme.DARK
      ColorTheme.LIGHT.name -> ColorTheme.LIGHT
      ColorTheme.AUTO.name -> ColorTheme.AUTO
      else -> ColorTheme.DARK
    }

}