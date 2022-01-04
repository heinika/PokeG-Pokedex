package com.heinika.pokeg.utils

import androidx.compose.ui.graphics.Color
import com.heinika.pokeg.R
import com.heinika.pokeg.ui.theme.*

sealed class GameProps(val nameResId: Int, val flavorResId: Int, val imageResId: Int)

class EvolutionProp(val color: Color, nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

val evolutionPropList = listOf(
  EvolutionProp(
    fireColor,
    R.string.Fire_Stone,
    R.string.Fire_Stone_flavor,
    R.drawable.evolution_prop_1
  ),
  EvolutionProp(
    electricColor,
    R.string.Thunder_Stone,
    R.string.Thunder_Stone_flavor,
    R.drawable.evolution_prop_2
  ),
  EvolutionProp(
    waterColor,
    R.string.Water_Stone,
    R.string.Water_Stone_flavor,
    R.drawable.evolution_prop_3
  ),
  EvolutionProp(
    grassColor,
    R.string.Leaf_Stone,
    R.string.Leaf_Stone_flavor,
    R.drawable.evolution_prop_4
  ),
  EvolutionProp(
    normalColor,
    R.string.Moon_Stone,
    R.string.Moon_Stone_flavor,
    R.drawable.evolution_prop_5
  ),
  EvolutionProp(
    rockColor,
    R.string.Sun_Stone,
    R.string.Sun_Stone_flavor,
    R.drawable.evolution_prop_6
  ),
  EvolutionProp(
    fairyColor,
    R.string.Shiny_Stone,
    R.string.Shiny_Stone_flavor,
    R.drawable.evolution_prop_7
  ),
  EvolutionProp(
    darkColor,
    R.string.Dusk_Stone,
    R.string.Dusk_Stone_flavor,
    R.drawable.evolution_prop_8
  ),
  EvolutionProp(
    steelColor,
    R.string.Dawn_Stone,
    R.string.Dawn_Stone_flavor,
    R.drawable.evolution_prop_9
  ),
  EvolutionProp(
    iceColor,
    R.string.Ice_Stone,
    R.string.Ice_Stone_flavor,
    R.drawable.evolution_prop_10
  ),
  EvolutionProp(
    Color.Red,
    R.string.Sweet_Apple,
    R.string.Sweet_Apple_flavor,
    R.drawable.evolution_prop_11
  ),
  EvolutionProp(
    grassColor,
    R.string.Tart_Apple,
    R.string.Tart_Apple_flavor,
    R.drawable.evolution_prop_12
  ),
  EvolutionProp(
    bugColor,
    R.string.Cracked_Pot,
    R.string.Cracked_Pot_flavor,
    R.drawable.evolution_prop_13
  ),
  EvolutionProp(
    dragonColor,
    R.string.Chipped_Pot,
    R.string.Chipped_Pot_flavor,
    R.drawable.evolution_prop_14
  ),
  EvolutionProp(
    psychicColor,
    R.string.Galarica_Cuff,
    R.string.Galarica_Cuff_flavor,
    R.drawable.evolution_prop_15
  ),
  EvolutionProp(
    grassColor,
    R.string.Galarica_Wreath,
    R.string.Galarica_Wreath_flavor,
    R.drawable.evolution_prop_16
  )
)

class BattleProp(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

val battlePropList = listOf(
  BattleProp(R.string.Guard_Spec, R.string.Guard_Spec_flavor, R.drawable.battle_prop_1),
  BattleProp(R.string.Dire_Hit, R.string.Dire_Hit_flavor, R.drawable.battle_prop_2),
  BattleProp(R.string.X_Attack, R.string.X_Attack_flavor, R.drawable.battle_prop_3),
  BattleProp(R.string.X_Defense, R.string.X_Defense_flavor, R.drawable.battle_prop_4),
  BattleProp(R.string.X_Speed, R.string.X_Speed_flavor, R.drawable.battle_prop_5),
  BattleProp(R.string.X_Accuracy, R.string.X_Accuracy_flavor, R.drawable.battle_prop_6),
  BattleProp(R.string.X_Sp_Atk, R.string.X_Sp_Atk_flavor, R.drawable.battle_prop_7),
  BattleProp(R.string.X_Sp_Def, R.string.X_Sp_Def_flavor, R.drawable.battle_prop_8),
  BattleProp(R.string.Poké_Doll, R.string.Poké_Doll_flavor, R.drawable.battle_prop_9),
  BattleProp(R.string.Fluffy_Tail, R.string.Fluffy_Tail_flavor, R.drawable.battle_prop_10),
  BattleProp(R.string.Poké_Toy, R.string.Poké_Toy_flavor, R.drawable.battle_prop_11),
  BattleProp(R.string.Max_Mushrooms, R.string.Max_Mushrooms_flavor, R.drawable.battle_prop_12),
)

class RecoveryProp(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

val recoveryPropList = listOf(
  RecoveryProp(R.string.Potion, R.string.Potion_flavor, R.drawable.recovery_prop_1),
  RecoveryProp(R.string.Antidote, R.string.Antidote_flavor, R.drawable.recovery_prop_2),
  RecoveryProp(R.string.Burn_Heal, R.string.Burn_Heal_flavor, R.drawable.recovery_prop_3),
  RecoveryProp(R.string.Ice_Heal, R.string.Ice_Heal_flavor, R.drawable.recovery_prop_4),
  RecoveryProp(R.string.Awakening, R.string.Awakening_flavor, R.drawable.recovery_prop_5),
  RecoveryProp(R.string.Paralyze_Heal, R.string.Paralyze_Heal_flavor, R.drawable.recovery_prop_6),
  RecoveryProp(R.string.Full_Restore, R.string.Full_Restore_flavor, R.drawable.recovery_prop_7),
  RecoveryProp(R.string.Max_Potion, R.string.Max_Potion_flavor, R.drawable.recovery_prop_8),
  RecoveryProp(R.string.Hyper_Potion, R.string.Hyper_Potion_flavor, R.drawable.recovery_prop_9),
  RecoveryProp(R.string.Super_Potion, R.string.Super_Potion_flavor, R.drawable.recovery_prop_10),
  RecoveryProp(R.string.Full_Heal, R.string.Full_Heal_flavor, R.drawable.recovery_prop_11),
  RecoveryProp(R.string.Revive, R.string.Revive_flavor, R.drawable.recovery_prop_12),
  RecoveryProp(R.string.Max_Revive, R.string.Max_Revive_flavor, R.drawable.recovery_prop_13),
  RecoveryProp(R.string.Fresh_Water, R.string.Fresh_Water_flavor, R.drawable.recovery_prop_14),
  RecoveryProp(R.string.Soda_Pop, R.string.Soda_Pop_flavor, R.drawable.recovery_prop_15),
  RecoveryProp(R.string.Lemonade, R.string.Lemonade_flavor, R.drawable.recovery_prop_16),
  RecoveryProp(R.string.Moomoo_Milk, R.string.Moomoo_Milk_flavor, R.drawable.recovery_prop_17),
  RecoveryProp(R.string.Energy_Powder, R.string.Energy_Powder_flavor, R.drawable.recovery_prop_18),
  RecoveryProp(R.string.Energy_Root, R.string.Energy_Root_flavor, R.drawable.recovery_prop_19),
  RecoveryProp(R.string.Heal_Powder, R.string.Heal_Powder_flavor, R.drawable.recovery_prop_20),
  RecoveryProp(R.string.Revival_Herb, R.string.Revival_Herb_flavor, R.drawable.recovery_prop_21),
  RecoveryProp(R.string.Ether, R.string.Ether_flavor, R.drawable.recovery_prop_22),
  RecoveryProp(R.string.Max_Ether, R.string.Max_Ether_flavor, R.drawable.recovery_prop_23),
  RecoveryProp(R.string.Elixir, R.string.Elixir_flavor, R.drawable.recovery_prop_24),
  RecoveryProp(R.string.Max_Elixir, R.string.Max_Elixir_flavor, R.drawable.recovery_prop_25),
  RecoveryProp(R.string.Lava_Cookie, R.string.Lava_Cookie_flavor, R.drawable.recovery_prop_26),
  RecoveryProp(R.string.Berry_Juice, R.string.Berry_Juice_flavor, R.drawable.recovery_prop_27),
  RecoveryProp(R.string.Sacred_Ash, R.string.Sacred_Ash_flavor, R.drawable.recovery_prop_28),
  RecoveryProp(R.string.Rage_Candy_Bar, R.string.Rage_Candy_Bar_flavor, R.drawable.recovery_prop_29),
  RecoveryProp(R.string.Old_Gateau, R.string.Old_Gateau_flavor, R.drawable.recovery_prop_30),
  RecoveryProp(R.string.Sweet_Heart, R.string.Sweet_Heart_flavor, R.drawable.recovery_prop_31),
  RecoveryProp(R.string.Casteliacone, R.string.Casteliacone_flavor, R.drawable.recovery_prop_32),
  RecoveryProp(R.string.Lumiose_Galette, R.string.Lumiose_Galette_flavor, R.drawable.recovery_prop_33),
  RecoveryProp(R.string.Shalour_Sable, R.string.Shalour_Sable_flavor, R.drawable.recovery_prop_34),
  RecoveryProp(R.string.Big_Malasada, R.string.Big_Malasada_flavor, R.drawable.recovery_prop_35),
  RecoveryProp(R.string.Pewter_Crunchies, R.string.Pewter_Crunchies_flavor, R.drawable.recovery_prop_36),
  RecoveryProp(R.string.Max_Honey, R.string.Max_Honey_flavor, R.drawable.recovery_prop_37),
)

class Apricorn(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

val apricornList = listOf(
  Apricorn(R.string.Red_Apricorn, R.string.Red_Apricorn_flavor, R.drawable.apricorn_prop_1),
  Apricorn(R.string.Blue_Apricorn, R.string.Blue_Apricorn_flavor, R.drawable.apricorn_prop_2),
  Apricorn(R.string.Yellow_Apricorn, R.string.Yellow_Apricorn_flavor, R.drawable.apricorn_prop_3),
  Apricorn(R.string.Green_Apricorn, R.string.Green_Apricorn_flavor, R.drawable.apricorn_prop_4),
  Apricorn(R.string.Pink_Apricorn, R.string.Pink_Apricorn_flavor, R.drawable.apricorn_prop_5),
  Apricorn(R.string.White_Apricorn, R.string.White_Apricorn_flavor, R.drawable.apricorn_prop_6),
  Apricorn(R.string.Black_Apricorn, R.string.Black_Apricorn_flavor, R.drawable.apricorn_prop_7)
)

class PreciousProp(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

val preciousPropList = listOf(
  PreciousProp(R.string.Tiny_Mushroom, R.string.Tiny_Mushroom_flavor, R.drawable.precious_prop_1),
  PreciousProp(R.string.Big_Mushroom, R.string.Big_Mushroom_flavor, R.drawable.precious_prop_2),
  PreciousProp(R.string.Pearl, R.string.Pearl_flavor, R.drawable.precious_prop_3),
  PreciousProp(R.string.Big_Pearl, R.string.Big_Pearl_flavor, R.drawable.precious_prop_4),
  PreciousProp(R.string.Stardust, R.string.Stardust_flavor, R.drawable.precious_prop_5),
  PreciousProp(R.string.Star_Piece, R.string.Star_Piece_flavor, R.drawable.precious_prop_6),
  PreciousProp(R.string.Gold_Leaf, R.string.Gold_Leaf_flavor, R.drawable.precious_prop_7),
  PreciousProp(R.string.Silver_Leaf, R.string.Silver_Leaf_flavor, R.drawable.precious_prop_8),
  PreciousProp(R.string.Brick_Piece, R.string.Brick_Piece_flavor, R.drawable.precious_prop_9),
  PreciousProp(R.string.Nugget, R.string.Nugget_flavor, R.drawable.precious_prop_10),
  PreciousProp(R.string.Rare_Bone, R.string.Rare_Bone_flavor, R.drawable.precious_prop_11),
  PreciousProp(R.string.Pretty_Wing, R.string.Pretty_Wing_flavor, R.drawable.precious_prop_12),
  PreciousProp(R.string.Balm_Mushroom, R.string.Balm_Mushroom_flavor, R.drawable.precious_prop_13),
  PreciousProp(R.string.Big_Nugget, R.string.Big_Nugget_flavor, R.drawable.precious_prop_14),
  PreciousProp(R.string.Pearl_String, R.string.Pearl_String_flavor, R.drawable.precious_prop_15),
  PreciousProp(R.string.Comet_Shard, R.string.Comet_Shard_flavor, R.drawable.precious_prop_16),
  PreciousProp(R.string.Relic_Copper, R.string.Relic_Copper_flavor, R.drawable.precious_prop_17),
  PreciousProp(R.string.Relic_Silver, R.string.Relic_Silver_flavor, R.drawable.precious_prop_18),
  PreciousProp(R.string.Relic_Gold, R.string.Relic_Gold_flavor, R.drawable.precious_prop_19),
  PreciousProp(R.string.Relic_Vase, R.string.Relic_Vase_flavor, R.drawable.precious_prop_20),
  PreciousProp(R.string.Relic_Band, R.string.Relic_Band_flavor, R.drawable.precious_prop_21),
  PreciousProp(R.string.Relic_Statue, R.string.Relic_Statue_flavor, R.drawable.precious_prop_22),
  PreciousProp(R.string.Relic_Crown, R.string.Relic_Crown_flavor, R.drawable.precious_prop_23),
  PreciousProp(R.string.Strange_Souvenir, R.string.Strange_Souvenir_flavor, R.drawable.precious_prop_24),
  PreciousProp(R.string.Bottle_Cap, R.string.Bottle_Cap_flavor, R.drawable.precious_prop_25),
  PreciousProp(R.string.Gold_Bottle, R.string.Gold_Bottle_flavor, R.drawable.precious_prop_26),
  PreciousProp(R.string.Stretchy_Spring, R.string.Stretchy_Spring_flavor, R.drawable.precious_prop_27),
  PreciousProp(R.string.Chalky_Stone, R.string.Chalky_Stone_flavor, R.drawable.precious_prop_28),
  PreciousProp(R.string.Marble, R.string.Marble_flavor, R.drawable.precious_prop_29),
  PreciousProp(R.string.Lone_Earring, R.string.Lone_Earring_flavor, R.drawable.precious_prop_30),
  PreciousProp(R.string.Beach_Glass, R.string.Beach_Glass_flavor, R.drawable.precious_prop_31),
  PreciousProp(R.string.Polished_Mud, R.string.Polished_Mud_flavor, R.drawable.precious_prop_32),
  PreciousProp(R.string.Tropical_Shell, R.string.Tropical_Shell_flavor, R.drawable.precious_prop_33),

)

class Mail(val gen: Generation,nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

val mailIIList = listOf(
  Mail(Generation.GenerationII,R.string.Bluesky_Mail, R.string.Bluesky_Mail_flavor, R.drawable.mail_ii_prop_1),
  Mail(Generation.GenerationII,R.string.Eon_Mail, R.string.Eon_Mail_flavor, R.drawable.mail_ii_prop_2),
  Mail(Generation.GenerationII,R.string.Flower_Mail, R.string.Flower_Mail_flavor, R.drawable.mail_ii_prop_3),
  Mail(Generation.GenerationII,R.string.Liteblue_mail, R.string.Liteblue_mail_flavor, R.drawable.mail_ii_prop_4),
  Mail(Generation.GenerationII,R.string.Lovely_Mail, R.string.Lovely_Mail_flavor, R.drawable.mail_ii_prop_5),
  Mail(Generation.GenerationII,R.string.Mirage_Mail, R.string.Mirage_Mail_flavor, R.drawable.mail_ii_prop_6),
  Mail(Generation.GenerationII,R.string.Morph_Mail, R.string.Morph_Mail_flavor, R.drawable.mail_ii_prop_7),
  Mail(Generation.GenerationII,R.string.Portrait_mail, R.string.Portrait_mail_flavor, R.drawable.mail_ii_prop_8),
  Mail(Generation.GenerationII,R.string.Music_Mail, R.string.Music_Mail_flavor, R.drawable.mail_ii_prop_9),
  Mail(Generation.GenerationII,R.string.Surf_Mail, R.string.Surf_Mail_flavor, R.drawable.mail_ii_prop_10),
)

val mailIIIList = listOf(
  Mail(Generation.GenerationIII,R.string.Orange_Mail, R.string.Orange_Mail_flavor, R.drawable.mail_iii_prop_1),
  Mail(Generation.GenerationIII,R.string.Harbor_Mail, R.string.Harbor_Mail_flavor, R.drawable.mail_iii_prop_2),
  Mail(Generation.GenerationIII,R.string.Glitter_Mail, R.string.Glitter_Mail_flavor, R.drawable.mail_iii_prop_3),
  Mail(Generation.GenerationIII,R.string.Mech_Mail, R.string.Mech_Mail_flavor, R.drawable.mail_iii_prop_4),
  Mail(Generation.GenerationIII,R.string.Wood_Mail, R.string.Wood_Mail_flavor, R.drawable.mail_iii_prop_5),
  Mail(Generation.GenerationIII,R.string.Wave_Mail, R.string.Wave_Mail_flavor, R.drawable.mail_iii_prop_6),
  Mail(Generation.GenerationIII,R.string.Bead_Mail, R.string.Bead_Mail_flavor, R.drawable.mail_iii_prop_7),
  Mail(Generation.GenerationIII,R.string.Shadow_Mail, R.string.Shadow_Mail_flavor, R.drawable.mail_iii_prop_8),
  Mail(Generation.GenerationIII,R.string.Tropical_Mail, R.string.Tropical_Mail_flavor, R.drawable.mail_iii_prop_9),
  Mail(Generation.GenerationIII,R.string.Dream_Mail, R.string.Dream_Mail_flavor, R.drawable.mail_iii_prop_10),
  Mail(Generation.GenerationIII,R.string.Miracle_Mail, R.string.Miracle_Mail_flavor, R.drawable.mail_iii_prop_11),
  Mail(Generation.GenerationIII,R.string.Retro_Mail, R.string.Retro_Mail_flavor, R.drawable.mail_iii_prop_12),

)

val mailIVList = listOf(
  Mail(Generation.GenerationIV,R.string.Grass_Mail, R.string.Grass_Mail_flavor, R.drawable.mail_iv_prop_1),
  Mail(Generation.GenerationIV,R.string.Flame_Mail, R.string.Flame_Mail_flavor, R.drawable.mail_iv_prop_2),
  Mail(Generation.GenerationIV,R.string.Bubble_Mail, R.string.Bubble_Mail_flavor, R.drawable.mail_iv_prop_3),
  Mail(Generation.GenerationIV,R.string.Bloom_Mail, R.string.Bloom_Mail_flavor, R.drawable.mail_iv_prop_5),
  Mail(Generation.GenerationIV,R.string.Tunnel_Mail, R.string.Tunnel_Mail_flavor, R.drawable.mail_iv_prop_6),
  Mail(Generation.GenerationIV,R.string.Steel_Mail, R.string.Steel_Mail_flavor, R.drawable.mail_iv_prop_7),
  Mail(Generation.GenerationIV,R.string.Heart_Mail, R.string.Heart_Mail_flavor, R.drawable.mail_iv_prop_8),
  Mail(Generation.GenerationIV,R.string.Snow_Mail, R.string.Snow_Mail_flavor, R.drawable.mail_iv_prop_9),
  Mail(Generation.GenerationIV,R.string.Space_Mail, R.string.Space_Mail_flavor, R.drawable.mail_iv_prop_10),
  Mail(Generation.GenerationIV,R.string.Air_Mail, R.string.Air_Mail_flavor, R.drawable.mail_iv_prop_11),
  Mail(Generation.GenerationIV,R.string.Mosaic_Mail, R.string.Mosaic_Mail_flavor, R.drawable.mail_iv_prop_12),
  Mail(Generation.GenerationIV,R.string.Brick_Mail, R.string.Brick_Mail_flavor, R.drawable.mail_iv_prop_12),
)

val mailVList = listOf(
  Mail(Generation.GenerationV,R.string.Greet_Mail, R.string.Greet_Mail_flavor, R.drawable.mail_v_prop_1),
  Mail(Generation.GenerationV,R.string.Favored_Mail, R.string.Favored_Mail_flavor, R.drawable.mail_v_prop_2),
  Mail(Generation.GenerationV,R.string.RSVP_Mail, R.string.RSVP_Mail_flavor, R.drawable.mail_v_prop_3),
  Mail(Generation.GenerationV,R.string.Thanks_Mail, R.string.Thanks_Mail_flavor, R.drawable.mail_v_prop_4),
  Mail(Generation.GenerationV,R.string.Inquiry_Mail, R.string.Inquiry_Mail_flavor, R.drawable.mail_v_prop_5),
  Mail(Generation.GenerationV,R.string.Like_Mail, R.string.Like_Mail_flavor, R.drawable.mail_v_prop_6),
  Mail(Generation.GenerationV,R.string.Reply_Mail, R.string.Reply_Mail_flavor, R.drawable.mail_v_prop_7),
  Mail(Generation.GenerationV,R.string.Bridge_Mail_S, R.string.Bridge_Mail_S_flavor, R.drawable.mail_v_prop_8),
  Mail(Generation.GenerationV,R.string.Bridge_Mail_D, R.string.Bridge_Mail_D_flavor, R.drawable.mail_v_prop_9),
  Mail(Generation.GenerationV,R.string.Bridge_Mail_T, R.string.Bridge_Mail_T_flavor, R.drawable.mail_v_prop_10),
  Mail(Generation.GenerationV,R.string.Bridge_Mail_V, R.string.Bridge_Mail_V_flavor, R.drawable.mail_v_prop_11),
  Mail(Generation.GenerationV,R.string.Bridge_Mail_M, R.string.Bridge_Mail_M_flavor, R.drawable.mail_v_prop_12),
)


class Pokeball(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

val pokeballList = listOf(
  Pokeball(R.string.Poké_Ball, R.string.Poké_Ball_flavor, R.drawable.pokeball_prop_1),
  Pokeball(R.string.Great_Ball, R.string.Great_Ball_flavor, R.drawable.pokeball_prop_2),
  Pokeball(R.string.Ultra_Ball, R.string.Ultra_Ball_flavor, R.drawable.pokeball_prop_3),
  Pokeball(R.string.Safari_Ball, R.string.Safari_Ball_flavor, R.drawable.pokeball_prop_4),
  Pokeball(R.string.Master_Ball, R.string.Master_Ball_flavor, R.drawable.pokeball_prop_5),
  Pokeball(R.string.Fast_Ball, R.string.Fast_Ball_flavor, R.drawable.pokeball_prop_6),
  Pokeball(R.string.Level_Ball, R.string.Level_Ball_flavor, R.drawable.pokeball_prop_7),
  Pokeball(R.string.Lure_Ball, R.string.Lure_Ball_flavor, R.drawable.pokeball_prop_8),
  Pokeball(R.string.Heavy_Ball, R.string.Heavy_Ball_flavor, R.drawable.pokeball_prop_9),
  Pokeball(R.string.Love_Ball, R.string.Love_Ball_flavor, R.drawable.pokeball_prop_10),
  Pokeball(R.string.Friend_Ball, R.string.Friend_Ball_flavor, R.drawable.pokeball_prop_11),
  Pokeball(R.string.Moon_Ball, R.string.Moon_Ball_flavor, R.drawable.pokeball_prop_12),
  Pokeball(R.string.Sport_Ball, R.string.Sport_Ball_flavor, R.drawable.pokeball_prop_13),
  Pokeball(R.string.Park_Ball, R.string.Park_Ball_flavor, R.drawable.pokeball_prop_14),
  Pokeball(R.string.Net_Ball, R.string.Net_Ball_flavor, R.drawable.pokeball_prop_15),
  Pokeball(R.string.Dive_Ball, R.string.Dive_Ball_flavor, R.drawable.pokeball_prop_16),
  Pokeball(R.string.Nest_Ball, R.string.Nest_Ball_flavor, R.drawable.pokeball_prop_17),
  Pokeball(R.string.Repeat_Ball, R.string.Repeat_Ball_flavor, R.drawable.pokeball_prop_18),
  Pokeball(R.string.Timer_Ball, R.string.Timer_Ball_flavor, R.drawable.pokeball_prop_19),
  Pokeball(R.string.Luxury_Ball, R.string.Luxury_Ball_flavor, R.drawable.pokeball_prop_20),
  Pokeball(R.string.Premier_Ball, R.string.Premier_Ball_flavor, R.drawable.pokeball_prop_21),
  Pokeball(R.string.Dusk_Ball, R.string.Dusk_Ball_flavor, R.drawable.pokeball_prop_22),
  Pokeball(R.string.Heal_Ball, R.string.Heal_Ball_flavor, R.drawable.pokeball_prop_23),
  Pokeball(R.string.Quick_Ball, R.string.Quick_Ball_flavor, R.drawable.pokeball_prop_24),
  Pokeball(R.string.Cherish_Ball, R.string.Cherish_Ball_flavor, R.drawable.pokeball_prop_25),
  Pokeball(R.string.Dream_Ball, R.string.Dream_Ball_flavor, R.drawable.pokeball_prop_26),
  Pokeball(R.string.Beast_Ball, R.string.Beast_Ball_flavor, R.drawable.pokeball_prop_27),
)

class Fossil(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

val fossilList = listOf(
  Fossil(R.string.Helix_Fossil, R.string.Helix_Fossil_flavor, R.drawable.fossil_prop_1),
  Fossil(R.string.Dome_Fossil, R.string.Dome_Fossil_flavor, R.drawable.fossil_prop_2),
  Fossil(R.string.Old_Amber, R.string.Old_Amber_flavor, R.drawable.fossil_prop_3),
  Fossil(R.string.Root_Fossil, R.string.Root_Fossil_flavor, R.drawable.fossil_prop_4),
  Fossil(R.string.Claw_Fossil, R.string.Claw_Fossil_flavor, R.drawable.fossil_prop_5),
  Fossil(R.string.Skull_Fossil, R.string.Skull_Fossil_flavor, R.drawable.fossil_prop_6),
  Fossil(R.string.Armor_Fossil, R.string.Armor_Fossil_flavor, R.drawable.fossil_prop_7),
  Fossil(R.string.Cover_Fossil, R.string.Cover_Fossil_flavor, R.drawable.fossil_prop_8),
  Fossil(R.string.Plume_Fossil, R.string.Plume_Fossil_flavor, R.drawable.fossil_prop_9),
  Fossil(R.string.Jaw_Fossil, R.string.Jaw_Fossil_flavor, R.drawable.fossil_prop_10),
  Fossil(R.string.Sail_Fossil, R.string.Sail_Fossil_flavor, R.drawable.fossil_prop_11),
  Fossil(
    R.string.Fossilized_Bird,
    R.string.Fossilized_Bird_flavor,
    R.drawable.fossil_prop_12
  ),
  Fossil(
    R.string.Fossilized_Fish,
    R.string.Fossilized_Fish_flavor,
    R.drawable.fossil_prop_13
  ),
  Fossil(
    R.string.Fossilized_Drake,
    R.string.Fossilized_Drake_flavor,
    R.drawable.fossil_prop_14
  ),
  Fossil(
    R.string.Fossilized_Dino,
    R.string.Fossilized_Dino_flavor,
    R.drawable.fossil_prop_15
  ),
)

class SwapProps(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

val swapPropList = listOf(
  SwapProps(R.string.Red_Shard, R.string.Red_Shard_flavor, R.drawable.swap_prop_1),
  SwapProps(R.string.Blue_Shard, R.string.Blue_Shard_flavor, R.drawable.swap_prop_2),
  SwapProps(R.string.Yellow_Shard, R.string.Yellow_Shard_flavor, R.drawable.swap_prop_3),
  SwapProps(R.string.Green_Shard, R.string.Green_Shard_flavor, R.drawable.swap_prop_4),
  SwapProps(R.string.Shoal_Salt, R.string.Shoal_Salt_flavor, R.drawable.swap_prop_5),
  SwapProps(R.string.Shoal_Shell, R.string.Shoal_Shell_flavor, R.drawable.swap_prop_6),
  SwapProps(R.string.Heart_Scale, R.string.Heart_Scale_flavor, R.drawable.swap_prop_7),
  SwapProps(R.string.Galarica_Twig, R.string.Galarica_Twig_flavor, R.drawable.swap_prop_8),
  SwapProps(R.string.Armorite_Ore, R.string.Armorite_Ore_flavor, R.drawable.swap_prop_9),
  SwapProps(R.string.Dynite_Ore, R.string.Dynite_Ore_flavor, R.drawable.swap_prop_10),
  SwapProps(
    R.string.Mysterious_Shard_S,
    R.string.Mysterious_Shard_S_flavor,
    R.drawable.swap_prop_11
  ),
  SwapProps(
    R.string.Mysterious_Shard_L,
    R.string.Mysterious_Shard_L_flavor,
    R.drawable.swap_prop_12
  ),
)

class CarryProps(val gen: Generation, nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

val carryIIPropsList = listOf(
  CarryProps(Generation.GenerationII, R.string.Bright_Powder, R.string.Bright_Powder_flavor, R.drawable.carry_ii_prop_1),
  CarryProps(Generation.GenerationII, R.string.Exp_Share, R.string.Exp_Share_flavor, R.drawable.carry_ii_prop_2),
  CarryProps(Generation.GenerationII, R.string.Quick_Claw, R.string.Quick_Claw_flavor, R.drawable.carry_ii_prop_3),
  CarryProps(Generation.GenerationII, R.string.Kings_Rock, R.string.Kings_Rock_flavor, R.drawable.carry_ii_prop_4),
  CarryProps(Generation.GenerationII, R.string.Silver_Powder, R.string.Silver_Powder_flavor, R.drawable.carry_ii_prop_5),
  CarryProps(Generation.GenerationII, R.string.Amulet_Coin, R.string.Amulet_Coin_flavor, R.drawable.carry_ii_prop_6),
  CarryProps(Generation.GenerationII, R.string.Cleanse_Tag, R.string.Cleanse_Tag_flavor, R.drawable.carry_ii_prop_7),
  CarryProps(Generation.GenerationII, R.string.Smoke_Ball, R.string.Smoke_Ball_flavor, R.drawable.carry_ii_prop_8),
  CarryProps(Generation.GenerationII, R.string.Everstone, R.string.Everstone_flavor, R.drawable.carry_ii_prop_9),
  CarryProps(Generation.GenerationII, R.string.Focus_Band, R.string.Focus_Band_flavor, R.drawable.carry_ii_prop_10),
  CarryProps(Generation.GenerationII, R.string.Lucky_Egg, R.string.Lucky_Egg_flavor, R.drawable.carry_ii_prop_11),
  CarryProps(Generation.GenerationII, R.string.Scope_Lens, R.string.Scope_Lens_flavor, R.drawable.carry_ii_prop_12),
  CarryProps(Generation.GenerationII, R.string.Metal_Coat, R.string.Metal_Coat_flavor, R.drawable.carry_ii_prop_13),
  CarryProps(Generation.GenerationII, R.string.Leftovers, R.string.Leftovers_flavor, R.drawable.carry_ii_prop_14),
  CarryProps(Generation.GenerationII, R.string.Dragon_Scale, R.string.Dragon_Scale_flavor, R.drawable.carry_ii_prop_15),
  CarryProps(Generation.GenerationII, R.string.Light_Ball, R.string.Light_Ball_flavor, R.drawable.carry_ii_prop_16),
  CarryProps(Generation.GenerationII, R.string.Soft_Sand, R.string.Soft_Sand_flavor, R.drawable.carry_ii_prop_17),
  CarryProps(Generation.GenerationII, R.string.Hard_Stone, R.string.Hard_Stone_flavor, R.drawable.carry_ii_prop_18),
  CarryProps(Generation.GenerationII, R.string.Miracle_Seed, R.string.Miracle_Seed_flavor, R.drawable.carry_ii_prop_19),
  CarryProps(Generation.GenerationII, R.string.Black_Glasses, R.string.Black_Glasses_flavor, R.drawable.carry_ii_prop_20),
  CarryProps(Generation.GenerationII, R.string.Black_Belt, R.string.Black_Belt_flavor, R.drawable.carry_ii_prop_21),
  CarryProps(Generation.GenerationII, R.string.Magnet_, R.string.Magnet__flavor, R.drawable.carry_ii_prop_22),
  CarryProps(Generation.GenerationII, R.string.Mystic_Water, R.string.Mystic_Water_flavor, R.drawable.carry_ii_prop_23),
  CarryProps(Generation.GenerationII, R.string.Sharp_Beak, R.string.Sharp_Beak_flavor, R.drawable.carry_ii_prop_24),
  CarryProps(Generation.GenerationII, R.string.Poison_Barb, R.string.Poison_Barb_flavor, R.drawable.carry_ii_prop_25),
  CarryProps(Generation.GenerationII, R.string.NeverMelt_Ice, R.string.NeverMelt_Ice_flavor, R.drawable.carry_ii_prop_26),
  CarryProps(Generation.GenerationII, R.string.Spell_Tag, R.string.Spell_Tag_flavor, R.drawable.carry_ii_prop_27),
  CarryProps(Generation.GenerationII, R.string.Twisted_Spoon, R.string.Twisted_Spoon_flavor, R.drawable.carry_ii_prop_28),
  CarryProps(Generation.GenerationII, R.string.Charcoal, R.string.Charcoal_flavor, R.drawable.carry_ii_prop_29),
  CarryProps(Generation.GenerationII, R.string.Dragon_Fang, R.string.Dragon_Fang_flavor, R.drawable.carry_ii_prop_30),
  CarryProps(Generation.GenerationII, R.string.Pink_Bow, R.string.Pink_Bow_flavor, R.drawable.carry_ii_prop_31),
  CarryProps(Generation.GenerationII, R.string.Polkadot_Bow, R.string.Polkadot_Bow_flavor, R.drawable.carry_ii_prop_32),
  CarryProps(Generation.GenerationII, R.string.Berserk_Gene, R.string.Berserk_Gene_flavor, R.drawable.carry_ii_prop_33),
  CarryProps(Generation.GenerationII, R.string.Up_Grade, R.string.Up_Grade_flavor, R.drawable.carry_ii_prop_34),
  CarryProps(Generation.GenerationII, R.string.Lucky_Punch, R.string.Lucky_Punch_flavor, R.drawable.carry_ii_prop_35),
  CarryProps(Generation.GenerationII, R.string.Metal_Powder, R.string.Metal_Powder_flavor, R.drawable.carry_ii_prop_36),
  CarryProps(Generation.GenerationII, R.string.Thick_Club, R.string.Thick_Club_flavor, R.drawable.carry_ii_prop_37),
  CarryProps(Generation.GenerationII, R.string.Stick, R.string.Stick_flavor, R.drawable.carry_ii_prop_38),
  CarryProps(Generation.GenerationII, R.string.Berry, R.string.Berry_flavor, R.drawable.carry_ii_prop_39),
  CarryProps(Generation.GenerationII, R.string.Gold_Berry, R.string.Gold_Berry_flavor, R.drawable.carry_ii_prop_40),
  CarryProps(Generation.GenerationII, R.string.PRZCureBerry, R.string.PRZCureBerry_flavor, R.drawable.carry_ii_prop_41),
  CarryProps(Generation.GenerationII, R.string.PSNCureBerry, R.string.PSNCureBerry_flavor, R.drawable.carry_ii_prop_42),
  CarryProps(Generation.GenerationII, R.string.Bitter_Berry, R.string.Bitter_Berry_flavor, R.drawable.carry_ii_prop_43),
  CarryProps(Generation.GenerationII, R.string.Burnt_Berry, R.string.Burnt_Berry_flavor, R.drawable.carry_ii_prop_44),
  CarryProps(Generation.GenerationII, R.string.Ice_Berry, R.string.Ice_Berry_flavor, R.drawable.carry_ii_prop_45),
  CarryProps(Generation.GenerationII, R.string.Mint_Berry, R.string.Mint_Berry_flavor, R.drawable.carry_ii_prop_46),
  CarryProps(Generation.GenerationII, R.string.MiracleBerry, R.string.MiracleBerry_flavor, R.drawable.carry_ii_prop_47),
  CarryProps(Generation.GenerationII, R.string.MysteryBerry, R.string.MysteryBerry_flavor, R.drawable.carry_ii_prop_48),
)

val carryIIIPropsList = listOf(
  CarryProps(Generation.GenerationIII, R.string.White_Herb, R.string.White_Herb_flavor, R.drawable.carry_iii_prop_1),
  CarryProps(Generation.GenerationIII, R.string.Mental_Herb, R.string.Mental_Herb_flavor, R.drawable.carry_iii_prop_2),
  CarryProps(Generation.GenerationIII, R.string.Macho_Brace, R.string.Macho_Brace_flavor, R.drawable.carry_iii_prop_3),
  CarryProps(Generation.GenerationIII, R.string.Soothe_Bell, R.string.Soothe_Bell_flavor, R.drawable.carry_iii_prop_4),
  CarryProps(Generation.GenerationIII, R.string.Choice_Band, R.string.Choice_Band_flavor, R.drawable.carry_iii_prop_5),
  CarryProps(Generation.GenerationIII, R.string.Soul_Dew, R.string.Soul_Dew_flavor, R.drawable.carry_iii_prop_6),
  CarryProps(Generation.GenerationIII, R.string.Deep_Sea_Tooth, R.string.Deep_Sea_Tooth_flavor, R.drawable.carry_iii_prop_7),
  CarryProps(Generation.GenerationIII, R.string.Deep_Sea_Scale, R.string.Deep_Sea_Scale_flavor, R.drawable.carry_iii_prop_8),
  CarryProps(Generation.GenerationIII, R.string.Silk_Scarf, R.string.Silk_Scarf_flavor, R.drawable.carry_iii_prop_9),
  CarryProps(Generation.GenerationIII, R.string.Shell_Bell, R.string.Shell_Bell_flavor, R.drawable.carry_iii_prop_10),
  CarryProps(Generation.GenerationIII, R.string.Sea_Incense, R.string.Sea_Incense_flavor, R.drawable.carry_iii_prop_11),
  CarryProps(Generation.GenerationIII, R.string.Lax_Incense, R.string.Lax_Incense_flavor, R.drawable.carry_iii_prop_12),
  CarryProps(Generation.GenerationIII, R.string.Red_Scarf, R.string.Red_Scarf_flavor, R.drawable.carry_iii_prop_13),
  CarryProps(Generation.GenerationIII, R.string.Blue_Scarf, R.string.Blue_Scarf_flavor, R.drawable.carry_iii_prop_14),
  CarryProps(Generation.GenerationIII, R.string.Pink_Scarf, R.string.Pink_Scarf_flavor, R.drawable.carry_iii_prop_15),
  CarryProps(Generation.GenerationIII, R.string.Green_Scarf, R.string.Green_Scarf_flavor, R.drawable.carry_iii_prop_16),
  CarryProps(Generation.GenerationIII, R.string.Yellow_Scarf, R.string.Yellow_Scarf_flavor, R.drawable.carry_iii_prop_17),
)

val carryIVPropsList = listOf(
  CarryProps(Generation.GenerationIV, R.string.Flame_Plate, R.string.Flame_Plate_flavor, R.drawable.carry_iv_prop_1),
  CarryProps(Generation.GenerationIV, R.string.Splash_Plate, R.string.Splash_Plate_flavor, R.drawable.carry_iv_prop_2),
  CarryProps(Generation.GenerationIV, R.string.Zap_Plate, R.string.Zap_Plate_flavor, R.drawable.carry_iv_prop_3),
  CarryProps(Generation.GenerationIV, R.string.Meadow_Plate, R.string.Meadow_Plate_flavor, R.drawable.carry_iv_prop_4),
  CarryProps(Generation.GenerationIV, R.string.Icicle_Plate, R.string.Icicle_Plate_flavor, R.drawable.carry_iv_prop_5),
  CarryProps(Generation.GenerationIV, R.string.Fist_Plate, R.string.Fist_Plate_flavor, R.drawable.carry_iv_prop_6),
  CarryProps(Generation.GenerationIV, R.string.Toxic_Plate, R.string.Toxic_Plate_flavor, R.drawable.carry_iv_prop_7),
  CarryProps(Generation.GenerationIV, R.string.Earth_Plate, R.string.Earth_Plate_flavor, R.drawable.carry_iv_prop_8),
  CarryProps(Generation.GenerationIV, R.string.Sky_Plate, R.string.Sky_Plate_flavor, R.drawable.carry_iv_prop_9),
  CarryProps(Generation.GenerationIV, R.string.Mind_Plate, R.string.Mind_Plate_flavor, R.drawable.carry_iv_prop_10),
  CarryProps(Generation.GenerationIV, R.string.Insect_Plate, R.string.Insect_Plate_flavor, R.drawable.carry_iv_prop_11),
  CarryProps(Generation.GenerationIV, R.string.Stone_Plate, R.string.Stone_Plate_flavor, R.drawable.carry_iv_prop_12),
  CarryProps(Generation.GenerationIV, R.string.Spooky_Plate, R.string.Spooky_Plate_flavor, R.drawable.carry_iv_prop_13),
  CarryProps(Generation.GenerationIV, R.string.Draco_Plate, R.string.Draco_Plate_flavor, R.drawable.carry_iv_prop_14),
  CarryProps(Generation.GenerationIV, R.string.Dread_Plate, R.string.Dread_Plate_flavor, R.drawable.carry_iv_prop_15),
  CarryProps(Generation.GenerationIV, R.string.Iron_Plate, R.string.Iron_Plate_flavor, R.drawable.carry_iv_prop_16),
  CarryProps(Generation.GenerationIV, R.string.Oval_Stone, R.string.Oval_Stone_flavor, R.drawable.carry_iv_prop_17),
  CarryProps(Generation.GenerationIV, R.string.Adamant_Orb, R.string.Adamant_Orb_flavor, R.drawable.carry_iv_prop_18),
  CarryProps(Generation.GenerationIV, R.string.Lustrous_Orb, R.string.Lustrous_Orb_flavor, R.drawable.carry_iv_prop_19),
  CarryProps(Generation.GenerationIV, R.string.Griseous_Orb, R.string.Griseous_Orb_flavor, R.drawable.carry_iv_prop_20),
  CarryProps(Generation.GenerationIV, R.string.Odd_Incense, R.string.Odd_Incense_flavor, R.drawable.carry_iv_prop_21),
  CarryProps(Generation.GenerationIV, R.string.Rock_Incense, R.string.Rock_Incense_flavor, R.drawable.carry_iv_prop_22),
  CarryProps(Generation.GenerationIV, R.string.Full_Incense, R.string.Full_Incense_flavor, R.drawable.carry_iv_prop_23),
  CarryProps(Generation.GenerationIV, R.string.Wave_Incense, R.string.Wave_Incense_flavor, R.drawable.carry_iv_prop_24),
  CarryProps(Generation.GenerationIV, R.string.Rose_Incense, R.string.Rose_Incense_flavor, R.drawable.carry_iv_prop_25),
  CarryProps(Generation.GenerationIV, R.string.Luck_Incense, R.string.Luck_Incense_flavor, R.drawable.carry_iv_prop_26),
  CarryProps(Generation.GenerationIV, R.string.Pure_Incense, R.string.Pure_Incense_flavor, R.drawable.carry_iv_prop_27),
  CarryProps(Generation.GenerationIV, R.string.Protector, R.string.Protector_flavor, R.drawable.carry_iv_prop_28),
  CarryProps(Generation.GenerationIV, R.string.Electirizer, R.string.Electirizer_flavor, R.drawable.carry_iv_prop_29),
  CarryProps(Generation.GenerationIV, R.string.Magmarizer, R.string.Magmarizer_flavor, R.drawable.carry_iv_prop_30),
  CarryProps(Generation.GenerationIV, R.string.Dubious_Disc, R.string.Dubious_Disc_flavor, R.drawable.carry_iv_prop_31),
  CarryProps(Generation.GenerationIV, R.string.Reaper_Cloth, R.string.Reaper_Cloth_flavor, R.drawable.carry_iv_prop_32),
  CarryProps(Generation.GenerationIV, R.string.Razor_Claw, R.string.Razor_Claw_flavor, R.drawable.carry_iv_prop_33),
  CarryProps(Generation.GenerationIV, R.string.Razor_Fang, R.string.Razor_Fang_flavor, R.drawable.carry_iv_prop_34),
  CarryProps(Generation.GenerationIV, R.string.Wide_Lens, R.string.Wide_Lens_flavor, R.drawable.carry_iv_prop_35),
  CarryProps(Generation.GenerationIV, R.string.Muscle_Band, R.string.Muscle_Band_flavor, R.drawable.carry_iv_prop_36),
  CarryProps(Generation.GenerationIV, R.string.Wise_Glasses, R.string.Wise_Glasses_flavor, R.drawable.carry_iv_prop_37),
  CarryProps(Generation.GenerationIV, R.string.Expert_Belt, R.string.Expert_Belt_flavor, R.drawable.carry_iv_prop_38),
  CarryProps(Generation.GenerationIV, R.string.Light_Clay, R.string.Light_Clay_flavor, R.drawable.carry_iv_prop_39),
  CarryProps(Generation.GenerationIV, R.string.Life_Orb, R.string.Life_Orb_flavor, R.drawable.carry_iv_prop_40),
  CarryProps(Generation.GenerationIV, R.string.Power_Herb, R.string.Power_Herb_flavor, R.drawable.carry_iv_prop_41),
  CarryProps(Generation.GenerationIV, R.string.Toxic_Orb, R.string.Toxic_Orb_flavor, R.drawable.carry_iv_prop_42),
  CarryProps(Generation.GenerationIV, R.string.Flame_Orb, R.string.Flame_Orb_flavor, R.drawable.carry_iv_prop_43),
  CarryProps(Generation.GenerationIV, R.string.Quick_Powder, R.string.Quick_Powder_flavor, R.drawable.carry_iv_prop_44),
  CarryProps(Generation.GenerationIV, R.string.Focus_Sash, R.string.Focus_Sash_flavor, R.drawable.carry_iv_prop_45),
  CarryProps(Generation.GenerationIV, R.string.Zoom_Lens, R.string.Zoom_Lens_flavor, R.drawable.carry_iv_prop_46),
  CarryProps(Generation.GenerationIV, R.string.Metronome, R.string.Metronome_flavor, R.drawable.carry_iv_prop_47),
  CarryProps(Generation.GenerationIV, R.string.Iron_Ball, R.string.Iron_Ball_flavor, R.drawable.carry_iv_prop_48),
  CarryProps(Generation.GenerationIV, R.string.Lagging_Tail, R.string.Lagging_Tail_flavor, R.drawable.carry_iv_prop_49),
  CarryProps(Generation.GenerationIV, R.string.Destiny_Knot, R.string.Destiny_Knot_flavor, R.drawable.carry_iv_prop_50),
  CarryProps(Generation.GenerationIV, R.string.Black_Sludge, R.string.Black_Sludge_flavor, R.drawable.carry_iv_prop_51),
  CarryProps(Generation.GenerationIV, R.string.Icy_Rock, R.string.Icy_Rock_flavor, R.drawable.carry_iv_prop_52),
  CarryProps(Generation.GenerationIV, R.string.Smooth_Rock, R.string.Smooth_Rock_flavor, R.drawable.carry_iv_prop_53),
  CarryProps(Generation.GenerationIV, R.string.Heat_Rock, R.string.Heat_Rock_flavor, R.drawable.carry_iv_prop_54),
  CarryProps(Generation.GenerationIV, R.string.Damp_Rock, R.string.Damp_Rock_flavor, R.drawable.carry_iv_prop_55),
  CarryProps(Generation.GenerationIV, R.string.Grip_Claw, R.string.Grip_Claw_flavor, R.drawable.carry_iv_prop_56),
  CarryProps(Generation.GenerationIV, R.string.Choice_Scarf, R.string.Choice_Scarf_flavor, R.drawable.carry_iv_prop_57),
  CarryProps(Generation.GenerationIV, R.string.Sticky_Barb, R.string.Sticky_Barb_flavor, R.drawable.carry_iv_prop_58),
  CarryProps(Generation.GenerationIV, R.string.Power_Bracer, R.string.Power_Bracer_flavor, R.drawable.carry_iv_prop_59),
  CarryProps(Generation.GenerationIV, R.string.Power_Belt, R.string.Power_Belt_flavor, R.drawable.carry_iv_prop_60),
  CarryProps(Generation.GenerationIV, R.string.Rusted_Sword, R.string.Rusted_Sword_flavor, R.drawable.carry_viii_prop_1),
  CarryProps(Generation.GenerationIV, R.string.Power_Lens, R.string.Power_Lens_flavor, R.drawable.carry_iv_prop_61),
  CarryProps(Generation.GenerationIV, R.string.Power_Band, R.string.Power_Band_flavor, R.drawable.carry_iv_prop_62),
  CarryProps(Generation.GenerationIV, R.string.Power_Anklet, R.string.Power_Anklet_flavor, R.drawable.carry_iv_prop_63),
  CarryProps(Generation.GenerationIV, R.string.Power_Weight, R.string.Power_Weight_flavor, R.drawable.carry_iv_prop_64),
  CarryProps(Generation.GenerationIV, R.string.Shed_Shell, R.string.Shed_Shell_flavor, R.drawable.carry_iv_prop_65),
  CarryProps(Generation.GenerationIV, R.string.Big_Root, R.string.Big_Root_flavor, R.drawable.carry_iv_prop_66),
  CarryProps(Generation.GenerationIV, R.string.Choice_Specs, R.string.Choice_Specs_flavor, R.drawable.carry_iv_prop_67),
)

val carryVPropsList = listOf(
  CarryProps(Generation.GenerationV, R.string.Douse_Drive, R.string.Douse_Drive_flavor, R.drawable.carry_v_prop_1),
  CarryProps(Generation.GenerationV, R.string.Shock_Drive, R.string.Shock_Drive_flavor, R.drawable.carry_v_prop_2),
  CarryProps(Generation.GenerationV, R.string.Burn_Drive, R.string.Burn_Drive_flavor, R.drawable.carry_v_prop_3),
  CarryProps(Generation.GenerationV, R.string.Chill_Drive, R.string.Chill_Drive_flavor, R.drawable.carry_v_prop_4),
  CarryProps(Generation.GenerationV, R.string.Prism_Scale, R.string.Prism_Scale_flavor, R.drawable.carry_v_prop_5),
  CarryProps(Generation.GenerationV, R.string.Eviolite, R.string.Eviolite_flavor, R.drawable.carry_v_prop_6),
  CarryProps(Generation.GenerationV, R.string.Float_Stone, R.string.Float_Stone_flavor, R.drawable.carry_v_prop_7),
  CarryProps(Generation.GenerationV, R.string.Rocky_Helmet, R.string.Rocky_Helmet_flavor, R.drawable.carry_v_prop_8),
  CarryProps(Generation.GenerationV, R.string.Air_Balloon, R.string.Air_Balloon_flavor, R.drawable.carry_v_prop_9),
  CarryProps(Generation.GenerationV, R.string.Red_Card, R.string.Red_Card_flavor, R.drawable.carry_v_prop_10),
  CarryProps(Generation.GenerationV, R.string.Ring_Target, R.string.Ring_Target_flavor, R.drawable.carry_v_prop_11),
  CarryProps(Generation.GenerationV, R.string.Binding_Band, R.string.Binding_Band_flavor, R.drawable.carry_v_prop_12),
  CarryProps(Generation.GenerationV, R.string.Absorb_Bulb, R.string.Absorb_Bulb_flavor, R.drawable.carry_v_prop_13),
  CarryProps(Generation.GenerationV, R.string.Cell_Battery, R.string.Cell_Battery_flavor, R.drawable.carry_v_prop_14),
  CarryProps(Generation.GenerationV, R.string.Eject_Button, R.string.Eject_Button_flavor, R.drawable.carry_v_prop_15),
  CarryProps(Generation.GenerationV, R.string.Fire_Gem, R.string.Fire_Gem_flavor, R.drawable.carry_v_prop_16),
  CarryProps(Generation.GenerationV, R.string.Water_Gem, R.string.Water_Gem_flavor, R.drawable.carry_v_prop_17),
  CarryProps(Generation.GenerationV, R.string.Electric_Gem, R.string.Electric_Gem_flavor, R.drawable.carry_v_prop_18),
  CarryProps(Generation.GenerationV, R.string.Grass_Gem, R.string.Grass_Gem_flavor, R.drawable.carry_v_prop_19),
  CarryProps(Generation.GenerationV, R.string.Ice_Gem, R.string.Ice_Gem_flavor, R.drawable.carry_v_prop_20),
  CarryProps(Generation.GenerationV, R.string.Fighting_Gem, R.string.Fighting_Gem_flavor, R.drawable.carry_v_prop_21),
  CarryProps(Generation.GenerationV, R.string.Poison_Gem, R.string.Poison_Gem_flavor, R.drawable.carry_v_prop_22),
  CarryProps(Generation.GenerationV, R.string.Ground_Gem, R.string.Ground_Gem_flavor, R.drawable.carry_v_prop_23),
  CarryProps(Generation.GenerationV, R.string.Flying_Gem, R.string.Flying_Gem_flavor, R.drawable.carry_v_prop_24),
  CarryProps(Generation.GenerationV, R.string.Psychic_Gem, R.string.Psychic_Gem_flavor, R.drawable.carry_v_prop_25),
  CarryProps(Generation.GenerationV, R.string.Bug_Gem, R.string.Bug_Gem_flavor, R.drawable.carry_v_prop_26),
  CarryProps(Generation.GenerationV, R.string.Rock_Gem, R.string.Rock_Gem_flavor, R.drawable.carry_v_prop_27),
  CarryProps(Generation.GenerationV, R.string.Ghost_Gem, R.string.Ghost_Gem_flavor, R.drawable.carry_v_prop_28),
  CarryProps(Generation.GenerationV, R.string.Dragon_Gem, R.string.Dragon_Gem_flavor, R.drawable.carry_v_prop_29),
  CarryProps(Generation.GenerationV, R.string.Dark_Gem, R.string.Dark_Gem_flavor, R.drawable.carry_v_prop_30),
  CarryProps(Generation.GenerationV, R.string.Steel_Gem, R.string.Steel_Gem_flavor, R.drawable.carry_v_prop_31),
  CarryProps(Generation.GenerationV, R.string.Normal_Gem, R.string.Normal_Gem_flavor, R.drawable.carry_v_prop_32)
)

val carryVIPropsList = listOf(
  CarryProps(Generation.GenerationVI, R.string.Weakness_Policy, R.string.Weakness_Policy_flavor, R.drawable.carry_vi_prop_1),
  CarryProps(Generation.GenerationVI, R.string.Assault_Vest, R.string.Assault_Vest_flavor, R.drawable.carry_vi_prop_2),
  CarryProps(Generation.GenerationVI, R.string.Pixie_Plate, R.string.Pixie_Plate_flavor, R.drawable.carry_vi_prop_3),
  CarryProps(Generation.GenerationVI, R.string.Fairy_Gem, R.string.Fairy_Gem_flavor, R.drawable.carry_vi_prop_4),
  CarryProps(Generation.GenerationVI, R.string.Whipped_Dream, R.string.Whipped_Dream_flavor, R.drawable.carry_vi_prop_5),
  CarryProps(Generation.GenerationVI, R.string.Sachet, R.string.Sachet_flavor, R.drawable.carry_vi_prop_6),
  CarryProps(Generation.GenerationVI, R.string.Luminous_Moss, R.string.Luminous_Moss_flavor, R.drawable.carry_vi_prop_7),
  CarryProps(Generation.GenerationVI, R.string.Snowball, R.string.Snowball_flavor, R.drawable.carry_vi_prop_8),
  CarryProps(Generation.GenerationVI, R.string.Safety_Goggles, R.string.Safety_Goggles_flavor, R.drawable.carry_vi_prop_9),
  CarryProps(Generation.GenerationVI, R.string.Venusaurite, R.string.Venusaurite_flavor, R.drawable.carry_vi_prop_10),
  CarryProps(Generation.GenerationVI, R.string.Charizardite_X, R.string.Charizardite_X_flavor, R.drawable.carry_vi_prop_11),
  CarryProps(Generation.GenerationVI, R.string.Charizardite_Y, R.string.Charizardite_Y_flavor, R.drawable.carry_vi_prop_12),
  CarryProps(Generation.GenerationVI, R.string.Blastoisinite, R.string.Blastoisinite_flavor, R.drawable.carry_vi_prop_13),
  CarryProps(Generation.GenerationVI, R.string.Beedrillite, R.string.Beedrillite_flavor, R.drawable.carry_vi_prop_14),
  CarryProps(Generation.GenerationVI, R.string.Pidgeotite, R.string.Pidgeotite_flavor, R.drawable.carry_vi_prop_15),
  CarryProps(Generation.GenerationVI, R.string.Alakazite, R.string.Alakazite_flavor, R.drawable.carry_vi_prop_16),
  CarryProps(Generation.GenerationVI, R.string.Slowbronite, R.string.Slowbronite_flavor, R.drawable.carry_vi_prop_17),
  CarryProps(Generation.GenerationVI, R.string.Gengarite, R.string.Gengarite_flavor, R.drawable.carry_vi_prop_18),
  CarryProps(Generation.GenerationVI, R.string.Kangaskhanite, R.string.Kangaskhanite_flavor, R.drawable.carry_vi_prop_19),
  CarryProps(Generation.GenerationVI, R.string.Pinsirite, R.string.Pinsirite_flavor, R.drawable.carry_vi_prop_20),
  CarryProps(Generation.GenerationVI, R.string.Gyaradosite, R.string.Gyaradosite_flavor, R.drawable.carry_vi_prop_21),
  CarryProps(Generation.GenerationVI, R.string.Aerodactylite, R.string.Aerodactylite_flavor, R.drawable.carry_vi_prop_22),
  CarryProps(Generation.GenerationVI, R.string.Mewtwonite_X, R.string.Mewtwonite_X_flavor, R.drawable.carry_vi_prop_23),
  CarryProps(Generation.GenerationVI, R.string.Mewtwonite_Y, R.string.Mewtwonite_Y_flavor, R.drawable.carry_vi_prop_24),
  CarryProps(Generation.GenerationVI, R.string.Ampharosite, R.string.Ampharosite_flavor, R.drawable.carry_vi_prop_25),
  CarryProps(Generation.GenerationVI, R.string.Steelixite, R.string.Steelixite_flavor, R.drawable.carry_vi_prop_26),
  CarryProps(Generation.GenerationVI, R.string.Scizorite, R.string.Scizorite_flavor, R.drawable.carry_vi_prop_27),
  CarryProps(Generation.GenerationVI, R.string.Heracronite, R.string.Heracronite_flavor, R.drawable.carry_vi_prop_28),
  CarryProps(Generation.GenerationVI, R.string.Houndoominite, R.string.Houndoominite_flavor, R.drawable.carry_vi_prop_29),
  CarryProps(Generation.GenerationVI, R.string.Tyranitarite, R.string.Tyranitarite_flavor, R.drawable.carry_vi_prop_30),
  CarryProps(Generation.GenerationVI, R.string.Sceptilite, R.string.Sceptilite_flavor, R.drawable.carry_vi_prop_31),
  CarryProps(Generation.GenerationVI, R.string.Blazikenite, R.string.Blazikenite_flavor, R.drawable.carry_vi_prop_32),
  CarryProps(Generation.GenerationVI, R.string.Swampertite, R.string.Swampertite_flavor, R.drawable.carry_vi_prop_33),
  CarryProps(Generation.GenerationVI, R.string.Gardevoirite, R.string.Gardevoirite_flavor, R.drawable.carry_vi_prop_34),
  CarryProps(Generation.GenerationVI, R.string.Sablenite, R.string.Sablenite_flavor, R.drawable.carry_vi_prop_35),
  CarryProps(Generation.GenerationVI, R.string.Mawilite, R.string.Mawilite_flavor, R.drawable.carry_vi_prop_36),
  CarryProps(Generation.GenerationVI, R.string.Aggronite, R.string.Aggronite_flavor, R.drawable.carry_vi_prop_37),
  CarryProps(Generation.GenerationVI, R.string.Medichamite, R.string.Medichamite_flavor, R.drawable.carry_vi_prop_38),
  CarryProps(Generation.GenerationVI, R.string.Manectite, R.string.Manectite_flavor, R.drawable.carry_vi_prop_39),
  CarryProps(Generation.GenerationVI, R.string.Sharpedonite, R.string.Sharpedonite_flavor, R.drawable.carry_vi_prop_40),
  CarryProps(Generation.GenerationVI, R.string.Cameruptite, R.string.Cameruptite_flavor, R.drawable.carry_vi_prop_41),
  CarryProps(Generation.GenerationVI, R.string.Altarianite, R.string.Altarianite_flavor, R.drawable.carry_vi_prop_42),
  CarryProps(Generation.GenerationVI, R.string.Banettite, R.string.Banettite_flavor, R.drawable.carry_vi_prop_43),
  CarryProps(Generation.GenerationVI, R.string.Absolite, R.string.Absolite_flavor, R.drawable.carry_vi_prop_44),
  CarryProps(Generation.GenerationVI, R.string.Glalitite, R.string.Glalitite_flavor, R.drawable.carry_vi_prop_45),
  CarryProps(Generation.GenerationVI, R.string.Salamencite, R.string.Salamencite_flavor, R.drawable.carry_vi_prop_46),
  CarryProps(Generation.GenerationVI, R.string.Metagrossite, R.string.Metagrossite_flavor, R.drawable.carry_vi_prop_47),
  CarryProps(Generation.GenerationVI, R.string.Latiasite, R.string.Latiasite_flavor, R.drawable.carry_vi_prop_48),
  CarryProps(Generation.GenerationVI, R.string.Latiosite, R.string.Latiosite_flavor, R.drawable.carry_vi_prop_49),
  CarryProps(Generation.GenerationVI, R.string.Lopunnite, R.string.Lopunnite_flavor, R.drawable.carry_vi_prop_50),
  CarryProps(Generation.GenerationVI, R.string.Lucarionite, R.string.Lucarionite_flavor, R.drawable.carry_vi_prop_51),
  CarryProps(Generation.GenerationVI, R.string.Garchompite, R.string.Garchompite_flavor, R.drawable.carry_vi_prop_52),
  CarryProps(Generation.GenerationVI, R.string.Abomasite, R.string.Abomasite_flavor, R.drawable.carry_vi_prop_53),
  CarryProps(Generation.GenerationVI, R.string.Galladite, R.string.Galladite_flavor, R.drawable.carry_vi_prop_54),
  CarryProps(Generation.GenerationVI, R.string.Audinite, R.string.Audinite_flavor, R.drawable.carry_vi_prop_55),
  CarryProps(Generation.GenerationVI, R.string.Diancite, R.string.Diancite_flavor, R.drawable.carry_vi_prop_56),
  CarryProps(Generation.GenerationVI, R.string.Red_Orb, R.string.Red_Orb_flavor, R.drawable.carry_vi_prop_57),
  CarryProps(Generation.GenerationVI, R.string.BlueOrb, R.string.BlueOrb_flavor, R.drawable.carry_vi_prop_58),
)

val carryVIIPropsList = listOf(
  CarryProps(Generation.GenerationVII, R.string.Adrenaline_Orb, R.string.Adrenaline_Orb_flavor, R.drawable.carry_vii_prop_1),
  CarryProps(Generation.GenerationVII, R.string.Terrain_Extender, R.string.Terrain_Extender_flavor, R.drawable.carry_vii_prop_2),
  CarryProps(Generation.GenerationVII, R.string.Protective_Pads, R.string.Protective_Pads_flavor, R.drawable.carry_vii_prop_3),
  CarryProps(Generation.GenerationVII, R.string.Electric_Seed, R.string.Electric_Seed_flavor, R.drawable.carry_vii_prop_4),
  CarryProps(Generation.GenerationVII, R.string.Psychic_Seed, R.string.Psychic_Seed_flavor, R.drawable.carry_vii_prop_5),
  CarryProps(Generation.GenerationVII, R.string.Misty_Seed, R.string.Misty_Seed_flavor, R.drawable.carry_vii_prop_6),
  CarryProps(Generation.GenerationVII, R.string.Grassy_Seed, R.string.Grassy_Seed_flavor, R.drawable.carry_vii_prop_7),
  CarryProps(Generation.GenerationVII, R.string.Fighting_Memory, R.string.Fighting_Memory_flavor, R.drawable.carry_vii_prop_8),
  CarryProps(Generation.GenerationVII, R.string.Flying_Memory, R.string.Flying_Memory_flavor, R.drawable.carry_vii_prop_9),
  CarryProps(Generation.GenerationVII, R.string.Poison_Memory, R.string.Poison_Memory_flavor, R.drawable.carry_vii_prop_10),
  CarryProps(Generation.GenerationVII, R.string.Ground_Memory, R.string.Ground_Memory_flavor, R.drawable.carry_vii_prop_11),
  CarryProps(Generation.GenerationVII, R.string.Rock_Memory, R.string.Rock_Memory_flavor, R.drawable.carry_vii_prop_12),
  CarryProps(Generation.GenerationVII, R.string.Bug_Memory, R.string.Bug_Memory_flavor, R.drawable.carry_vii_prop_13),
  CarryProps(Generation.GenerationVII, R.string.Ghost_Memory, R.string.Ghost_Memory_flavor, R.drawable.carry_vii_prop_14),
  CarryProps(Generation.GenerationVII, R.string.Steel_Memory, R.string.Steel_Memory_flavor, R.drawable.carry_vii_prop_15),
  CarryProps(Generation.GenerationVII, R.string.Fire_Memory, R.string.Fire_Memory_flavor, R.drawable.carry_vii_prop_16),
  CarryProps(Generation.GenerationVII, R.string.Water_Memory, R.string.Water_Memory_flavor, R.drawable.carry_vii_prop_17),
  CarryProps(Generation.GenerationVII, R.string.Grass_Memory, R.string.Grass_Memory_flavor, R.drawable.carry_vii_prop_18),
  CarryProps(Generation.GenerationVII, R.string.Electric_Memory, R.string.Electric_Memory_flavor, R.drawable.carry_vii_prop_19),
  CarryProps(Generation.GenerationVII, R.string.Psychic_Memory, R.string.Psychic_Memory_flavor, R.drawable.carry_vii_prop_20),
  CarryProps(Generation.GenerationVII, R.string.Ice_Memory, R.string.Ice_Memory_flavor, R.drawable.carry_vii_prop_21),
  CarryProps(Generation.GenerationVII, R.string.Dragon_Memory, R.string.Dragon_Memory_flavor, R.drawable.carry_vii_prop_22),
  CarryProps(Generation.GenerationVII, R.string.Dark_Memory, R.string.Dark_Memory_flavor, R.drawable.carry_vii_prop_23),
  CarryProps(Generation.GenerationVII, R.string.Fairy_Memory, R.string.Fairy_Memory_flavor, R.drawable.carry_vii_prop_24),
)

val carryVIIIPropsList = listOf(
  CarryProps(Generation.GenerationVIII, R.string.Rusted_Sword, R.string.Rusted_Sword_flavor, R.drawable.carry_viii_prop_1),
  CarryProps(Generation.GenerationVIII, R.string.Rusted_Shield, R.string.Rusted_Shield_flavor, R.drawable.carry_viii_prop_2),
  CarryProps(Generation.GenerationVIII, R.string.Strawberry_Sweet, R.string.Strawberry_Sweet_flavor, R.drawable.carry_viii_prop_3),
  CarryProps(Generation.GenerationVIII, R.string.Love_Sweet, R.string.Love_Sweet_flavor, R.drawable.carry_viii_prop_4),
  CarryProps(Generation.GenerationVIII, R.string.Berry_Sweet, R.string.Berry_Sweet_flavor, R.drawable.carry_viii_prop_5),
  CarryProps(Generation.GenerationVIII, R.string.Clover_Sweet, R.string.Clover_Sweet_flavor, R.drawable.carry_viii_prop_6),
  CarryProps(Generation.GenerationVIII, R.string.Flower_Sweet, R.string.Flower_Sweet_flavor, R.drawable.carry_viii_prop_7),
  CarryProps(Generation.GenerationVIII, R.string.Star_Sweet, R.string.Star_Sweet_flavor, R.drawable.carry_viii_prop_8),
  CarryProps(Generation.GenerationVIII, R.string.Ribbon_Sweet, R.string.Ribbon_Sweet_flavor, R.drawable.carry_viii_prop_9),
  CarryProps(Generation.GenerationVIII, R.string.Throat_Spray, R.string.Throat_Spray_flavor, R.drawable.carry_viii_prop_10),
  CarryProps(Generation.GenerationVIII, R.string.Eject_Pack, R.string.Eject_Pack_flavor, R.drawable.carry_viii_prop_11),
  CarryProps(Generation.GenerationVIII, R.string.Heavy_Duty_Boots, R.string.Heavy_Duty_Boots_flavor, R.drawable.carry_viii_prop_12),
  CarryProps(Generation.GenerationVIII, R.string.Blunder_Policy, R.string.Blunder_Policy_flavor, R.drawable.carry_viii_prop_13),
  CarryProps(Generation.GenerationVIII, R.string.Room_Service, R.string.Room_Service_flavor, R.drawable.carry_viii_prop_14),
  CarryProps(Generation.GenerationVIII, R.string.Utility_Umbrella, R.string.Utility_Umbrella_flavor, R.drawable.carry_viii_prop_15),
)

