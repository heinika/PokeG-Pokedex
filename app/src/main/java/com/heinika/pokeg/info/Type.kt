package com.heinika.pokeg.info

import android.content.Context
import androidx.compose.ui.graphics.Color
import com.heinika.pokeg.R
import com.heinika.pokeg.ui.theme.*

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

  val attackHalfList:List<Type>
    get() = when(this){
      NORMAL -> listOf(GROUND,STEEL)
      FIGHTING -> listOf(FLYING,POISON,BUG,PSYCHIC,FAIRY)
      FLYING -> listOf(ROCK,STEEL,ELECTRIC)
      POISON -> listOf(POISON,GROUND,ROCK,GHOST)
      GROUND -> listOf(BUG,GRASS)
      ROCK -> listOf(FIGHTING,GROUND,STEEL)
      BUG -> listOf(FIGHTING,STEEL,POISON,GHOST,STEEL,FIRE,FAIRY)
      GHOST -> listOf(DARK)
      STEEL -> listOf(STEEL,FIRE,WATER,ELECTRIC)
      FIRE -> listOf(ROCK,FIRE,WATER,DRAGON)
      WATER -> listOf(WATER,GRASS,DRAGON)
      GRASS -> listOf(FLYING,POISON,BUG,STEEL,FIRE,GRASS,DRAGON)
      ELECTRIC -> listOf(GRASS,ELECTRIC,DRAGON)
      PSYCHIC -> listOf(STEEL,PSYCHIC)
      ICE -> listOf(STEEL,FIRE,WATER,ICE)
      DRAGON -> listOf(STEEL)
      DARK -> listOf(FIGHTING,DARK,FAIRY)
      FAIRY -> listOf(POISON,STEEL,FIRE)
      UNKNOWN -> emptyList()
    }

  val attackZeroList:List<Type>
    get() = when(this){
      NORMAL -> listOf(GHOST)
      FIGHTING -> listOf(GHOST)
      FLYING -> emptyList()
      POISON -> listOf(STEEL)
      GROUND -> listOf(GROUND)
      ROCK -> emptyList()
      BUG -> emptyList()
      GHOST -> listOf(NORMAL)
      STEEL -> emptyList()
      FIRE -> emptyList()
      WATER -> emptyList()
      GRASS -> emptyList()
      ELECTRIC -> listOf(GROUND)
      PSYCHIC -> listOf(DARK)
      ICE -> emptyList()
      DRAGON -> listOf(FAIRY)
      DARK -> emptyList()
      FAIRY -> emptyList()
      UNKNOWN -> emptyList()
    }

  val attackDoubleList:List<Type>
    get() = when(this){
      NORMAL -> emptyList()
      FIGHTING -> listOf(NORMAL,ROCK,STEEL,ICE,DARK)
      FLYING -> listOf(FIGHTING,BUG,GRASS)
      POISON -> listOf(GRASS,FAIRY)
      GROUND -> listOf(POISON,ROCK)
      ROCK -> listOf(FIGHTING,BUG,FIRE,ICE)
      BUG -> listOf(GRASS,PSYCHIC,DARK)
      GHOST -> listOf(GHOST,PSYCHIC)
      STEEL -> listOf(ROCK,ICE,FAIRY)
      FIRE -> listOf(BUG,STEEL,GRASS,ICE)
      WATER -> listOf(GROUND,ROCK,FIRE)
      GRASS -> listOf(GROUND,ROCK,WATER)
      ELECTRIC -> listOf(FLYING,WATER)
      PSYCHIC -> listOf(FIGHTING,POISON)
      ICE -> listOf(FIGHTING,GROUND,GRASS,DRAGON)
      DRAGON -> listOf(DRAGON)
      DARK -> listOf(GHOST,PSYCHIC)
      FAIRY -> listOf(FIGHTING,DRAGON,DARK)
      UNKNOWN -> emptyList()
    }

  val startColor: Color
    get() = when (this) {
      NORMAL -> startNormalColor
      FIGHTING -> startFightingColor
      FLYING -> startFlyingColor
      POISON -> startPoisonColor
      GROUND -> startGroundColor
      ROCK -> startRockColor
      BUG -> startBugColor
      GHOST -> startGhostColor
      STEEL -> startSteelColor
      FIRE -> startFireColor
      WATER -> startWaterColor
      GRASS -> startGrassColor
      ELECTRIC -> startElectricColor
      PSYCHIC -> startPsychicColor
      ICE -> startIceColor
      DRAGON -> startDragonColor
      DARK -> startDarkColor
      FAIRY -> startFairyColor
      else -> startGrassColor
    }

  val endColor: Color
    get() = when (this) {
      NORMAL -> normalColor
      FIGHTING -> fightingColor
      FLYING -> flyingColor
      POISON -> poisonColor
      GROUND -> groundColor
      ROCK -> rockColor
      BUG -> bugColor
      GHOST -> ghostColor
      STEEL -> steelColor
      FIRE -> fireColor
      WATER -> waterColor
      GRASS -> grassColor
      ELECTRIC -> electricColor
      PSYCHIC -> psychicColor
      ICE -> iceColor
      DRAGON -> dragonColor
      DARK -> darkColor
      FAIRY -> fairyColor
      else -> grassColor
    }

  val darkStartColor: Color
    get() = when (this) {
      NORMAL -> darkStartNormalColor
      FIGHTING -> darkStartFightingColor
      FLYING -> darkStartFlyingColor
      POISON -> darkStartPoisonColor
      GROUND -> darkStartGroundColor
      ROCK -> darkStartRockColor
      BUG -> darkStartBugColor
      GHOST -> darkStartGhostColor
      STEEL -> darkStartSteelColor
      FIRE -> darkStartFireColor
      WATER -> darkStartWaterColor
      GRASS -> darkStartGrassColor
      ELECTRIC -> darkStartElectricColor
      PSYCHIC -> darkStartPsychicColor
      ICE -> darkStartIceColor
      DRAGON -> darkStartDragonColor
      DARK -> darkStartDarkColor
      FAIRY -> darkStartFairyColor
      else -> darkStartGrassColor
    }

  val darkEndColor: Color
    get() = when (this) {
      NORMAL -> darkEndNormalColor
      FIGHTING -> darkEndFightingColor
      FLYING -> darkEndFlyingColor
      POISON -> darkEndPoisonColor
      GROUND -> darkEndGroundColor
      ROCK -> darkEndRockColor
      BUG -> darkEndBugColor
      GHOST -> darkEndGhostColor
      STEEL -> darkEndSteelColor
      FIRE -> darkEndFireColor
      WATER -> darkEndWaterColor
      GRASS -> darkEndGrassColor
      ELECTRIC -> darkEndElectricColor
      PSYCHIC -> darkEndPsychicColor
      ICE -> darkEndIceColor
      DRAGON -> darkEndDragonColor
      DARK -> darkEndDarkColor
      FAIRY -> darkEndFairyColor
      else -> darkEndGrassColor
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