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

enum class Pokeball(val nameResId: Int, val flavorResId: Int, val imageResId: Int){
  PokeBall(R.string.Poké_Ball,R.string.Poké_Ball_flavor,R.drawable.pokeball_prop_1),
  GreatBall(R.string.Great_Ball,R.string.Great_Ball_flavor,R.drawable.pokeball_prop_2),
  UltraBall(R.string.Ultra_Ball,R.string.Ultra_Ball_flavor,R.drawable.pokeball_prop_3),
  SafariBall(R.string.Safari_Ball,R.string.Safari_Ball_flavor,R.drawable.pokeball_prop_4),
  MasterBall(R.string.Master_Ball,R.string.Master_Ball_flavor,R.drawable.pokeball_prop_5),
  FastBall(R.string.Fast_Ball,R.string.Fast_Ball_flavor,R.drawable.pokeball_prop_6),
  LevelBall(R.string.Level_Ball,R.string.Level_Ball_flavor,R.drawable.pokeball_prop_7),
  LureBall(R.string.Lure_Ball,R.string.Lure_Ball_flavor,R.drawable.pokeball_prop_8),
  HeavyBall(R.string.Heavy_Ball,R.string.Heavy_Ball_flavor,R.drawable.pokeball_prop_9),
  LoveBall(R.string.Love_Ball,R.string.Love_Ball_flavor,R.drawable.pokeball_prop_10),
  FriendBall(R.string.Friend_Ball,R.string.Friend_Ball_flavor,R.drawable.pokeball_prop_11),
  MoonBall(R.string.Moon_Ball,R.string.Moon_Ball_flavor,R.drawable.pokeball_prop_12),
  SportBall(R.string.Sport_Ball,R.string.Sport_Ball_flavor,R.drawable.pokeball_prop_13),
  ParkBall(R.string.Park_Ball,R.string.Park_Ball_flavor,R.drawable.pokeball_prop_14),
  NetBall(R.string.Net_Ball,R.string.Net_Ball_flavor,R.drawable.pokeball_prop_15),
  DiveBall(R.string.Dive_Ball,R.string.Dive_Ball_flavor,R.drawable.pokeball_prop_16),
  NestBall(R.string.Nest_Ball,R.string.Nest_Ball_flavor,R.drawable.pokeball_prop_17),
  RepeatBall(R.string.Repeat_Ball,R.string.Repeat_Ball_flavor,R.drawable.pokeball_prop_18),
  TimerBall(R.string.Timer_Ball,R.string.Timer_Ball_flavor,R.drawable.pokeball_prop_19),
  LuxuryBall(R.string.Luxury_Ball,R.string.Luxury_Ball_flavor,R.drawable.pokeball_prop_20),
  PremierBall(R.string.Premier_Ball,R.string.Premier_Ball_flavor,R.drawable.pokeball_prop_21),
  DuskBall(R.string.Dusk_Ball,R.string.Dusk_Ball_flavor,R.drawable.pokeball_prop_22),
  HealBall(R.string.Heal_Ball,R.string.Heal_Ball_flavor,R.drawable.pokeball_prop_23),
  QuickBall(R.string.Quick_Ball,R.string.Quick_Ball_flavor,R.drawable.pokeball_prop_24),
  CherishBall(R.string.Cherish_Ball,R.string.Cherish_Ball_flavor,R.drawable.pokeball_prop_25),
  DreamBall(R.string.Dream_Ball,R.string.Dream_Ball_flavor,R.drawable.pokeball_prop_26),
  BeastBall(R.string.Beast_Ball,R.string.Beast_Ball_flavor,R.drawable.pokeball_prop_27),
}

enum class Fossil(val nameResId: Int, val flavorResId: Int, val imageResId: Int){
  Helix_Fossil(R.string.Helix_Fossil,R.string.Helix_Fossil_flavor,R.drawable.fossil_prop_1),
  Dome_Fossil(R.string.Dome_Fossil,R.string.Dome_Fossil_flavor,R.drawable.fossil_prop_2),
  Old_Amber(R.string.Old_Amber,R.string.Old_Amber_flavor,R.drawable.fossil_prop_3),
  Root_Fossil(R.string.Root_Fossil,R.string.Root_Fossil_flavor,R.drawable.fossil_prop_4),
  Claw_Fossil(R.string.Claw_Fossil,R.string.Claw_Fossil_flavor,R.drawable.fossil_prop_5),
  Skull_Fossil(R.string.Skull_Fossil,R.string.Skull_Fossil_flavor,R.drawable.fossil_prop_6),
  Armor_Fossil(R.string.Armor_Fossil,R.string.Armor_Fossil_flavor,R.drawable.fossil_prop_7),
  Cover_Fossil(R.string.Cover_Fossil,R.string.Cover_Fossil_flavor,R.drawable.fossil_prop_8),
  Plume_Fossil(R.string.Plume_Fossil,R.string.Plume_Fossil_flavor,R.drawable.fossil_prop_9),
  Jaw_Fossil(R.string.Jaw_Fossil,R.string.Jaw_Fossil_flavor,R.drawable.fossil_prop_10),
  Sail_Fossil(R.string.Sail_Fossil,R.string.Sail_Fossil_flavor,R.drawable.fossil_prop_11),
  Fossilized_Bird(R.string.Fossilized_Bird,R.string.Fossilized_Bird_flavor,R.drawable.fossil_prop_12),
  Fossilized_Fish(R.string.Fossilized_Fish,R.string.Fossilized_Fish_flavor,R.drawable.fossil_prop_13),
  Fossilized_Drake(R.string.Fossilized_Drake,R.string.Fossilized_Drake_flavor,R.drawable.fossil_prop_14),
  Fossilized_Dino(R.string.Fossilized_Dino,R.string.Fossilized_Dino_flavor,R.drawable.fossil_prop_15),
}

enum class SwapProps(val nameResId: Int, val flavorResId: Int, val imageResId: Int){
  Red_Shard(R.string.Red_Shard,R.string.Red_Shard_flavor,R.drawable.swap_prop_1),
  Blue_Shard(R.string.Blue_Shard,R.string.Blue_Shard_flavor,R.drawable.swap_prop_2),
  Yellow_Shard(R.string.Yellow_Shard,R.string.Yellow_Shard_flavor,R.drawable.swap_prop_3),
  Green_Shard(R.string.Green_Shard,R.string.Green_Shard_flavor,R.drawable.swap_prop_4),
  Shoal_Salt(R.string.Shoal_Salt,R.string.Shoal_Salt_flavor,R.drawable.swap_prop_5),
  Shoal_Shell(R.string.Shoal_Shell,R.string.Shoal_Shell_flavor,R.drawable.swap_prop_6),
  Heart_Scale(R.string.Heart_Scale,R.string.Heart_Scale_flavor,R.drawable.swap_prop_7),
  Galarica_Twig(R.string.Galarica_Twig,R.string.Galarica_Twig_flavor,R.drawable.swap_prop_8),
  Armorite_Ore(R.string.Armorite_Ore,R.string.Armorite_Ore_flavor,R.drawable.swap_prop_9),
  Dynite_Ore(R.string.Dynite_Ore,R.string.Dynite_Ore_flavor,R.drawable.swap_prop_10),
  Mysterious_Shard_S(R.string.Mysterious_Shard_S,R.string.Mysterious_Shard_S_flavor,R.drawable.swap_prop_11),
  Mysterious_Shard_L(R.string.Mysterious_Shard_L,R.string.Mysterious_Shard_L_flavor,R.drawable.swap_prop_12),
}