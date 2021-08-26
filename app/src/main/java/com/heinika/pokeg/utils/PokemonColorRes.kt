package com.heinika.pokeg.utils

import android.app.Application
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonColorRes @Inject constructor(private val context: Application) {

  fun getTypeColor(type: Int): Int = PokemonProp.getTypeColor(context, type)

}