package com.heinika.pokeg.utils

import android.content.Context
import com.heinika.pokeg.R

object PokemonProp {
  enum class BaseStatus(private val resId: Int, private val resColor: Int) {
    HP(R.string.hp, R.color.colorPrimary),
    ATK(R.string.atk, R.color.md_orange_100),
    DEF(R.string.def, R.color.md_blue_200),
    SP_ATK(R.string.sp_atk, R.color.flying),
    SP_DEF(R.string.sp_def, R.color.md_green_200),
    SPEED(R.string.spd, R.color.poison);

    fun getName(context: Context): CharSequence {
      return context.getString(resId)
    }

    fun getColor(context: Context): Int {
      return context.getColor(resColor)
    }
  }

  enum class BodyStatus(private val resId: Int, private val resColor: Int) {
    WEIGHT(R.string.weight, R.color.weight),
    HEIGHT(R.string.height, R.color.height);

    fun getName(context: Context): CharSequence {
      return context.getString(resId)
    }

    fun getColor(context: Context): Int {
      return context.getColor(resColor)
    }
  }

  enum class Tag(
    private val resId: Int,
    private val resColor: Int
  ) {
    Favorite(R.string.favorite, R.color.favorite),
    Legendary(R.string.legendary, R.color.legendary),
    Mythical(R.string.mythical, R.color.mythical),
    BABY(R.string.baby, R.color.baby);

    fun getName(context: Context): CharSequence {
      return context.getString(resId)
    }

    fun getColor(context: Context): Int {
      return context.getColor(resColor)
    }
  }

  enum class Type(val typeId: Int, val typeNameResId: Int, val typeColorResId: Int) {
    NORMAL(1, R.string.type_normal, R.color.gray_21),
    FIGHTING(2, R.string.type_fighting, R.color.fighting),
    FLYING(3, R.string.type_flying, R.color.flying),
    POISON(4, R.string.type_poison, R.color.poison),
    GROUND(5, R.string.type_ground, R.color.ground),
    ROCK(6, R.string.type_rock, R.color.rock),
    BUG(7, R.string.type_bug, R.color.bug),
    GHOST(8, R.string.type_ghost, R.color.ghost),
    STEEL(9, R.string.type_steel, R.color.steel),
    FIRE(10, R.string.type_fire, R.color.fire),
    WATER(11, R.string.type_water, R.color.water),
    GRASS(12, R.string.type_grass, R.color.grass),
    ELECTRIC(13, R.string.type_electric, R.color.electric),
    PSYCHIC(14, R.string.type_psychic, R.color.psychic),
    ICE(15, R.string.type_ice, R.color.ice),
    DRAGON(16, R.string.type_dragon, R.color.dragon),
    DARK(17, R.string.type_dark, R.color.dark),
    FAIRY(18, R.string.type_fairy, R.color.fairy),
    UNKNOWN(-1, R.string.type_unknown, R.color.gray_21);

    fun getName(context: Context): String {
      return context.getString(this.typeNameResId)
    }

    fun getColor(context: Context): Int {
      return context.getColor(this.typeColorResId)
    }
  }

  fun getTypeString(context: Context, id: Int): String {
    return when (id) {
      Type.NORMAL.typeId -> context.getString(Type.NORMAL.typeNameResId)
      Type.FIGHTING.typeId -> context.getString(Type.FIGHTING.typeNameResId)
      Type.FLYING.typeId -> context.getString(Type.FLYING.typeNameResId)
      Type.POISON.typeId -> context.getString(Type.POISON.typeNameResId)
      Type.GROUND.typeId -> context.getString(Type.GROUND.typeNameResId)
      Type.ROCK.typeId -> context.getString(Type.ROCK.typeNameResId)
      Type.BUG.typeId -> context.getString(Type.BUG.typeNameResId)
      Type.GHOST.typeId -> context.getString(Type.GHOST.typeNameResId)
      Type.STEEL.typeId -> context.getString(Type.STEEL.typeNameResId)
      Type.FIRE.typeId -> context.getString(Type.FIRE.typeNameResId)
      Type.WATER.typeId -> context.getString(Type.WATER.typeNameResId)
      Type.GRASS.typeId -> context.getString(Type.GRASS.typeNameResId)
      Type.ELECTRIC.typeId -> context.getString(Type.ELECTRIC.typeNameResId)
      Type.PSYCHIC.typeId -> context.getString(Type.PSYCHIC.typeNameResId)
      Type.ICE.typeId -> context.getString(Type.ICE.typeNameResId)
      Type.DRAGON.typeId -> context.getString(Type.DRAGON.typeNameResId)
      Type.DARK.typeId -> context.getString(Type.DARK.typeNameResId)
      Type.FAIRY.typeId -> context.getString(Type.FAIRY.typeNameResId)
      else -> context.getString(Type.UNKNOWN.typeNameResId)
    }
  }

  fun getTypeColor(context: Context, id: Int): Int {
    return when (id) {
      Type.NORMAL.typeId -> context.getColor(Type.NORMAL.typeColorResId)
      Type.FIGHTING.typeId -> context.getColor(Type.FIGHTING.typeColorResId)
      Type.FLYING.typeId -> context.getColor(Type.FLYING.typeColorResId)
      Type.POISON.typeId -> context.getColor(Type.POISON.typeColorResId)
      Type.GROUND.typeId -> context.getColor(Type.GROUND.typeColorResId)
      Type.ROCK.typeId -> context.getColor(Type.ROCK.typeColorResId)
      Type.BUG.typeId -> context.getColor(Type.BUG.typeColorResId)
      Type.GHOST.typeId -> context.getColor(Type.GHOST.typeColorResId)
      Type.STEEL.typeId -> context.getColor(Type.STEEL.typeColorResId)
      Type.FIRE.typeId -> context.getColor(Type.FIRE.typeColorResId)
      Type.WATER.typeId -> context.getColor(Type.WATER.typeColorResId)
      Type.GRASS.typeId -> context.getColor(Type.GRASS.typeColorResId)
      Type.ELECTRIC.typeId -> context.getColor(Type.ELECTRIC.typeColorResId)
      Type.PSYCHIC.typeId -> context.getColor(Type.PSYCHIC.typeColorResId)
      Type.ICE.typeId -> context.getColor(Type.ICE.typeColorResId)
      Type.DRAGON.typeId -> context.getColor(Type.DRAGON.typeColorResId)
      Type.DARK.typeId -> context.getColor(Type.DARK.typeColorResId)
      Type.FAIRY.typeId -> context.getColor(Type.FAIRY.typeColorResId)
      else -> context.getColor(Type.UNKNOWN.typeColorResId)
    }
  }

}

enum class Generation(
  val id: Int,
  val resId: Int,
  val resColor: Int,
  val filterString: String
) {
  GenerationI(1, R.string.generation_i, R.color.generation_1, "I"),
  GenerationII(2, R.string.generation_ii, R.color.generation_2, "II"),
  GenerationIII(3, R.string.generation_iii, R.color.generation_3, "III"),
  GenerationIV(4, R.string.generation_iv, R.color.generation_4, "IV"),
  GenerationV(5, R.string.generation_v, R.color.generation_5, "V"),
  GenerationVI(6, R.string.generation_vi, R.color.generation_6, "VI"),
  GenerationVII(7, R.string.generation_vii, R.color.generation_7, "VII"),
  GenerationVIII(8, R.string.generation_viii, R.color.generation_8, "VIII");

  fun getName(context: Context): CharSequence {
    return context.getString(resId)
  }

  fun getColor(context: Context): Int {
    return context.getColor(resColor)
  }
}