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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.heinika.pokeg.PokemonDataCache
import com.heinika.pokeg.R
import com.heinika.pokeg.info.Ability
import com.heinika.pokeg.info.Generation
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.info.getTypeString
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.model.PokemonName
import com.heinika.pokeg.model.PokemonSpecie
import com.heinika.pokeg.module.detail.DetailViewModel
import com.heinika.pokeg.module.team.compose.TagCard
import com.heinika.pokeg.repository.res.ResUtils
import com.heinika.pokeg.ui.theme.abilityCardColor
import com.heinika.pokeg.ui.theme.heightCardColor
import com.heinika.pokeg.ui.theme.specieNameCardColor
import com.heinika.pokeg.ui.theme.weightCardColor
import com.heinika.pokeg.utils.*


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun PokemonDetailScreen(
  globalId: Int,
  detailViewModel: DetailViewModel,
  onPokemonItemClick: (Pokemon) -> Unit,
  onAbilityClick: (Ability) -> Unit,
  onBack: () -> Unit
) {
  val pokemon = PokemonDataCache.pokemonList.first { it.globalId == globalId }
  val specieName = detailViewModel.getPokemonSpecieNameLiveData(pokemon.speciesId).observeAsState()
  val abilities = detailViewModel.getPokemonAbilitiesLiveData(pokemon.globalId).observeAsState()
  val species = detailViewModel.getPokemonSpecieLiveData(pokemon.speciesId).observeAsState()
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight()
      .verticalScroll(rememberScrollState())
  ) {
    Card(elevation = 8.dp, shape = RoundedCornerShape(bottomEnd = 26.dp, bottomStart = 26.dp)) {
      Box(
        Modifier
          .height(330.dp + SystemBar.statusBarHeightDp.dp)
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
            title = { NameRow(pokemon, specieName.value) },
            backgroundColor = Color.Transparent,
            modifier = Modifier.padding(top = SystemBar.statusBarHeightDp.dp)
          )

          HeaderCard(pokemon, abilities.value, specieName.value, species.value, onAbilityClick)
        }
      }
    }
  }
}

@ExperimentalAnimationApi
@Composable
private fun NameRow(pokemon: Pokemon, specieName: List<PokemonName>?) {
  Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
    Text(ResUtils.getNameById(pokemon.id, pokemon.name, pokemon.form, LocalContext.current))
    Spacer(modifier = Modifier.width(8.dp))
    if (!specieName.isNullOrEmpty()) {
      SpecieNameCard(specieName[0].genus)
    }

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
private fun HeaderCard(
  pokemon: Pokemon,
  abilities: List<Ability>?,
  pokemonNames: List<PokemonName>?,
  species: PokemonSpecie?,
  onAbilityClick: (Ability) -> Unit
) {
  Box(Modifier.fillMaxWidth()) {
    Image(
      painter = rememberImagePainter(
        data = getPokemonImageUrl(pokemon.globalId, pokemon.name),
        builder = { crossfade(true) }
      ),
      contentDescription = "Picture of Pokemon",
      modifier = Modifier
        .align(Alignment.Center)
        .width(220.dp)
        .fillMaxHeight()
    )

    Column(
      Modifier
        .padding(start = 12.dp)
        .align(Alignment.TopStart)
    ) {
      PokemonTypesRow(pokemon.types)
      abilities?.forEach { AbilityRow(it, onAbilityClick) }
      AttributeCard(attr = pokemon.getFormatHeight(), heightCardColor)
      AttributeCard(attr = pokemon.getFormatWeight(), weightCardColor)
    }


    Column(
      Modifier
        .padding(end = 12.dp)
        .align(Alignment.TopEnd),
      horizontalAlignment = Alignment.End
    ) {
      pokemonNames?.first { it.localLanguageId.isEnId }?.let { TagCard(it.name) }
      pokemonNames?.first { it.localLanguageId.isJaId }?.let { TagCard(it.name) }
      TagCard(stringResource(id = Generation.values()[pokemon.generationId - 1].resId))
      species?.shapeId?.let { TagCard(ResUtils.getShape(it, LocalContext.current)) }
      species?.habitatId?.let { if (it.isNotEmpty()) TagCard(ResUtils.getHabitat(it.toInt(), LocalContext.current)) }
      if (species?.isBaby?.toBoolean == true) {
        TagCard(LocalContext.current.getString(R.string.baby))
      }
      if (species?.isLegendary?.toBoolean == true) {
        TagCard(LocalContext.current.getString(R.string.legendary))
      }
      if (species?.isMythical?.toBoolean == true) {
        TagCard(LocalContext.current.getString(R.string.mythical))
      }
    }
  }
}

@ExperimentalMaterialApi
@Composable
fun PokemonTypesRow(types: List<Int>) {
  Row {
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
    getTypeString(LocalContext.current, typeId),
    Type.values()[typeId - 1].darkStartColor,
    modifier.width(44.dp)
  )
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun AbilityRow(ability: Ability, onAbilityClick: (Ability) -> Unit) {
  AttributeCard(
    attr = stringResource(id = ability.nameResId),
    color = abilityCardColor,
    onclick = {
      onAbilityClick(ability)
    }
  )
}

@ExperimentalMaterialApi
@Composable
fun AttributeCard(
  attr: String,
  color: Color,
  modifier: Modifier = Modifier,
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