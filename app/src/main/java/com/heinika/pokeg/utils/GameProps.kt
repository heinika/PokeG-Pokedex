package com.heinika.pokeg.utils

import androidx.compose.ui.graphics.Color
import com.heinika.pokeg.R
import com.heinika.pokeg.ui.theme.*

enum class EvolutionProps(val color: Color, val nameResId: Int, val flavorResId: Int, val imageResId: Int) {
  FireStone(fireColor,R.string.Fire_Stone, R.string.Fire_Stone_flavor, R.drawable.evolution_prop_1),
  ThunderStone(electricColor,R.string.Thunder_Stone, R.string.Thunder_Stone_flavor, R.drawable.evolution_prop_2),
  WaterStone(waterColor,R.string.Water_Stone, R.string.Water_Stone_flavor, R.drawable.evolution_prop_3),
  LeafStone(grassColor,R.string.Leaf_Stone, R.string.Leaf_Stone_flavor, R.drawable.evolution_prop_4),
  MoonStone(normalColor,R.string.Moon_Stone, R.string.Moon_Stone_flavor, R.drawable.evolution_prop_5),
  SunStone(rockColor,R.string.Sun_Stone, R.string.Sun_Stone_flavor, R.drawable.evolution_prop_6),
  ShinyStone(fairyColor,R.string.Shiny_Stone, R.string.Shiny_Stone_flavor, R.drawable.evolution_prop_7),
  DuskStone(darkColor,R.string.Dusk_Stone, R.string.Dusk_Stone_flavor, R.drawable.evolution_prop_8),
  DawnStone(steelColor,R.string.Dawn_Stone, R.string.Dawn_Stone_flavor, R.drawable.evolution_prop_9),
  IceStone(iceColor,R.string.Ice_Stone, R.string.Ice_Stone_flavor, R.drawable.evolution_prop_10),
  SweetApple(Color.Red,R.string.Sweet_Apple, R.string.Sweet_Apple_flavor, R.drawable.evolution_prop_11),
  TartApple(grassColor,R.string.Tart_Apple, R.string.Tart_Apple_flavor, R.drawable.evolution_prop_12),
  CrackedPot(bugColor,R.string.Cracked_Pot, R.string.Cracked_Pot_flavor, R.drawable.evolution_prop_13),
  ChippedPot(dragonColor,R.string.Chipped_Pot, R.string.Chipped_Pot_flavor, R.drawable.evolution_prop_14),
  GalaricaCuff(psychicColor,R.string.Galarica_Cuff, R.string.Galarica_Cuff_flavor, R.drawable.evolution_prop_15),
  GalaricaWreath(grassColor,R.string.Galarica_Wreath, R.string.Galarica_Wreath_flavor, R.drawable.evolution_prop_16)
}