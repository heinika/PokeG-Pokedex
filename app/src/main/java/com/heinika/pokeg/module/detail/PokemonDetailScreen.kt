package com.heinika.pokeg.module.detail


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.heinika.pokeg.module.moves.compose.MoveCard
import com.heinika.pokeg.module.team.compose.TagCard
import com.heinika.pokeg.repository.res.ResUtils
import com.heinika.pokeg.ui.compose.PokemonAvatar
import com.heinika.pokeg.ui.compose.PokemonCard
import com.heinika.pokeg.ui.compose.SelectVersionDialog
import com.heinika.pokeg.ui.theme.*
import com.heinika.pokeg.utils.*


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@ExperimentalAnimationApi
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

  LaunchedEffect(key1 = null, block = {
    detailViewModel.refreshPokemonMoveVersion(globalId, pokemon.speciesId)
    detailViewModel.refreshPokemonNameList(pokemon.speciesId)
    detailViewModel.refreshPokemonAbilityList(pokemon.globalId)
    detailViewModel.refreshPokemonSpecie(pokemon.speciesId)
    detailViewModel.refreshSpeciesAllOtherForms(pokemon.speciesId, pokemon.globalId)
    detailViewModel.refreshSpecieEggGroupList(pokemon.speciesId)
    detailViewModel.refreshSpecieFlavorText(pokemon.speciesId)
    detailViewModel.refreshSpecieEvolutionChainList(pokemon.speciesId)
  })
  val pokemonNameList = remember { detailViewModel.pokemonNameList }
  val abilityList = remember { detailViewModel.abilityList }
  val species = remember { detailViewModel.pokemonSpecie }
  val specieFlavorText = remember { detailViewModel.specieFlavorText }
  val speciesEggGroupList = remember { detailViewModel.speciesEggGroupList }
  val chainList = remember { detailViewModel.speciesEvolutionChainList }
  val otherForms = remember { detailViewModel.allOtherFormList }
  val versions = remember { detailViewModel.versionIdList }
  val favouritePokemonsState = remember { mutableStateListOf<String>().apply { addAll(ConfigMMKV.favoritePokemons) } }

  val versionId = remember { detailViewModel.versionId }
  val moveMethodId = remember { detailViewModel.moveMethodId }
  val pokemonMoveMap = remember { detailViewModel.pokemonMoveMap }

  var isShowSelectedDialog by remember { mutableStateOf(false) }


  Box(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
    LazyColumn(
      modifier = Modifier
        .fillMaxSize()
    ) {
      item {
        Card(shape = RoundedCornerShape(bottomEnd = 26.dp, bottomStart = 26.dp)) {
          Box(
            Modifier
              .height(330.dp + SystemBar.statusBarHeightDp.dp)
              .typeBackground(pokemon.types)
          ) {
            Column(Modifier.fillMaxWidth()) {
              SmallTopAppBar(
                navigationIcon = {
                  IconButton(onClick = {
                    onBack()
                  }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
                  }
                },
                title = {
                  NameRow(pokemon, favouritePokemonsState.contains(pokemon.globalId.toString()), pokemonNameList.toList(),
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
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent),
                modifier = Modifier.padding(top = SystemBar.statusBarHeightDp.dp, start = 12.dp, end = 12.dp).shadow(4.dp,MaterialTheme.shapes.small)
              )

              HeaderCard(pokemon, abilityList.toList(), pokemonNameList.toList(), species.value, onAbilityClick, onTypeClick)
            }
          }
        }

        PokemonDescCard(specieFlavorText.value)

        StatusCard(pokemon)

        EggCard(speciesEggGroupList.toList(), species.value)


        if (chainList.isNotEmpty()) {
          DetailCard(modifier = Modifier.padding(12.dp, 0.dp, 12.dp, 12.dp)) {
            Column(modifier = Modifier.padding(12.dp, 12.dp, 12.dp, 0.dp)) {
              chainList.forEach { chain ->
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

        otherForms.toList().forEachIndexed { index, it ->
          PokemonCard(
            pokemon = it,
            onClick = { onPokemonItemClick(it) },
            isPaddingBottom = index == otherForms.size - 1,
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


        versionId.value?.let {
          VersionCard(Modifier.padding(12.dp, 0.dp, 12.dp, 12.dp), it, onClick = {
            isShowSelectedDialog = true
          })
        }


        DetailCard(Modifier.padding(12.dp, 0.dp, 12.dp, 12.dp)) {
          Row(
            Modifier
              .fillMaxWidth()
              .height(46.dp)
          ) {
            pokemonMoveMap.value.keys.sortedBy {
              when (it) {
                2 -> 3
                3 -> 4
                4 -> 2
                else -> it
              }
            }.forEach { methodId ->
              val isSelected = methodId == moveMethodId.value
              Box(modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(MaterialTheme.shapes.small)
                .border(if (isSelected) 2.dp else 0.dp, if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent, MaterialTheme.shapes.small)
                .clickable {
                  detailViewModel.changeMethodId(methodId)
                }) {
                Text(
                  text = ResUtils.getMoveMethodName(methodId, LocalContext.current),
                  Modifier
                    .align(Alignment.Center),
                  style = MaterialTheme.typography.titleMedium,
                  textAlign = TextAlign.Center,
                  color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
              }
            }

          }
        }
      }

      pokemonMoveMap.value[moveMethodId.value]?.let { moveItemList ->
        items(moveItemList) { moveItem ->
          MoveCard(move = Move.values().first { moveItem.id == it.id }, level = moveItem.level, onClick = {})
        }
      }
    }



    SelectVersionDialog(
      moveVersionList = versions.map { moveVersionId -> MoveVersion.values().first { it.ordinal == moveVersionId - 1 } },
      dialogState = isShowSelectedDialog,
      onDialogStateChange = { isShowSelectedDialog = it },
      onDismissRequest = { isShowSelectedDialog = false },
      onVersionItemClick = {
        detailViewModel.refreshPokemonMoveMap(globalId, pokemon.speciesId, it.id)
        isShowSelectedDialog = false
      })

  }

}


@Composable
fun EggCard(eggGroups: List<SpeciesEggGroup>, pokemonSpecie: PokemonSpecie?) {
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
        Text(text = eggGroups.joinToString(" ") { ResUtils.getEggGroupName(it.eggGroupId, context) })
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
        style = MaterialTheme.typography.titleSmall
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
      style = MaterialTheme.typography.bodyMedium,
      modifier = Modifier.padding(8.dp),
      textAlign = TextAlign.Center
    )
  }
}

@Composable
fun DetailCard(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
  Card(
    modifier = modifier,
    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
    shape = MaterialTheme.shapes.small
  ) {
    content()
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VersionCard(modifier: Modifier = Modifier, versionId: Int, onClick: () -> Unit) {
  Card(
    modifier = modifier,
    shape = MaterialTheme.shapes.medium,
    border = BorderStroke(2.dp, RashColor),
    onClick = { onClick() }
  ) {
    Row(modifier = Modifier.padding(12.dp)) {
      Text("当前技能版本：${ResUtils.getVersionName(versionId, LocalContext.current)}", color = RashColor)
    }
  }
}

@ExperimentalAnimationApi
@Composable
private fun NameRow(pokemon: Pokemon, isFavorite: Boolean, specieName: List<PokemonName>, onFavouriteClick: (Pokemon) -> Unit) {
  Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
    Text(ResUtils.getNameById(pokemon.id, pokemon.name, pokemon.form, LocalContext.current),style = MaterialTheme.typography.titleMedium)
    Spacer(modifier = Modifier.width(8.dp))
    if (specieName.isNotEmpty()) {
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

    Text(pokemon.getFormatId(), Modifier.padding(end = 16.dp), style = MaterialTheme.typography.labelSmall)
  }
}

@Composable
fun SpecieNameCard(specieName: String) {
  Card(
    shape = RoundedCornerShape(16.dp),
  ) {
    Text(
      specieName, style = MaterialTheme.typography.labelSmall,
      modifier = Modifier.padding(6.dp, 3.dp)
    )
  }
}

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
private fun HeaderCard(
  pokemon: Pokemon,
  abilities: List<Ability>,
  pokemonNames: List<PokemonName>,
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
      abilities.forEach { AbilityRow(it, onAbilityClick) }
      AttributeCard(attr = pokemon.getFormatHeight(), heightCardColor)
      AttributeCard(attr = pokemon.getFormatWeight(), weightCardColor)
    }


    Column(
      Modifier
        .padding(end = 12.dp)
        .align(Alignment.TopEnd),
      horizontalAlignment = Alignment.End
    ) {
      if (pokemonNames.isNotEmpty()) {
        TagCard(pokemonNames.first { it.localLanguageId.isEnId }.name)
        TagCard(pokemonNames.first { it.localLanguageId.isJaId }.name)
      }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonTypeCard(typeId: Int, modifier: Modifier = Modifier, onTypeClick: (Type) -> Unit) {
  Card(
    modifier = modifier.width(44.dp),
    shape = MaterialTheme.shapes.medium,
    colors = CardDefaults.cardColors(containerColor = Type.values()[typeId - 1].darkStartColor),
    onClick = { onTypeClick(Type.values()[typeId - 1])  }
  ) {
    Text(
      getTypeString(LocalContext.current, typeId), style = MaterialTheme.typography.titleSmall,
      modifier = Modifier.fillMaxWidth().padding(6.dp, 3.dp),
      textAlign = TextAlign.Center
    )
  }
}

@ExperimentalAnimationApi
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AttributeCard(
  attr: String,
  color: Color,
  modifier: Modifier = Modifier,
  onclick: (() -> Unit)? = null
) {
  Card(
    modifier = modifier,
    shape = MaterialTheme.shapes.medium,
    colors = CardDefaults.cardColors(containerColor = color),
    onClick = { onclick?.invoke() }
  ) {
    Text(
      attr, style = MaterialTheme.typography.titleSmall,
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