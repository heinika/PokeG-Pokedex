package com.heinika.pokeg.module.detailcompose


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.heinika.pokeg.ConfigMMKV
import com.heinika.pokeg.PokemonDataCache
import com.heinika.pokeg.R
import com.heinika.pokeg.info.*
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.model.PokemonName
import com.heinika.pokeg.model.PokemonSpecie
import com.heinika.pokeg.model.SpeciesEggGroup
import com.heinika.pokeg.module.detail.DetailViewModel
import com.heinika.pokeg.module.moves.compose.MoveCard
import com.heinika.pokeg.module.mypokemon.compose.PokemonAvatar
import com.heinika.pokeg.module.mypokemon.compose.PokemonCard
import com.heinika.pokeg.module.team.compose.TagCard
import com.heinika.pokeg.repository.res.ResUtils
import com.heinika.pokeg.ui.compose.SelectVersionDialog
import com.heinika.pokeg.ui.theme.*
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
  onTypeClick: (Type) -> Unit,
  onBack: () -> Unit
) {
  val pokemon = PokemonDataCache.pokemonList.first { it.globalId == globalId }
  val specieName = detailViewModel.getPokemonSpecieNameLiveData(pokemon.speciesId).observeAsState()
  val abilities = detailViewModel.getPokemonAbilitiesLiveData(pokemon.globalId).observeAsState()
  val species = detailViewModel.getPokemonSpecieLiveData(pokemon.speciesId).observeAsState()
  val specieFlavor = detailViewModel.getSpecieFlavorTextsLiveData(pokemon.speciesId).observeAsState()
  val eggGroup = detailViewModel.getSpecieEggGroupLiveData(pokemon.speciesId).observeAsState()
  val chainList = detailViewModel.getSpecieEvolutionChainLiveData(pokemon.speciesId).observeAsState()
  val otherForms = detailViewModel.speciesAllOtherFormsLiveData(pokemon.speciesId, pokemon.globalId).observeAsState()
  val versions = detailViewModel.getPokemonMoveVersionLiveData(pokemon.id, pokemon.speciesId).observeAsState()
  val favouritePokemonsState = remember { mutableStateListOf<String>().apply { addAll(ConfigMMKV.favoritePokemons) } }

  val defaultVersionId = ConfigMMKV.defaultVersion
  var selectedMoveVersionId by remember { mutableStateOf(-1) }
  val versionId: Int? = when {
    selectedMoveVersionId != -1 -> selectedMoveVersionId
    versions.value == null -> null
    versions.value!!.contains(defaultVersionId) -> defaultVersionId
    else -> versions.value!!.last()
  }

  var moveMethodId by remember { mutableStateOf(1) }

  var isShowSelectedDialog by remember { mutableStateOf(false) }

  val pokemonMoveMap = when (versionId) {
    null -> null
    else -> detailViewModel.getPokemonMoveLiveData(pokemon.id, pokemon.speciesId, versionId).observeAsState()
  }

  val selectedMoveVersionMap = if (selectedMoveVersionId != -1) {
    detailViewModel.getPokemonMoveLiveData(pokemon.id, pokemon.speciesId, selectedMoveVersionId).observeAsState()
  } else {
    null
  }

  Box {
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
    ) {
      item {
        Card(elevation = 8.dp, shape = RoundedCornerShape(bottomEnd = 26.dp, bottomStart = 26.dp)) {
          Box(
            Modifier
              .height(330.dp + SystemBar.statusBarHeightDp.dp)
              .typeBackground(pokemon.types)
          ) {
            Column(Modifier.fillMaxWidth()) {
              TopAppBar(
                content = {
                  IconButton(onClick = {
                    onBack()
                  }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                  }
                  NameRow(pokemon, favouritePokemonsState.contains(pokemon.globalId.toString()), specieName.value,
                    onFavouriteClick = {
                      ConfigMMKV.favoritePokemons = if (ConfigMMKV.favoritePokemons.contains(it.globalId.toString())) {
                        favouritePokemonsState.remove(it.globalId.toString())
                        ConfigMMKV.favoritePokemons - it.globalId.toString()
                      } else {
                        favouritePokemonsState.add(it.globalId.toString())
                        ConfigMMKV.favoritePokemons + it.globalId.toString()
                      }
                    })
                },
                backgroundColor = Color.Transparent,
                modifier = Modifier.padding(top = SystemBar.statusBarHeightDp.dp, start = 12.dp, end = 12.dp)
              )

              HeaderCard(pokemon, abilities.value, specieName.value, species.value, onAbilityClick, onTypeClick)
            }
          }
        }

        specieFlavor.value?.let { PokemonDescCard(it) }

        StatusCard(pokemon)

        EggCard(eggGroup.value, species.value)

        chainList.value?.let { speciesEvolutionChains ->
          if (speciesEvolutionChains.isNotEmpty()) {
            DetailCard(modifier = Modifier.padding(12.dp, 0.dp, 12.dp, 12.dp)) {
              Column(modifier = Modifier.padding(12.dp, 12.dp, 12.dp, 0.dp)) {
                speciesEvolutionChains.forEach { chain ->
                  Row(
                    modifier = Modifier
                      .fillMaxWidth()
                      .padding(bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                  ) {
                    val fromPokemon =
                      PokemonDataCache.pokemonList.first { it.id == chain.evolvedFromSpeciesId }
                    val toPokemon =
                      PokemonDataCache.pokemonList.first { it.id == chain.evolvedToSpeciesId }
                    PokemonAvatar(fromPokemon, onClick = { onPokemonItemClick(it) })
                    Text(
                      chain.getDescText(LocalContext.current),
                      modifier = Modifier.weight(1f),
                      textAlign = TextAlign.Center
                    )
                    PokemonAvatar(toPokemon, onClick = { onPokemonItemClick(it) })
                  }
                }
              }
            }
          }
        }

        otherForms.value?.run {
          forEachIndexed { index, it ->
            PokemonCard(
              pokemon = it,
              onClick = { onPokemonItemClick(it) },
              isPaddingBottom = index == size - 1,
              isFavourite = favouritePokemonsState.contains(it.globalId.toString()),
              onFavouriteClick = {
                ConfigMMKV.favoritePokemons = if (ConfigMMKV.favoritePokemons.contains(it.globalId.toString())) {
                  favouritePokemonsState.remove(it.globalId.toString())
                  ConfigMMKV.favoritePokemons - it.globalId.toString()
                } else {
                  favouritePokemonsState.add(it.globalId.toString())
                  ConfigMMKV.favoritePokemons + it.globalId.toString()
                }
              })
          }
        }


        versionId?.let {
          VersionCard(Modifier.padding(12.dp, 0.dp, 12.dp, 12.dp), it, onClick = {
            isShowSelectedDialog = true
          })
        }

        pokemonMoveMap?.value?.let { movesMap ->
          DetailCard(Modifier.padding(12.dp, 0.dp, 12.dp, 12.dp)) {
            Row(
              Modifier
                .fillMaxWidth()
                .height(46.dp)
            ) {
              movesMap.keys.sortedBy {
                when (it) {
                  2 -> 3
                  3 -> 4
                  4 -> 2
                  else -> it
                }
              }.forEach { methodId ->
                val isSelected = methodId == moveMethodId
                Box(modifier = Modifier
                  .weight(1f)
                  .fillMaxHeight()
                  .clip(MaterialTheme.shapes.medium)
                  .border(if (isSelected) 2.dp else 0.dp, if (isSelected) YellowLight else Color.Transparent, MaterialTheme.shapes.medium)
                  .clickable {
                    moveMethodId = methodId
                  }) {
                  Text(
                    text = ResUtils.getMoveMethodName(methodId, LocalContext.current),
                    Modifier
                      .align(Alignment.Center),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    color = if (isSelected) YellowLight else Color.White
                  )
                }
              }
            }
          }
        }
      }

      if (selectedMoveVersionId == -1) {
        pokemonMoveMap?.value?.let { movesMap ->
          items(movesMap[moveMethodId]!!) { moveItem ->
            MoveCard(move = Move.values().first { moveItem.id == it.id }, level = moveItem.level, onClick = {})
          }
        }
      } else {
        selectedMoveVersionMap?.value?.let { movesMap ->
          items(movesMap[moveMethodId]!!) { moveItem ->
            MoveCard(move = Move.values().first { moveItem.id == it.id }, level = moveItem.level, onClick = {})
          }
        }
      }
    }


    versions.value?.let { versions ->
      SelectVersionDialog(
        moveVersionList = versions.map { moveVersionId -> MoveVersion.values().first { it.ordinal == moveVersionId - 1 } },
        dialogState = isShowSelectedDialog,
        onDialogStateChange = { isShowSelectedDialog = it },
        onDismissRequest = { isShowSelectedDialog = false },
        onVersionItemClick = {
          moveMethodId = 1
          selectedMoveVersionId = it.id
          isShowSelectedDialog = false
        })
    }
  }

}


@Composable
fun EggCard(eggGroups: List<SpeciesEggGroup>?, pokemonSpecie: PokemonSpecie?) {
  val context = LocalContext.current
  DetailCard(modifier = Modifier.padding(12.dp)) {
    Row(
      modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth()
    ) {
      Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
        Text(text = stringResource(R.string.egg_group))
        Spacer(modifier = Modifier.height(8.dp))
        eggGroups?.let { speciesEggGroups -> Text(text = speciesEggGroups.joinToString(" ") { ResUtils.getEggGroupName(it.eggGroupId, context) }) }
      }
      Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
        Text(text = stringResource(R.string.egg_steps))
        Spacer(modifier = Modifier.height(8.dp))
        pokemonSpecie?.getEggSteps()?.let { Text(text = it) }
      }
      Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.weight(1f)) {
        Text(text = stringResource(R.string.grow_speed))
        Spacer(modifier = Modifier.height(8.dp))
        pokemonSpecie?.growthRateId?.let { Text(text = ResUtils.getGrowRate(it, context)) }
      }
    }
  }
}

@Composable
private fun StatusCard(pokemon: Pokemon) {
  DetailCard(modifier = Modifier.padding(12.dp, 0.dp)) {
    Column {
      StatProgress(
        statName = stringResource(R.string.sum_base_status),
        statValue = pokemon.totalBaseStat,
        color = progressHpColor,
        max = 700
      )
      StatProgress(
        statName = stringResource(R.string.hp),
        statValue = pokemon.hp,
        color = progressHpColor
      )
      StatProgress(
        statName = stringResource(R.string.atk),
        statValue = pokemon.atk,
        color = progressAttackColor
      )
      StatProgress(
        statName = stringResource(R.string.def),
        statValue = pokemon.def,
        color = progressDefenseColor
      )
      StatProgress(
        statName = stringResource(R.string.sp_atk),
        statValue = pokemon.spAtk,
        color = progressSpAttackColor
      )
      StatProgress(
        statName = stringResource(R.string.sp_def),
        statValue = pokemon.spDef,
        color = progressSpDefenseColor
      )
      StatProgress(
        Modifier.padding(bottom = 12.dp),
        stringResource(R.string.spd),
        pokemon.speed,
        color = progressSpeedColor
      )
    }
  }
}

@Composable
private fun StatProgress(
  modifier: Modifier = Modifier,
  statName: String,
  statValue: Int,
  color: Color,
  max: Int = 250,
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(top = 12.dp, start = 24.dp, end = 24.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(text = statName, modifier = Modifier.padding(end = 24.dp))
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(24.dp))
        .background(Color.White)
    ) {
      Text(
        text = "$statValue",
        modifier = Modifier
          .defaultMinSize(minWidth = 30.dp)
          .fillMaxWidth(statValue / max.toFloat())
          .clip(RoundedCornerShape(24.dp))
          .background(color)
          .padding(end = 8.dp),
        textAlign = TextAlign.End,
        style = MaterialTheme.typography.caption
      )
    }
  }
}

@Composable
private fun PokemonDescCard(desc: String) {
  DetailCard(
    modifier = Modifier
      .fillMaxWidth()
      .padding(12.dp)
  ) {
    Text(
      text = desc,
      style = MaterialTheme.typography.body1,
      modifier = Modifier.padding(8.dp),
      textAlign = TextAlign.Center
    )
  }
}

@Composable
fun DetailCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  Card(
    modifier = modifier,
    shape = RoundedCornerShape(16.dp),
    elevation = 4.dp,
    backgroundColor = Color(0xff162544)
  ) {
    content()
  }
}

@ExperimentalMaterialApi
@Composable
fun VersionCard(modifier: Modifier = Modifier, versionId: Int, onClick: () -> Unit) {
  Card(
    modifier = modifier,
    shape = MaterialTheme.shapes.medium,
    border = BorderStroke(2.dp, RashColor),
    backgroundColor = CarefulColor,
    elevation = 4.dp,
    onClick = { onClick() }
  ) {
    Row(modifier = Modifier.padding(12.dp)) {
      Text("当前技能版本：${ResUtils.getVersionName(versionId, LocalContext.current)}", color = RashColor)
    }
  }
}

@ExperimentalAnimationApi
@Composable
private fun NameRow(pokemon: Pokemon, isFavorite: Boolean, specieName: List<PokemonName>?, onFavouriteClick: (Pokemon) -> Unit) {
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

    IconButton(onClick = { onFavouriteClick(pokemon) }) {
      Icon(imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder, contentDescription = "")
    }

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
  onAbilityClick: (Ability) -> Unit,
  onTypeClick: (Type) -> Unit
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
      PokemonTypesRow(pokemon.types, onTypeClick)
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
fun PokemonTypesRow(types: List<Int>, onTypeClick: (Type) -> Unit) {
  Row {
    types.forEachIndexed { index, pokemonTypeId ->
      if (index == 0) {
        PokemonTypeCard(pokemonTypeId, modifier = Modifier.padding(end = 8.dp), onTypeClick)
      } else {
        PokemonTypeCard(pokemonTypeId, onTypeClick = onTypeClick)
      }
    }
  }
}

@ExperimentalMaterialApi
@Composable
fun PokemonTypeCard(typeId: Int, modifier: Modifier = Modifier, onTypeClick: (Type) -> Unit) {
  AttributeCard(
    getTypeString(LocalContext.current, typeId),
    Type.values()[typeId - 1].darkStartColor,
    modifier.width(44.dp),
    onclick = { onTypeClick(Type.values()[typeId - 1]) }
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