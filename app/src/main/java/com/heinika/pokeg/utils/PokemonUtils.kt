package com.heinika.pokeg.utils

import android.content.Context
import com.heinika.pokeg.R
import timber.log.Timber


object PokemonUtils {

  fun getTypeColor(type: String): Int {
    return when (type) {
      "fighting" -> R.color.fighting
      "flying" -> R.color.flying
      "poison" -> R.color.poison
      "ground" -> R.color.ground
      "rock" -> R.color.rock
      "bug" -> R.color.bug
      "ghost" -> R.color.ghost
      "steel" -> R.color.steel
      "fire" -> R.color.fire
      "water" -> R.color.water
      "grass" -> R.color.grass
      "electric" -> R.color.electric
      "psychic" -> R.color.psychic
      "ice" -> R.color.ice
      "dragon" -> R.color.dragon
      "fairy" -> R.color.fairy
      "dark" -> R.color.dark
      "normal" -> R.color.gray_21
      else -> R.color.gray_21
    }
  }

  fun getNameById(context: Context, id: Int, name: String): String =
    if (id <= 898) {
      getResString(context, "pkoemon_name_$id")
    } else {
      name
    }


  fun getTypeString(context: Context, type: String): String {
    return getResString(context, "type_$type")
  }

  private fun getResString(context: Context, name: String): String {
    return try {
      context.resources.getString(context.resources.getIdentifier(name, "string", context.packageName))
    } catch (e: Exception) {
      Timber.e("no resource name $name")
      ""
    }
  }

}
