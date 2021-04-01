package com.heinika.pokeg.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity
@JsonClass(generateAdapter = true)
data class Pokemon(
  var page: Int = 0,
  @field:Json(name = "name") @PrimaryKey val name: String,
  @field:Json(name = "url") val url: String,
  var types: List<PokemonInfo.TypeResponse> = emptyList(),
  var totalBaseStat: Int = 0,
  var id: Int = 0
) {

//  fun getImageUrl(): String {
//    val index = url.split("/".toRegex()).dropLast(1).last()
//    return "https://pokeres.bastionbot.org/images/pokemon/$index.png"
//  }

  fun getImageUrl(): String {
    val index = url.split("/".toRegex()).dropLast(1).last().toInt()
    return when {
      index == 875 -> "https://media.52poke.com/wiki/thumb/e/e3/875Eiscue.png/600px-875Eiscue.png"
      index == 877 -> "https://media.52poke.com/wiki/thumb/5/51/877Morpeko.png/600px-877Morpeko.png"
      index == 888 -> "https://cdn.bulbagarden.net/upload/thumb/c/ca/888Zacian.png/600px-888Zacian.png"
      index == 889 -> "https://cdn.bulbagarden.net/upload/thumb/7/72/889Zamazenta.png/600px-889Zamazenta.png"
      index <= 890 -> {
//          "https://img.pokemondb.net/artwork/${name}.jpg"
        "https://pokeres.bastionbot.org/images/pokemon/$index.png"
      }
      index == 891 -> "https://cdn.bulbagarden.net/upload/4/47/891Kubfu.png"
      index == 892 -> "https://cdn.bulbagarden.net/upload/thumb/e/e7/892Urshifu-Single_Strike.png/600px-892Urshifu-Single_Strike.png"
      index == 893 -> "https://cdn.bulbagarden.net/upload/thumb/a/a5/893Zarude.png/250px-893Zarude.png"
      index == 894 -> "https://cdn.bulbagarden.net/upload/thumb/9/9b/894Regieleki.png/600px-894Regieleki.png"
      index == 895 -> "https://cdn.bulbagarden.net/upload/thumb/e/e8/895Regidrago.png/600px-895Regidrago.png"
      index == 896 -> "https://cdn.bulbagarden.net/upload/thumb/f/ff/896Glastrier.png/600px-896Glastrier.png"
      index == 897 -> "https://cdn.bulbagarden.net/upload/thumb/7/7e/897Spectrier.png/600px-897Spectrier.png"
      index == 898 -> "https://cdn.bulbagarden.net/upload/thumb/3/3c/898Calyrex.png/600px-898Calyrex.png"
      index == 10061 -> "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/2d7bbc09-b889-4fb5-89c5-390e7aa64cb4/d8dgmj3-5d2a1529-7a92-4a5b-bdfa-456dfea107c6.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOiIsImlzcyI6InVybjphcHA6Iiwib2JqIjpbW3sicGF0aCI6IlwvZlwvMmQ3YmJjMDktYjg4OS00ZmI1LTg5YzUtMzkwZTdhYTY0Y2I0XC9kOGRnbWozLTVkMmExNTI5LTdhOTItNGE1Yi1iZGZhLTQ1NmRmZWExMDdjNi5wbmcifV1dLCJhdWQiOlsidXJuOnNlcnZpY2U6ZmlsZS5kb3dubG9hZCJdfQ.BG07eG91ph0S-A3yaWSKGOZ9n1P7EpTkaNePukVFiv4"
      index == 10085 -> "https://cdn.bulbagarden.net/upload/2/28/Spr_6o_025_C.png"
      index == 10091 -> "https://cdn.bulbagarden.net/upload/thumb/9/91/019Rattata-Alola.png/600px-019Rattata-Alola.png"
      index == 10092 -> "https://archives.bulbagarden.net/media/upload/thumb/7/71/020Raticate-Alola.png/600px-020Raticate-Alola.png"
      index == 10093 -> "https://veekun.com/dex/media/pokemon/main-sprites/ultra-sun-ultra-moon/20-totem-alola.png"
      index == 10094 -> "https://cdn.bulbagarden.net/upload/thumb/1/17/025Pikachu-Original.png/600px-025Pikachu-Original.png"
      index == 10095 -> "https://cdn.bulbagarden.net/upload/thumb/4/44/025Pikachu-Hoenn.png/600px-025Pikachu-Hoenn.png"
      index == 10096 -> "https://cdn.bulbagarden.net/upload/thumb/a/a4/025Pikachu-Sinnoh.png/600px-025Pikachu-Sinnoh.png"
      index == 10097 -> "https://cdn.bulbagarden.net/upload/thumb/e/e6/025Pikachu-Unova.png/600px-025Pikachu-Unova.png"
      index == 10098 -> "https://cdn.bulbagarden.net/upload/thumb/4/44/025Pikachu-Kalos.png/600px-025Pikachu-Kalos.png"
      index == 10099 -> "https://cdn.bulbagarden.net/upload/thumb/e/e3/025Pikachu-Alola.png/600px-025Pikachu-Alola.png"
      index == 10100 -> "https://s1.52poke.wiki/wiki/thumb/3/3a/026Raichu-Alola.png/600px-026Raichu-Alola.png"
      index == 10101 -> "https://s1.52poke.wiki/wiki/thumb/c/c9/027Sandshrew-Alola.png/600px-027Sandshrew-Alola.png"
      index == 10102 -> "https://s1.52poke.wiki/wiki/thumb/b/bd/028Sandslash-Alola.png/600px-028Sandslash-Alola.png"
      index == 10103 -> "https://s1.52poke.wiki/wiki/thumb/3/35/037Vulpix-Alola.png/600px-037Vulpix-Alola.png"
      index == 10104 -> "https://s1.52poke.wiki/wiki/thumb/2/26/038Ninetales-Alola.png/600px-038Ninetales-Alola.png"
      index == 10105 -> "https://s1.52poke.wiki/wiki/thumb/1/10/050Diglett-Alola.png/600px-050Diglett-Alola.png"
      index == 10106 -> "https://s1.52poke.wiki/wiki/thumb/2/22/051Dugtrio-Alola.png/600px-051Dugtrio-Alola.png"
      index == 10107 -> "https://s1.52poke.wiki/wiki/thumb/e/e3/052Meowth-Alola.png/600px-052Meowth-Alola.png"
      index == 10108 -> "https://s1.52poke.wiki/wiki/thumb/8/80/053Persian-Alola.png/600px-053Persian-Alola.png"
      index == 10109 -> "https://s1.52poke.wiki/wiki/thumb/4/43/074Geodude-Alola.png/600px-074Geodude-Alola.png"
      index == 10110 -> "https://s1.52poke.wiki/wiki/thumb/6/62/075Graveler-Alola.png/600px-075Graveler-Alola.png"
      index == 10111 -> "https://s1.52poke.wiki/wiki/thumb/0/07/076Golem-Alola.png/600px-076Golem-Alola.png"
      index == 10112 -> "https://s1.52poke.wiki/wiki/thumb/e/e0/088Grimer-Alola.png/150px-088Grimer-Alola.png"
      index == 10113 -> "https://s1.52poke.wiki/wiki/thumb/1/15/089Muk-Alola.png/600px-089Muk-Alola.png"
      index == 10114 -> "https://s1.52poke.wiki/wiki/thumb/7/74/103Exeggutor-Alola.png/600px-103Exeggutor-Alola.png"
      index == 10115 -> "https://s1.52poke.wiki/wiki/thumb/0/06/105Marowak-Alola.png/600px-105Marowak-Alola.png"
      index == 10116 -> "https://cdn.bulbagarden.net/upload/thumb/6/67/658Greninja.png/600px-658Greninja.png"
      index == 10121 -> "https://cdn.bulbagarden.net/upload/thumb/b/ba/735Gumshoos.png/600px-735Gumshoos.png"
      index == 10122 -> "https://cdn.bulbagarden.net/upload/thumb/4/4e/738Vikavolt.png/600px-738Vikavolt.png"
      index == 10127 -> "https://www.pikpng.com/pngl/b/431-4317690_wishiwashi-regular-and-school-form-now-complete-wishiwashi.png"
      index == 10128 -> "https://static.miraheze.org/atrociousgameplaywiki/thumb/c/c5/Lurantis.png/600px-Lurantis.png"
      index == 10129 -> "https://cdn.bulbagarden.net/upload/thumb/7/72/758Salazzle.png/600px-758Salazzle.png"
      index == 10130 -> "https://cdn.bulbagarden.net/upload/d/da/HOME774O.png"
      index == 10131 -> "https://cdn.bulbagarden.net/upload/5/58/HOME774Y.png"
      index == 10132 -> "https://cdn.bulbagarden.net/upload/0/01/HOME774G.png"
      index == 10133 -> "https://cdn.bulbagarden.net/upload/b/bb/HOME774B.png"
      index == 10134 -> "https://cdn.bulbagarden.net/upload/6/6e/HOME774I.png"
      index == 10135 -> "https://cdn.bulbagarden.net/upload/f/f9/HOME774V.png"
      index == 10136 -> "https://cdn.bulbagarden.net/upload/8/80/HOME774R.png"
      index == 10137 -> "https://cdn.bulbagarden.net/upload/d/da/HOME774O.png"
      index == 10138 -> "https://cdn.bulbagarden.net/upload/5/58/HOME774Y.png"
      index == 10139 -> "https://cdn.bulbagarden.net/upload/0/01/HOME774G.png"
      index == 10140 -> "https://cdn.bulbagarden.net/upload/b/bb/HOME774B.png"
      index == 10141 -> "https://cdn.bulbagarden.net/upload/6/6e/HOME774I.png"
      index == 10142 -> "https://cdn.bulbagarden.net/upload/f/f9/HOME774V.png"
      index == 10144 -> "https://cdn.bulbagarden.net/upload/thumb/9/9b/778Mimikyu.png/600px-778Mimikyu.png"
      index == 10145 -> "https://cdn.bulbagarden.net/upload/3/35/HOME778B.png"
      index == 10147 -> "https://cdn.bulbagarden.net/upload/4/4c/HOME801O.png"
      index == 10148 -> "https://cdn.bulbagarden.net/upload/thumb/7/75/025Pikachu-Partner.png/600px-025Pikachu-Partner.png"
      index == 10151 -> "https://cdn.bulbagarden.net/upload/thumb/5/51/744Rockruff.png/600px-744Rockruff.png"
      index == 10155 -> "https://cdn.bulbagarden.net/upload/8/80/800Necrozma-Dusk_Mane.png"
      index == 10156 -> "https://cdn.bulbagarden.net/upload/7/7a/800Necrozma-Dawn_Wings.png"
      index == 10157 -> "https://cdn.bulbagarden.net/upload/thumb/8/8b/800Necrozma-Ultra.png/600px-800Necrozma-Ultra.png"
      index == 10158 -> "https://cdn.bulbagarden.net/upload/0/09/052Meowth-Galar.png"
      index == 10159 -> "https://cdn.bulbagarden.net/upload/9/92/077Ponyta-Galar.png"
      index == 10160 -> "https://cdn.bulbagarden.net/upload/e/e0/078Rapidash-Galar.png"
      index == 10161 -> "https://cdn.bulbagarden.net/upload/9/9f/079Slowpoke-Galar.png"
      index == 10162 -> "https://cdn.bulbagarden.net/upload/8/8b/080Slowbro-Galar.png"
      index == 10163 -> "https://cdn.bulbagarden.net/upload/7/7d/083Farfetch%27d-Galar.png"
      index == 10164 -> "https://cdn.bulbagarden.net/upload/thumb/3/38/110Weezing-Galar.png/600px-110Weezing-Galar.png"
      name.contains("totem") -> "https://img.pokemondb.net/artwork/vector/${name.dropLast(6)}.png"
      else -> "https://img.pokemondb.net/artwork/vector/${name}.png"
    }
  }

  fun updatePokemonInfo(pokemonInfo: PokemonInfo): Pokemon {
    types = pokemonInfo.types
    totalBaseStat = pokemonInfo.getTotalStat()
    id = pokemonInfo.id
    return this
  }

  fun getIdString(): String = String.format("#%03d", id)
}


