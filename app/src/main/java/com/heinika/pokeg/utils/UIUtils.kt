package com.heinika.pokeg.utils

import android.content.res.Resources

val Number.dp: Int
  get() = (this.toInt() * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

val Int.toBoolean: Boolean
  get() = this == 1

val Int.isCnId: Boolean
  get() = this == 12

val Int.isEnId: Boolean
  get() = this == 9

val Int.isJaId: Boolean
  get() = this == 1

val Int.isHPStat: Boolean
  get() = this == 1

val Int.isAttackStat: Boolean
  get() = this == 2

val Int.isDefenseStat: Boolean
  get() = this == 3

val Int.isSAttackStat: Boolean
  get() = this == 4

val Int.isSDefenseStat: Boolean
  get() = this == 5

val Int.isSPeedStat: Boolean
  get() = this == 6

val Int.isByLevelUp: Boolean
  get() = this == 1

val Int.isByTrade: Boolean
  get() = this == 2

val Int.isUseItem: Boolean
  get() = this == 3

val Int.isShed: Boolean
  get() = this == 4

fun getPokemonImageUrl(id: Int, name: String) = when {
  name.contains("gmax") -> {
    if(gMaxImageUrls.any { it.contains(name.split("-")[0], true) }){
      gMaxImageUrls.first { it.contains(name.split("-")[0], true) }
    } else {
      ""
    }
  }
  name.contains("galar") -> {
    if (galarImageUrls.any { it.contains(name.split("-")[0], true) }){
      galarImageUrls.first { it.contains(name.split("-")[0], true) }
    }else{
      ""
    }
  }
  id == 875 -> "https://media.52poke.com/wiki/thumb/e/e3/875Eiscue.png/600px-875Eiscue.png"
  id == 877 -> "https://media.52poke.com/wiki/thumb/5/51/877Morpeko.png/600px-877Morpeko.png"
  id == 888 -> "https://cdn.bulbagarden.net/upload/thumb/c/ca/888Zacian.png/600px-888Zacian.png"
  id == 889 -> "https://cdn.bulbagarden.net/upload/thumb/7/72/889Zamazenta.png/600px-889Zamazenta.png"
//  id <= 890 -> {
////          "https://img.pokemondb.net/artwork/${name}.jpg"
//    "https://pokeres.bastionbot.org/images/pokemon/$id.png"
//  }
  id == 891 -> "https://cdn.bulbagarden.net/upload/4/47/891Kubfu.png"
  id == 892 -> "https://cdn.bulbagarden.net/upload/thumb/e/e7/892Urshifu-Single_Strike.png/600px-892Urshifu-Single_Strike.png"
  id == 893 -> "https://cdn.bulbagarden.net/upload/thumb/a/a5/893Zarude.png/250px-893Zarude.png"
  id == 894 -> "https://cdn.bulbagarden.net/upload/thumb/9/9b/894Regieleki.png/600px-894Regieleki.png"
  id == 895 -> "https://cdn.bulbagarden.net/upload/thumb/e/e8/895Regidrago.png/600px-895Regidrago.png"
  id == 896 -> "https://cdn.bulbagarden.net/upload/thumb/f/ff/896Glastrier.png/600px-896Glastrier.png"
  id == 897 -> "https://cdn.bulbagarden.net/upload/thumb/7/7e/897Spectrier.png/600px-897Spectrier.png"
  id == 898 -> "https://cdn.bulbagarden.net/upload/thumb/3/3c/898Calyrex.png/600px-898Calyrex.png"
  id == 10061 -> "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/2d7bbc09-b889-4fb5-89c5-390e7aa64cb4/d8dgmj3-5d2a1529-7a92-4a5b-bdfa-456dfea107c6.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOiIsImlzcyI6InVybjphcHA6Iiwib2JqIjpbW3sicGF0aCI6IlwvZlwvMmQ3YmJjMDktYjg4OS00ZmI1LTg5YzUtMzkwZTdhYTY0Y2I0XC9kOGRnbWozLTVkMmExNTI5LTdhOTItNGE1Yi1iZGZhLTQ1NmRmZWExMDdjNi5wbmcifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6ZmlsZS5kb3dubG9hZCJdfQ.BG07eG91ph0S-A3yaWSKGOZ9n1P7EpTkaNePukVFiv4"
  id == 10085 -> "https://cdn.bulbagarden.net/upload/2/28/Spr_6o_025_C.png"
  id == 10091 -> "https://cdn.bulbagarden.net/upload/thumb/9/91/019Rattata-Alola.png/600px-019Rattata-Alola.png"
  id == 10092 -> "https://archives.bulbagarden.net/media/upload/thumb/7/71/020Raticate-Alola.png/600px-020Raticate-Alola.png"
  id == 10093 -> "https://veekun.com/dex/media/pokemon/main-sprites/ultra-sun-ultra-moon/20-totem-alola.png"
  id == 10094 -> "https://cdn.bulbagarden.net/upload/thumb/1/17/025Pikachu-Original.png/600px-025Pikachu-Original.png"
  id == 10095 -> "https://cdn.bulbagarden.net/upload/thumb/4/44/025Pikachu-Hoenn.png/600px-025Pikachu-Hoenn.png"
  id == 10096 -> "https://cdn.bulbagarden.net/upload/thumb/a/a4/025Pikachu-Sinnoh.png/600px-025Pikachu-Sinnoh.png"
  id == 10097 -> "https://cdn.bulbagarden.net/upload/thumb/e/e6/025Pikachu-Unova.png/600px-025Pikachu-Unova.png"
  id == 10098 -> "https://cdn.bulbagarden.net/upload/thumb/4/44/025Pikachu-Kalos.png/600px-025Pikachu-Kalos.png"
  id == 10099 -> "https://cdn.bulbagarden.net/upload/thumb/e/e3/025Pikachu-Alola.png/600px-025Pikachu-Alola.png"
  id == 10100 -> "https://s1.52poke.wiki/wiki/thumb/3/3a/026Raichu-Alola.png/600px-026Raichu-Alola.png"
  id == 10101 -> "https://s1.52poke.wiki/wiki/thumb/c/c9/027Sandshrew-Alola.png/600px-027Sandshrew-Alola.png"
  id == 10102 -> "https://s1.52poke.wiki/wiki/thumb/b/bd/028Sandslash-Alola.png/600px-028Sandslash-Alola.png"
  id == 10103 -> "https://s1.52poke.wiki/wiki/thumb/3/35/037Vulpix-Alola.png/600px-037Vulpix-Alola.png"
  id == 10104 -> "https://s1.52poke.wiki/wiki/thumb/2/26/038Ninetales-Alola.png/600px-038Ninetales-Alola.png"
  id == 10105 -> "https://s1.52poke.wiki/wiki/thumb/1/10/050Diglett-Alola.png/600px-050Diglett-Alola.png"
  id == 10106 -> "https://s1.52poke.wiki/wiki/thumb/2/22/051Dugtrio-Alola.png/600px-051Dugtrio-Alola.png"
  id == 10107 -> "https://s1.52poke.wiki/wiki/thumb/e/e3/052Meowth-Alola.png/600px-052Meowth-Alola.png"
  id == 10108 -> "https://s1.52poke.wiki/wiki/thumb/8/80/053Persian-Alola.png/600px-053Persian-Alola.png"
  id == 10109 -> "https://s1.52poke.wiki/wiki/thumb/4/43/074Geodude-Alola.png/600px-074Geodude-Alola.png"
  id == 10110 -> "https://s1.52poke.wiki/wiki/thumb/6/62/075Graveler-Alola.png/600px-075Graveler-Alola.png"
  id == 10111 -> "https://s1.52poke.wiki/wiki/thumb/0/07/076Golem-Alola.png/600px-076Golem-Alola.png"
  id == 10112 -> "https://s1.52poke.wiki/wiki/thumb/e/e0/088Grimer-Alola.png/150px-088Grimer-Alola.png"
  id == 10113 -> "https://s1.52poke.wiki/wiki/thumb/1/15/089Muk-Alola.png/600px-089Muk-Alola.png"
  id == 10114 -> "https://s1.52poke.wiki/wiki/thumb/7/74/103Exeggutor-Alola.png/600px-103Exeggutor-Alola.png"
  id == 10115 -> "https://s1.52poke.wiki/wiki/thumb/0/06/105Marowak-Alola.png/600px-105Marowak-Alola.png"
  id == 10116 -> "https://cdn.bulbagarden.net/upload/thumb/6/67/658Greninja.png/600px-658Greninja.png"
  id == 10121 -> "https://cdn.bulbagarden.net/upload/thumb/b/ba/735Gumshoos.png/600px-735Gumshoos.png"
  id == 10122 -> "https://cdn.bulbagarden.net/upload/thumb/4/4e/738Vikavolt.png/600px-738Vikavolt.png"
  id == 10127 -> "https://www.pikpng.com/pngl/b/431-4317690_wishiwashi-regular-and-school-form-now-complete-wishiwashi.png"
  id == 10128 -> "https://static.miraheze.org/atrociousgameplaywiki/thumb/c/c5/Lurantis.png/600px-Lurantis.png"
  id == 10129 -> "https://cdn.bulbagarden.net/upload/thumb/7/72/758Salazzle.png/600px-758Salazzle.png"
  id == 10130 -> "https://cdn.bulbagarden.net/upload/d/da/HOME774O.png"
  id == 10131 -> "https://cdn.bulbagarden.net/upload/5/58/HOME774Y.png"
  id == 10132 -> "https://cdn.bulbagarden.net/upload/0/01/HOME774G.png"
  id == 10133 -> "https://cdn.bulbagarden.net/upload/b/bb/HOME774B.png"
  id == 10134 -> "https://cdn.bulbagarden.net/upload/6/6e/HOME774I.png"
  id == 10135 -> "https://cdn.bulbagarden.net/upload/f/f9/HOME774V.png"
  id == 10136 -> "https://cdn.bulbagarden.net/upload/8/80/HOME774R.png"
  id == 10137 -> "https://cdn.bulbagarden.net/upload/d/da/HOME774O.png"
  id == 10138 -> "https://cdn.bulbagarden.net/upload/5/58/HOME774Y.png"
  id == 10139 -> "https://cdn.bulbagarden.net/upload/0/01/HOME774G.png"
  id == 10140 -> "https://cdn.bulbagarden.net/upload/b/bb/HOME774B.png"
  id == 10141 -> "https://cdn.bulbagarden.net/upload/6/6e/HOME774I.png"
  id == 10142 -> "https://cdn.bulbagarden.net/upload/f/f9/HOME774V.png"
  id == 10144 -> "https://cdn.bulbagarden.net/upload/thumb/9/9b/778Mimikyu.png/600px-778Mimikyu.png"
  id == 10145 -> "https://cdn.bulbagarden.net/upload/3/35/HOME778B.png"
  id == 10147 -> "https://cdn.bulbagarden.net/upload/4/4c/HOME801O.png"
  id == 10148 -> "https://cdn.bulbagarden.net/upload/thumb/7/75/025Pikachu-Partner.png/600px-025Pikachu-Partner.png"
  id == 10151 -> "https://cdn.bulbagarden.net/upload/thumb/5/51/744Rockruff.png/600px-744Rockruff.png"
  id == 10155 -> "https://cdn.bulbagarden.net/upload/8/80/800Necrozma-Dusk_Mane.png"
  id == 10156 -> "https://cdn.bulbagarden.net/upload/7/7a/800Necrozma-Dawn_Wings.png"
  id == 10157 -> "https://cdn.bulbagarden.net/upload/thumb/8/8b/800Necrozma-Ultra.png/600px-800Necrozma-Ultra.png"
  id == 10158 -> "https://cdn.bulbagarden.net/upload/0/09/052Meowth-Galar.png"
  id == 10159 -> "https://cdn.bulbagarden.net/upload/9/92/077Ponyta-Galar.png"
  id == 10160 -> "https://cdn.bulbagarden.net/upload/e/e0/078Rapidash-Galar.png"
  id == 10161 -> "https://cdn.bulbagarden.net/upload/9/9f/079Slowpoke-Galar.png"
  id == 10162 -> "https://cdn.bulbagarden.net/upload/8/8b/080Slowbro-Galar.png"
  id == 10163 -> "https://cdn.bulbagarden.net/upload/7/7d/083Farfetch%27d-Galar.png"
  id == 10164 -> "https://cdn.bulbagarden.net/upload/thumb/3/38/110Weezing-Galar.png/600px-110Weezing-Galar.png"
  name.contains("totem") -> "https://img.pokemondb.net/artwork/vector/${name.dropLast(6)}.png"
  id <= 649 -> "https://img.pokemondb.net/artwork/vector/large/${name}.png"
  id <= 807 -> "https://img.pokemondb.net/artwork/vector/${name}.png"
  else -> "https://img.pokemondb.net/artwork/large/${name}.jpg"
}

private val gMaxImageUrls = setOf(
  "https://media.52poke.com/wiki/thumb/8/8a/003Venusaur-Gigantamax.png/450px-003Venusaur-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/8/88/006Charizard-Gigantamax.png/450px-006Charizard-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/d/dc/009Blastoise-Gigantamax.png/450px-009Blastoise-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/f/fd/012Butterfree-Gigantamax.png/450px-012Butterfree-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/9/9f/052Meowth-Gigantamax.png/450px-052Meowth-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/c/c4/068Machamp-Gigantamax.png/450px-068Machamp-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/3/31/094Gengar-Gigantamax.png/450px-094Gengar-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/d/d3/099Kingler-Gigantamax.png/450px-099Kingler-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/1/15/131Lapras-Gigantamax.png/450px-131Lapras-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/2/2e/133Eevee-Gigantamax.png/450px-133Eevee-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/3/38/143Snorlax-Gigantamax.png/450px-143Snorlax-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/c/c9/569Garbodor-Gigantamax.png/450px-569Garbodor-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/5/5e/809Melmetal-Gigantamax.png/450px-809Melmetal-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/0/0b/812Rillaboom-Gigantamax.png/450px-812Rillaboom-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/1/1e/815Cinderace-Gigantamax.png/450px-815Cinderace-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/b/bf/818Inteleon-Gigantamax.png/450px-818Inteleon-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/2/2e/823Corviknight-Gigantamax.png/450px-823Corviknight-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/2/24/826Orbeetle-Gigantamax.png/450px-826Orbeetle-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/b/b4/834Drednaw-Gigantamax.png/450px-834Drednaw-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/b/b0/839Coalossal-Gigantamax.png/450px-839Coalossal-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/a/a2/841Flapple-Gigantamax.png/450px-841Flapple-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/a/a2/841Flapple-Gigantamax.png/450px-841Flapple-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/1/19/844Sandaconda-Gigantamax.png/450px-844Sandaconda-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/7/73/849Toxtricity-Gigantamax.png/450px-849Toxtricity-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/b/b4/851Centiskorch-Gigantamax.png/450px-851Centiskorch-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/6/6a/858Hatterene-Gigantamax.png/450px-858Hatterene-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/4/45/861Grimmsnarl-Gigantamax.png/450px-861Grimmsnarl-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/4/40/869Alcremie-Gigantamax.png/450px-869Alcremie-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/1/16/879Copperajah-Gigantamax.png/450px-879Copperajah-Gigantamax.png",
  "https://media.52poke.com/wiki/thumb/1/1b/884Duraludon-Gigantamax.png/450px-884Duraludon-Gigantamax.png",
)

private val galarImageUrls = listOf(
  "https://media.52poke.com/wiki/thumb/9/92/077Ponyta-Galar.png/450px-077Ponyta-Galar.png",
  "https://media.52poke.com/wiki/thumb/e/e0/078Rapidash-Galar.png/450px-078Rapidash-Galar.png",
  "https://media.52poke.com/wiki/thumb/9/9f/079Slowpoke-Galar.png/450px-079Slowpoke-Galar.png",
  "https://media.52poke.com/wiki/thumb/8/8b/080Slowbro-Galar.png/450px-080Slowbro-Galar.png",
  "https://media.52poke.com/wiki/thumb/7/7d/083Farfetch%27d-Galar.png/450px-083Farfetch%27d-Galar.png",
  "https://media.52poke.com/wiki/thumb/3/38/110Weezing-Galar.png/450px-110Weezing-Galar.png",
  "https://media.52poke.com/wiki/thumb/d/d1/122Mr._Mime-Galar.png/450px-122Mr._Mime-Galar.png",
  "https://media.52poke.com/wiki/thumb/d/df/144Articuno-Galar.png/450px-144Articuno-Galar.png",
  "https://media.52poke.com/wiki/thumb/1/13/145Zapdos-Galar.png/450px-145Zapdos-Galar.png",
  "https://media.52poke.com/wiki/thumb/f/f7/146Moltres-Galar.png/450px-146Moltres-Galar.png",
  "https://media.52poke.com/wiki/thumb/c/ca/199Slowking-Galar.png/450px-199Slowking-Galar.png",
  "https://media.52poke.com/wiki/thumb/c/ce/222Corsola-Galar.png/450px-222Corsola-Galar.png",
  "https://media.52poke.com/wiki/thumb/8/8e/264Linoone-Galar.png/450px-264Linoone-Galar.png",
  "https://media.52poke.com/wiki/thumb/b/b0/263Zigzagoon-Galar.png/450px-263Zigzagoon-Galar.png",
  "https://media.52poke.com/wiki/thumb/c/c9/554Darumaka-Galar.png/450px-554Darumaka-Galar.png",
  "https://media.52poke.com/wiki/thumb/6/60/555Darmanitan-Galar.png/450px-555Darmanitan-Galar.png",
  "https://media.52poke.com/wiki/thumb/0/08/555Darmanitan-Galar_Zen_Mode.png/450px-555Darmanitan-Galar_Zen_Mode.png",
  "https://media.52poke.com/wiki/thumb/4/46/562Yamask-Galar.png/450px-562Yamask-Galar.png",
  "https://media.52poke.com/wiki/thumb/1/11/618Stunfisk-Galar.png/450px-618Stunfisk-Galar.png"
  )