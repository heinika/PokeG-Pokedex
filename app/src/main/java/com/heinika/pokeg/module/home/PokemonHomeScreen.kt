package com.heinika.pokeg.module.home

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import com.heinika.pokeg.ConfigMMKV
import com.heinika.pokeg.ConfigMMKV.favoritePokemons
import com.heinika.pokeg.R
import com.heinika.pokeg.info.BaseStatus
import com.heinika.pokeg.info.DexType
import com.heinika.pokeg.info.MoveVersion
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.module.main.MainViewModel
import com.heinika.pokeg.module.mypokemon.compose.PokemonCard
import com.heinika.pokeg.ui.compose.SelectVersionDialog
import com.heinika.pokeg.utils.SystemBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalComposeUiApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun PokemonHomeScreen(mainViewModel: MainViewModel, onDrawerItemClick: (screenName: String) -> Unit, onPokemonItemClick: (Pokemon) -> Unit) {
  val sortedPokemonList = remember { mainViewModel.pokemonSortStateList }
  val scaffoldState = rememberScaffoldState()
  val scope = rememberCoroutineScope()
  var moveVersion by remember { mutableStateOf(MoveVersion.values()[ConfigMMKV.defaultVersion - 1]) }
  var isShowSelectVersionDialog by remember { mutableStateOf(false) }

  Box {
    Scaffold(
      scaffoldState = scaffoldState,
      drawerShape = MaterialTheme.shapes.small,
      backgroundColor = MaterialTheme.colorScheme.background,
      contentColor = MaterialTheme.colorScheme.onBackground,
      drawerContent = {
        HomeLeftDrawer(moveVersion = moveVersion, onChangeVersionClick = { isShowSelectVersionDialog = true }, onDrawerItemClick = onDrawerItemClick)
      }) { paddingValues ->
      paddingValues.calculateTopPadding()

      val bottomDrawerState = rememberBottomDrawerState(initialValue = BottomDrawerValue.Closed)
      if (!mainViewModel.isLoading.value) {
        val types = remember { mutableStateOf(mainViewModel.filterTypeList.map { Type.values()[it - 1] }) }
        val generations = remember { mainViewModel.filterGenerations }
        val baseStatusList = remember { mainViewModel.sortBaseStatusList }
        val selectedBodyStatus = remember { mainViewModel.selectedBodyStatus }
        val tags = remember { mainViewModel.filterTags }
        val favouritePokemonsState = remember { mutableStateListOf<String>().apply { addAll(favoritePokemons) } }
        val isDesc = remember { mainViewModel._isSortDesc }

        BottomDrawer(
          drawerState = bottomDrawerState,
          gesturesEnabled = bottomDrawerState.isOpen,
          drawerShape = MaterialTheme.shapes.small,
          drawerContent = {
            HomeBottomDrawer(
              types = types.value,
              generations = generations.value,
              baseStatusList = baseStatusList.toList(),
              isBaseStatusDesc = isDesc.value,
              selectedBodyStatus = selectedBodyStatus.value,
              selectedTags = tags,
              onTypeSelectedChange = { typeList ->
                types.value = typeList
                mainViewModel.filterTypeList = typeList.map { it.typeId }
                mainViewModel.startSortAndFilter()
              },
              onSelectedGenerationChange = { generationList ->
                mainViewModel.changeGenerations(generationList)
                mainViewModel.startSortAndFilter()
              },
              onStatusChipClick = {
                if (baseStatusList.contains(it)) {
                  baseStatusList.remove(it)
                } else {
                  baseStatusList.add(it)
                }
                mainViewModel.changeBodyStatus(null)
                mainViewModel.startSortAndFilter()
              },
              onBaseStatusSumClick = {
                if (baseStatusList.size == 6) {
                  baseStatusList.clear()
                } else {
                  baseStatusList.clear()
                  baseStatusList.addAll(BaseStatus.values())
                }
                mainViewModel.changeBodyStatus(null)
                mainViewModel.startSortAndFilter()
              },
              onBaseStatusDescClick = {
                if (!isDesc.value) {
                  mainViewModel.changeSortOrder()
                }
              },
              onBaseStatusAscClick = {
                if (isDesc.value) {
                  mainViewModel.changeSortOrder()
                }
              },
              onBodyStatusChipClick = {
                val selectedResult = if (it == selectedBodyStatus.value) null else it
                baseStatusList.clear()
                mainViewModel.changeBodyStatus(selectedResult)
                mainViewModel.startSortAndFilter()
              },
              onTagClick = {
                if (tags.contains(it)) {
                  tags.remove(it)
                } else {
                  tags.add(it)
                }
                mainViewModel.startSortAndFilter()
              }
            )
          },
        ) {
          ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (pokemonColumn, actionsRow) = createRefs()

            val pokemonColumnState = rememberLazyListState()
            var dexType by remember { mutableStateOf(DexType.Global) }
            var isSearchMode by remember { mutableStateOf(false) }
            var searchContent by remember { mutableStateOf("") }
            val focusRequester = remember { FocusRequester() }

            val keyboardController = LocalSoftwareKeyboardController.current

            LazyColumn(
              modifier = Modifier.constrainAs(pokemonColumn) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
              },
              state = pokemonColumnState
            ) {
              item {
                CenterAlignedTopAppBar(
                  modifier = Modifier.padding(top = SystemBar.statusBarHeightDp.dp, start = 12.dp, end = 12.dp),
                  title = {
                    Box {
                      OutlinedTextField(
                        value = searchContent,
                        onValueChange = {
                          searchContent = it
                          mainViewModel.setSearchText(it)
                        },
                        modifier = Modifier
                          .alpha(if (isSearchMode) 1f else 0f)
                          .focusRequester(focusRequester),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        keyboardActions = KeyboardActions(onSearch = {
                          keyboardController?.hide()
                        }),
                        placeholder = { Text(text = stringResource(id = androidx.appcompat.R.string.search_menu_title)) },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                          unfocusedBorderColor = Color.Transparent,
                          focusedBorderColor = Color.Transparent,
                          cursorColor = MaterialTheme.colorScheme.primary
                        )
                      )

                      if (!isSearchMode) {
                        Row(
                          modifier = Modifier
                            .fillMaxSize()
                            .clickable { }
                            .padding(0.dp,12.dp)
                            .align(Alignment.CenterStart), verticalAlignment = Alignment.CenterVertically
                        ) {
                          Text(stringResource(id = dexType.stringId))
                        }
                      }
                    }
                  },
                  actions = {
                    if (!isSearchMode) {
                      Icon(imageVector = Icons.Default.LocationOn, contentDescription = "", modifier = Modifier
                        .clickable {
                          dexType = dexType.next()
                          mainViewModel.changDexType(dexType)
                          mainViewModel.startSortAndFilter()
                        }
                        .padding(12.dp))
                    }

                    Icon(imageVector = Icons.Default.Search, contentDescription = "", modifier = Modifier
                      .clickable {
                        isSearchMode = true
                        focusRequester.requestFocus()
                        keyboardController?.show()
                      }
                      .padding(12.dp))
                  },
                  navigationIcon = {
                    if (isSearchMode) {
                      Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "", modifier = Modifier
                        .clickable {
                          isSearchMode = false
                          keyboardController?.hide()
                        }
                        .padding(12.dp))
                    } else {
                      Icon(imageVector = Icons.Default.Menu, contentDescription = "", modifier = Modifier
                        .clickable {
                          scope.launch {
                            scaffoldState.drawerState.open()
                          }
                        }
                        .padding(12.dp))
                    }
                  })
              }

              items(sortedPokemonList) { pokemon ->
                PokemonCard(pokemon = pokemon, onClick = { onPokemonItemClick(pokemon) },
                  isFavourite = favouritePokemonsState.contains(pokemon.globalId.toString()),
                  onFavouriteClick = {
                    favoritePokemons = if (favoritePokemons.contains(it.globalId.toString())) {
                      favouritePokemonsState.remove(it.globalId.toString())
                      favoritePokemons - it.globalId.toString()
                    } else {
                      favouritePokemonsState.add(it.globalId.toString())
                      favoritePokemons + it.globalId.toString()
                    }
                  })
              }
            }

            AnimatedVisibility(
              visible = !pokemonColumnState.isScrollInProgress,
              enter = fadeIn() + slideIn(initialOffset = {
                IntOffset(
                  0,
                  SystemBar.statusBarHeightDp.toInt()
                )
              }),
              exit = slideOut(targetOffset = {
                IntOffset(
                  0,
                  SystemBar.statusBarHeightDp.toInt()
                )
              }) + fadeOut(),
              modifier = Modifier.constrainAs(actionsRow) {
                bottom.linkTo(parent.bottom, Dp(SystemBar.navigationBarHeightDp))
                start.linkTo(parent.start)
                end.linkTo(parent.end)
              }) {
              Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {

                FloatingActionButton(
                  onClick = {
                    scope.launch {
                      pokemonColumnState.scrollToItem(0)
                    }
                  }
                ) {
                  Image(
                    painter = painterResource(id = R.drawable.ic_arrow_top),
                    contentDescription = ""
                  )
                }

                FloatingActionButton(
                  onClick = {
                    scope.launch {
                      isSearchMode = false
                      bottomDrawerState.open()
                    }
                  }
                ) {
                  Image(
                    painter = painterResource(id = R.drawable.ic_filter_list),
                    contentDescription = ""
                  )
                }
              }

            }
          }
        }
      }
    }

    SelectVersionDialog(
      dialogState = isShowSelectVersionDialog,
      onDialogStateChange = { isShowSelectVersionDialog = it },
      onDismissRequest = { isShowSelectVersionDialog = false },
      moveVersionList = MoveVersion.values().toList(),
      onVersionItemClick = {
        ConfigMMKV.defaultVersion = it.id
        moveVersion = it
        isShowSelectVersionDialog = false
      })
  }
}


