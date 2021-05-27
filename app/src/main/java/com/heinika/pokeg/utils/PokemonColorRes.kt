package com.heinika.pokeg.utils

import android.app.Application
import androidx.core.content.ContextCompat
import com.heinika.pokeg.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonColorRes @Inject constructor(private val context: Application) {

  fun getTypeColor(type: Int): Int {
    val resId = when (type) {
      1 -> R.color.gray_21
      2 -> R.color.fighting
      3 -> R.color.flying
      4 -> R.color.poison
      5 -> R.color.ground
      6 -> R.color.rock
      7 -> R.color.bug
      8 -> R.color.ghost
      9 -> R.color.steel
      10 -> R.color.fire
      11 -> R.color.water
      12 -> R.color.grass
      13 -> R.color.electric
      14 -> R.color.psychic
      15 -> R.color.ice
      16 -> R.color.dragon
      17 -> R.color.dark
      18 -> R.color.fairy
      else -> R.color.gray_21
    }
    return ContextCompat.getColor(context, resId)
  }

  fun getTypeColor(type: String): Int {
    val resId = when (type) {
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
    return ContextCompat.getColor(context, resId)
  }

  //  fun getMegaDrawable(id: Int): List<Drawable?> {
//    if (id == 6 || id == 150) {
//      return listOf(
//        getResDrawable(String.format("a%03dmegax", id)),
//        getResDrawable(String.format("a%03dmegay", id))
//      )
//    }
//    return listOf(getResDrawable(String.format("a%03dmega", id)))
//  }

//  fun getMaxDrawable(id: Int): Drawable? {
//    return getResDrawable(String.format("max%03d", id))
//  }

//  @SuppressLint("UseCompatLoadingForDrawables")
//  private fun getResDrawable(name: String): Drawable? {
//    return try {
//      context.resources.getDrawable(
//        context.resources.getIdentifier(
//          name,
//          "drawable",
//          context.packageName
//        ), context.theme
//      )
//    } catch (e: java.lang.Exception) {
//      Timber.e("no resource name $name")
//      null
//    }
//  }

  //  fun getCommonDrawable(id: Int): Drawable = when (id) {
//    in 1..898 -> getResDrawable(String.format("a%03d", id))!!
//    else -> getResDrawable("ic_launcher_foreground")!!
//  }

//  @SuppressLint("UseCompatLoadingForDrawables")
//  private fun getResDrawable(name: String): Drawable? {
//    return try {
//      context.resources.getDrawable(
//        context.resources.getIdentifier(
//          name,
//          "drawable",
//          context.packageName
//        ), context.theme
//      )
//    } catch (e: java.lang.Exception) {
//      Timber.e("no resource name $name")
//      null
//    }
//  }
}