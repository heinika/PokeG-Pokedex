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
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import com.heinika.pokeg.*
import com.heinika.pokeg.R
import com.heinika.pokeg.info.DexType
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.module.main.MainViewModel
import com.heinika.pokeg.module.moves.compose.ChipStatus
import com.heinika.pokeg.module.moves.compose.SelectTwoTypeClipList
import com.heinika.pokeg.module.mypokemon.compose.PokemonCard
import com.heinika.pokeg.ui.theme.*
import com.heinika.pokeg.utils.SystemBar
import kotlinx.coroutines.launch

enum class DrawerScreens(val nameStringId: Int, val screenName: String, val color: Color) {
  VersionsScreen(R.string.version_list, VERSION_LIST_SCREEN, RelaxedColor),
  TypesDetailScreen(R.string.type_detail, TYPE_DETAIL_SCREEN, MildColor),
  AbilitiesScreen(R.string.abilities_detail, ABILITIES_SCREEN, HardyColor),
  MovesScreen(R.string.move_list, MOVES_SCREEN, BraveColor),
  GamePropsScreen(R.string.props_list, GAME_PROPS_SCREEN, QuietColor),
  NaturesScreen(R.string.nature_list, NATURE_SCREEN, CalmColor),
}

@ExperimentalComposeUiApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun PokemonHomeScreen(mainViewModel: MainViewModel, onDrawerItemClick: (screenName: String) -> Unit, onPokemonItemClick: (Pokemon) -> Unit) {
  val sortedPokemonList = mainViewModel.pokemonSortListLiveData.observeAsState()
  val scaffoldState = rememberScaffoldState()
  val scope = rememberCoroutineScope()

  Scaffold(
    scaffoldState = scaffoldState,
    drawerContent = {
      LazyColumn(modifier = Modifier.padding(top = SystemBar.statusBarHeightDp.dp)) {
        items(DrawerScreens.values()) {
          Card(modifier = Modifier
            .padding(12.dp, 6.dp)
            .height(42.dp)
            .fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            backgroundColor = it.color,
            onClick = { onDrawerItemClick(it.screenName) }) {
            Box {
              Text(
                text = stringResource(id = it.nameStringId),
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body1
              )
            }
          }
        }
      }
    }) { paddingValues ->
    paddingValues.calculateTopPadding()

    val bottomDrawerState = rememberBottomDrawerState(initialValue = BottomDrawerValue.Closed)
    sortedPokemonList.value?.let { sortedPokemonList ->

      val types = remember { mutableStateOf(emptyList<Type>()) }
      BottomDrawer(
        drawerState = bottomDrawerState,
        gesturesEnabled = bottomDrawerState.isOpen,
        drawerContent = {
          MainBottomDrawer(types.value,
            onTypeSelectedChange = { typeList ->
              types.value = typeList
              mainViewModel.filterTypeList = typeList.map { it.typeId }
              mainViewModel.startSortAndFilter()
            })
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
              TopAppBar(
                modifier = Modifier.padding(top = SystemBar.statusBarHeightDp.dp, start = 12.dp, end = 12.dp),
                backgroundColor = Color.Transparent,
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
                        cursorColor = MaterialTheme.colors.primary
                      )
                    )

                    if (!isSearchMode) {
                      Row(modifier = Modifier
                        .fillMaxSize()
                        .padding(0.dp, 8.dp)
                        .clickable { }, verticalAlignment = Alignment.CenterVertically
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
              PokemonCard(pokemon = pokemon, onClick = { onPokemonItemClick(pokemon) })
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
}

@ExperimentalMaterialApi
@Composable
fun MainBottomDrawer(types: List<Type>, onTypeSelectedChange: (List<Type>) -> Unit) {
  val typeChipStatusList = remember {
    mutableStateListOf<ChipStatus>().apply {
      Type.values().dropLast(1).forEach {
        add(if (types.contains(it)) ChipStatus.Selected else ChipStatus.UnSelected)
      }
    }
  }
  SelectTwoTypeClipList(typeChipsStatus = typeChipStatusList, onSelectedChange = { onTypeSelectedChange(it) })
}
