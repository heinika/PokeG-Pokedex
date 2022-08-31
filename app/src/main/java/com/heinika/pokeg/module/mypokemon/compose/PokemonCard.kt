package com.heinika.pokeg.module.mypokemon.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.heinika.pokeg.info.getTypeString
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.repository.res.ResUtils
import com.heinika.pokeg.ui.theme.BlackBackgroundColor
import com.heinika.pokeg.ui.theme.grassColor
import com.heinika.pokeg.utils.rememberFlavorPainter
import com.heinika.pokeg.utils.toTypeColor


@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun PokemonCard(
  pokemon: Pokemon,
  isPaddingBottom: Boolean = false,
  onClick: (pokemon: Pokemon) -> Unit
) {
  Card(
    Modifier
      .padding(12.dp, 12.dp, 12.dp, if (isPaddingBottom) 12.dp else 0.dp)
      .fillMaxWidth()
      .height(120.dp)
      .clip(RoundedCornerShape(16.dp))
      .clickable {
        onClick(pokemon)
      },
    backgroundColor = BlackBackgroundColor,
    elevation = 10.dp
  ) {
    Row(
      Modifier
        .fillMaxWidth()
        .padding(12.dp, 12.dp, 12.dp, 12.dp)
    ) {

      PokemonAvatar(pokemon, onClick = { onClick(pokemon) })

      Column(
        Modifier
          .width(0.dp)
          .weight(1f)
          .fillMaxHeight()
      ) {
        Box(
          Modifier
            .height(0.dp)
            .weight(1f)
            .padding(12.dp),
          contentAlignment = Alignment.Center
        ) {

          Column {
            Text(
              text = ResUtils.getNameById(
                pokemon.id,
                form = pokemon.form,
                context = LocalContext.current,
              ),
              color = Color.White,
              fontSize = 16.sp,
              textAlign = TextAlign.Center,
              fontWeight = FontWeight.Bold,
              maxLines = 1
            )

            Row(modifier = Modifier.padding(top = 24.dp)) {
              pokemon.types.forEachIndexed { index, pokemonType ->
                TypeCard(getTypeString(LocalContext.current, pokemonType), pokemonType.toTypeColor)
                if (index != pokemon.types.size - 1) {
                  Spacer(modifier = Modifier.width(8.dp))
                }
              }
            }
          }
        }
      }
      Column(
        Modifier.padding(end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        NumberText(
          String.format("#%03d", pokemon.id),
          Modifier
            .height(0.dp)
            .weight(1f)
            .padding(top = 12.dp)
        )
        NumberText("${pokemon.totalBaseStat}", modifier = Modifier.padding(bottom = 12.dp))
      }
    }
  }
}

@Composable
fun NumberText(content: String, modifier: Modifier = Modifier) =
  Text(modifier = modifier, text = content, fontSize = 12.sp, color = Color.White)

@Composable
fun TypeCard(typeName: String = "草", color: Color = grassColor) {
  Box(
    Modifier
      .width(44.dp)
      .height(25.dp)
      .clip(RoundedCornerShape(12.dp))
      .background(color)
  ) {
    Text(text = typeName, Modifier.align(Alignment.Center), color = Color.White)
  }
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Preview
@Composable
fun PokemonCardPreview() {
  PokemonCard(
    Pokemon(
      id = 1,
      1,
      speciesId = 1,
      name = "pikaqiu",
      listOf(12, 4),
      320,
      12,
      12,
      12,
      12,
      12,
      12,
      12,
      12,
      12,
      1,
    ),
    onClick = {},
  )
}

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun PokemonAvatar(pokemon: Pokemon, onClick: (pokemon: Pokemon) -> Unit) {
  Card(
    modifier = Modifier
      .size(96.dp)
      .padding(4.dp),
    shape = RoundedCornerShape(16.dp),
    onClick = { onClick(pokemon) }
  ) {
    if (pokemon.types.size == 2) {
      Image(
        painter = rememberFlavorPainter(data = pokemon.getImageUrl()),
        contentDescription = "Picture of Pokemon",
        modifier = Modifier
          .background(
            brush = Brush.linearGradient(
              pokemon.types.map { it.toTypeColor },
              Offset.Zero,
              Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY),
              TileMode.Clamp
            )
          )
          .padding(4.dp)
      )
    } else {
      Image(
        painter = rememberFlavorPainter(data = pokemon.getImageUrl()),
        contentDescription = "Picture of Pokemon",
        modifier = Modifier
          .background(
            color = pokemon.types[0].toTypeColor
          )
          .padding(4.dp)
      )
    }
  }
}