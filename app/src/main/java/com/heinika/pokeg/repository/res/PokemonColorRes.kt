package com.heinika.pokeg.repository.res

import android.app.Application
import com.heinika.pokeg.utils.PokemonProp
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonColorRes @Inject constructor(private val context: Application) {

  fun getTypeColor(type: Int): Int = PokemonProp.getTypeColor(context, type)

}