package com.heinika.pokeg.info

import com.heinika.pokeg.R
import com.heinika.pokeg.info.Generation


enum class Version(val resString: Int, val resDrawable: Int,val generation: Generation) {
  Red(R.string.version_red,R.drawable.version_red, Generation.GenerationI),
  Green(R.string.version_green,R.drawable.version_green, Generation.GenerationI),
  Blue(R.string.version_blue,R.drawable.version_blue, Generation.GenerationI),
  Yellow(R.string.version_yellow,R.drawable.version_yellow, Generation.GenerationI),
  Gold(R.string.version_gold,R.drawable.version_gold, Generation.GenerationII),
  Silver(R.string.version_silver,R.drawable.version_silver, Generation.GenerationII),
  Crystal(R.string.version_crystal,R.drawable.version_crystal, Generation.GenerationII),
  Ruby(R.string.version_ruby,R.drawable.version_ruby, Generation.GenerationIII),
  Sapphire(R.string.version_sapphire,R.drawable.version_sapphire, Generation.GenerationIII),
  Emerald(R.string.version_emerald,R.drawable.version_emerald, Generation.GenerationIII),
  Firered(R.string.version_firered,R.drawable.version_firered, Generation.GenerationIII),
  Leafgreen(R.string.version_leafgreen,R.drawable.version_leafgreen, Generation.GenerationIII),
  Diamond(R.string.version_diamond,R.drawable.version_diamond, Generation.GenerationIV),
  Pearl(R.string.version_pearl,R.drawable.version_pearl, Generation.GenerationIV),
  Platinum(R.string.version_platinum,R.drawable.version_platinum, Generation.GenerationIV),
  Heartgold(R.string.version_heartgold,R.drawable.version_heartgold, Generation.GenerationIV),
  Soulsilver(R.string.version_soulsilver,R.drawable.version_soulsilver, Generation.GenerationIV),
  Black(R.string.version_black,R.drawable.version_black, Generation.GenerationV),
  White(R.string.version_white,R.drawable.version_white, Generation.GenerationV),
  Colosseum(R.string.version_colosseum,R.drawable.version_colosseum, Generation.GenerationV),
  Xd(R.string.version_xd,R.drawable.version_xd, Generation.GenerationV),
  Black2(R.string.version_black_2,R.drawable.version_black_2, Generation.GenerationV),
  White2(R.string.version_white_2,R.drawable.version_white_2, Generation.GenerationV),
  X(R.string.version_x,R.drawable.version_x, Generation.GenerationVI),
  Y(R.string.version_y,R.drawable.version_y, Generation.GenerationVI),
  OmegaRuby(R.string.version_omega_ruby,R.drawable.version_omega_ruby, Generation.GenerationVI),
  AlphaSapphire(R.string.version_alpha_sapphire,R.drawable.version_alpha_sapphire, Generation.GenerationVI),
  Sun(R.string.version_sun,R.drawable.version_sun, Generation.GenerationVII),
  Moon(R.string.version_moon,R.drawable.version_moon, Generation.GenerationVII),
  UltraSun(R.string.version_ultra_sun,R.drawable.version_ultra_sun, Generation.GenerationVII),
  UltraMoon(R.string.version_ultra_moon,R.drawable.version_ultra_moon, Generation.GenerationVII),
  LetsGoPikachu(R.string.version_lets_go_pikachu,R.drawable.version_lets_go_pikachu, Generation.GenerationVII),
  LetsGoEevee(R.string.version_lets_go_eevee,R.drawable.version_lets_go_eevee, Generation.GenerationVII),
  LetsGoSword(R.string.version_sword,R.drawable.version_sword, Generation.GenerationVIII),
  Shield(R.string.version_shield,R.drawable.version_shield, Generation.GenerationVIII),
  BrilliantDiamond(R.string.version_brilliant_diamond,R.drawable.version_brilliant_diamond, Generation.GenerationVIII),
  ShiningPearl(R.string.version_shining_pearl,R.drawable.version_shining_pearl, Generation.GenerationVIII),
  Arceus(R.string.version_arceus,R.drawable.version_arceus, Generation.GenerationVIII),
}

