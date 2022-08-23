package com.heinika.pokeg.module.detailcompose


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.heinika.pokeg.PokemonDataCache
import com.heinika.pokeg.R
import com.heinika.pokeg.info.getTypeString
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.module.team.compose.TagCard
import com.heinika.pokeg.ui.theme.heightCardColor
import com.heinika.pokeg.ui.theme.specieNameCardColor
import com.heinika.pokeg.ui.theme.weightCardColor
import com.heinika.pokeg.utils.SystemBar
import com.heinika.pokeg.utils.getPokemonImageUrl
import com.heinika.pokeg.utils.toTypeColor


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun PokemonDetailScreen(
  globalId: Int,
  onPokemonItemClick: (Pokemon) -> Unit,
  onBack: () -> Unit
) {
  val pokemon = PokemonDataCache.pokemonList.first { it.globalId == globalId }
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .verticalScroll(rememberScrollState())
  ){
    Card(elevation = 8.dp, shape = RoundedCornerShape(bottomEnd = 26.dp, bottomStart = 26.dp)){
      Box(
        Modifier
          .height(300.dp + SystemBar.statusBarHeightDp.dp)
          .typeBackground(pokemon.types)
      ) {
        Column(Modifier.fillMaxWidth()) {
          TopAppBar(
            navigationIcon = {
              IconButton(onClick = {
                onBack()
              }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
              }
            },
            title = { NameRow(pokemon) },
            backgroundColor = Color.Transparent,
            modifier = Modifier.padding(top = SystemBar.statusBarHeightDp.dp)
          )

          HeaderCard(pokemon)
        }
      }
    }
  }
}

@ExperimentalAnimationApi
@Composable
private fun NameRow(pokemon: Pokemon) {
  Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
    Text(pokemon.name)
    Spacer(modifier = Modifier.width(8.dp))
    SpecieNameCard(pokemon.speciesId.toString())

    Spacer(
      modifier = Modifier
        .width(0.dp)
        .weight(1f)
    )
    Text(pokemon.getFormatId(), Modifier.padding(end = 16.dp))
  }
}

@Composable
fun SpecieNameCard(specieName: String) {
  Card(
    shape = RoundedCornerShape(16.dp),
    backgroundColor = specieNameCardColor,
    elevation = 4.dp
  ) {
    Text(
      specieName, style = MaterialTheme.typography.caption,
      modifier = Modifier.padding(6.dp, 3.dp)
    )
  }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
private fun HeaderCard(pokemon: Pokemon) {
  Box(Modifier.fillMaxWidth()) {
    Image(
      painter = rememberImagePainter(
        data = getPokemonImageUrl(pokemon.globalId, ""),
        builder = { crossfade(true) }
      ),
      contentDescription = "Picture of Pokemon",
      modifier = Modifier
        .align(Alignment.Center)
        .width(190.dp)
        .fillMaxHeight()
    )

    Column(Modifier.padding(start = 12.dp)) {
      PokemonTypesRow(pokemon.types)
      AttributeCard(Modifier.padding(top = 16.dp), attr = pokemon.getFormatHeight(), heightCardColor)
      AttributeCard(Modifier.padding(top = 16.dp), attr = pokemon.getFormatWeight(), weightCardColor)
    }


    Column(
      Modifier
        .padding(end = 12.dp)
        .align(Alignment.TopEnd),
      horizontalAlignment = Alignment.End
    ) {
//      TagCard(pokemon.eName)
//      TagCard(pokemon.jName)
//      TagCard(pokemon.generation)
//      TagCard(pokemon.shape)
//      TagCard(attr = pokemon.habitat)
      if (pokemon.isBaby) {
        TagCard(LocalContext.current.getString(R.string.baby))
      }
      if (pokemon.isLegendary) {
        TagCard(LocalContext.current.getString(R.string.legendary))
      }
      if (pokemon.isMythical) {
        TagCard(LocalContext.current.getString(R.string.mythical))
      }
    }
  }
}

@ExperimentalMaterialApi
@Composable
fun PokemonTypesRow(types: List<Int>) {
  Row(Modifier.padding(top = 16.dp)) {
    types.forEachIndexed { index, pokemonTypeId ->
      if (index == 0) {
        PokemonTypeCard(pokemonTypeId, modifier = Modifier.padding(end = 8.dp))
      } else {
        PokemonTypeCard(pokemonTypeId)
      }
    }
  }
}

@ExperimentalMaterialApi
@Composable
fun PokemonTypeCard(typeId: Int, modifier: Modifier = Modifier) {
  AttributeCard(
    modifier.width(44.dp),
    getTypeString(LocalContext.current, typeId),
    typeId.toTypeColor
  )
}

@ExperimentalMaterialApi
@Composable
fun AttributeCard(
  modifier: Modifier = Modifier,
  attr: String,
  color: Color,
  onclick: (() -> Unit)? = null
) {
  Card(
    modifier = modifier,
    backgroundColor = color,
    shape = RoundedCornerShape(16.dp),
    onClick = { onclick?.invoke() }
  ) {
    Text(
      attr, style = MaterialTheme.typography.caption,
      modifier = Modifier.padding(6.dp, 3.dp),
      textAlign = TextAlign.Center
    )
  }
}

fun Modifier.typeBackground(types: List<Int>): Modifier =
  if (types.size == 1) {
    background(color = types.first().toTypeColor)
  } else {
    background(
      brush = Brush.verticalGradient(
        types.map { it.toTypeColor }
      )
    )
  }