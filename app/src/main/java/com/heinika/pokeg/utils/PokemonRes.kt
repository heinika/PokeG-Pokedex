package com.heinika.pokeg.utils

import android.app.Application
import androidx.core.content.ContextCompat
import com.heinika.pokeg.R
import com.heinika.pokeg.model.Ability
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import timber.log.Timber
import java.lang.reflect.Type
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PokemonRes @Inject constructor(private val context: Application) {

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

  fun getNameById(id: Int, name: String): String =
    if (id <= 898) {
      getResString("pkoemon_name_$id")
    } else {
      name
    }


  fun getTypeString(type: String): String {
    return getResString("type_$type")
  }

  private fun getResString(name: String): String {
    return try {
      context.resources.getString(
        context.resources.getIdentifier(
          name,
          "string",
          context.packageName
        )
      )
    } catch (e: Exception) {
      Timber.e("no resource name $name")
      ""
    }
  }

  private val abilityList: List<Ability> by lazy {
    val abilityList: MutableList<Ability> = mutableListOf()
    try {
      val moshi = Moshi.Builder().build()
      val listMyData: Type = Types.newParameterizedType(MutableList::class.java, Ability::class.java)
      val adapter: JsonAdapter<List<Ability>> = moshi.adapter(listMyData)

      abilityList.addAll(adapter.fromJson(context.assets.open("abilitiesList.json").bufferedReader().use { it.readText() })!!)
    } catch (e: java.lang.Exception) {
      Timber.i(e)
    }
    abilityList
  }

  fun getAbilityName(id: Int): String {
    return if (id == 0){
      ""
    }else{
      abilityList[id -1].cname
    }
  }

}
