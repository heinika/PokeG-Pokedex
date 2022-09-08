package com.heinika.pokeg

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.*
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.heinika.pokeg.info.Ability
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.module.ability.AbilitiesColumn
import com.heinika.pokeg.module.ability.AbilityScreen
import com.heinika.pokeg.module.ability.AbilityViewModel
import com.heinika.pokeg.module.about.AboutScreen
import com.heinika.pokeg.module.detail.DetailViewModel
import com.heinika.pokeg.module.detail.PokemonDetailScreen
import com.heinika.pokeg.module.donation.DonationScreen
import com.heinika.pokeg.module.gameprops.GamePropsScreen
import com.heinika.pokeg.module.home.PokemonHomeScreen
import com.heinika.pokeg.module.main.MainViewModel
import com.heinika.pokeg.module.moves.MoveListScreen
import com.heinika.pokeg.module.moves.MoveListViewModel
import com.heinika.pokeg.module.nature.NatureColumn
import com.heinika.pokeg.module.typedetail.TypeDetailScreenViewModel
import com.heinika.pokeg.module.typedetail.compose.TypeDetailScreen
import com.heinika.pokeg.module.versions.VersionsScreen
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.SystemBar
import dagger.hilt.android.AndroidEntryPoint

const val KEY_POKEMON_ID = "pokemonId"
const val KEY_ABILITY_ID = "abilityId"
const val KEY_TYPE_IDS = "typeIds"

const val START_SCREEN = "START_SCREEN"

const val POKEMON_HOME_SCREEN = "PokemonHomeScreen"
const val POKEMON_DETAIL_SCREEN = "PokemonDetailScreen"
const val ABILITIES_SCREEN = "AbilitiesScreen"
const val ABILITY_SCREEN = "AbilityScreen"
const val TYPE_DETAIL_SCREEN = "TypeDetailScreen"
const val MOVES_SCREEN = "MovesScreen"
const val VERSION_LIST_SCREEN = "VersionListScreen"
const val NATURE_SCREEN = "NatureScreen"
const val GAME_PROPS_SCREEN = "GamePropsScreen"
const val DONATION_SCREEN = "DonationScreen"
const val ABOUT_SCREEN = "AboutScreen"


@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {
  private val mainViewModel: MainViewModel by viewModels()
  private val abilityViewModel: AbilityViewModel by viewModels()
  private val detailViewModel: DetailViewModel by viewModels()
  private val typeDetailScreenViewModel: TypeDetailScreenViewModel by viewModels()
  private val moveListViewModel: MoveListViewModel by viewModels()

  @OptIn(
    ExperimentalAnimationApi::class,
    ExperimentalMaterialApi::class,
    ExperimentalCoilApi::class,
    ExperimentalPagerApi::class,
    ExperimentalComposeUiApi::class
  )
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    SystemBar.initStatusBarHeight(this)
    val startScreen = intent.getStringExtra(START_SCREEN) ?: POKEMON_HOME_SCREEN
    setContent {
      PokeGTheme {
        val navController = rememberAnimatedNavController()
        AnimatedNavHost(navController = navController, startDestination = startScreen, builder = {
          //HomeScreen
          animatedComposable(
            route = POKEMON_HOME_SCREEN,
            content = {
              PokemonHomeScreen(
                mainViewModel,
                onDrawerItemClick = { navController.navigate(it) },
                onPokemonItemClick = { navController.navigate("$POKEMON_DETAIL_SCREEN/${it.globalId}") })
            }
          )

          //VersionListScreen
          animatedComposable(
            route = VERSION_LIST_SCREEN,
            content = {
              VersionsScreen()
            }
          )

          //NatureScreen
          animatedComposable(
            route = NATURE_SCREEN,
            content = {
              Surface {
                NatureColumn()
              }
            }
          )

          //AboutScreen
          animatedComposable(
            route = ABOUT_SCREEN,
            content = {
              Surface {
                AboutScreen(onBack = {
                  navController.popBackStack()
                })
              }
            }
          )

          //DonationScreen
          animatedComposable(
            route = DONATION_SCREEN,
            content = {
              Surface {
                DonationScreen(onBack = {
                  navController.popBackStack()
                })
              }
            }
          )

          //VersionListScreen
          animatedComposable(
            route = GAME_PROPS_SCREEN,
            content = {
              GamePropsScreen()
            }
          )

          //MovesScreen
          animatedComposable(
            route = MOVES_SCREEN,
            content = {
              MoveListScreen(moveListViewModel)
            }
          )

          //AbilitiesScreen
          animatedComposable(
            route = ABILITIES_SCREEN,
            content = {
              AbilitiesColumn(onItemClick = { abilityId ->
                navController.navigate("$ABILITY_SCREEN/${abilityId}")
              })
            }
          )

          //AbilityScreen
          animatedComposable(
            route = "$ABILITY_SCREEN/{$KEY_ABILITY_ID}",
            arguments = listOf(navArgument(KEY_ABILITY_ID) { type = NavType.IntType }),
            content = { navBackStackEntry ->
              navBackStackEntry.arguments?.let { bundle ->
                val ability = Ability.values()[bundle.getInt(KEY_ABILITY_ID) - 1]
                Surface(modifier = Modifier.fillMaxSize()) {
                  AbilityScreen(
                    ability,
                    onBack = { navController.popBackStack() },
                    onPokemonClick = { navController.navigate("$POKEMON_DETAIL_SCREEN/${it}") },
                    abilityViewModel
                  )
                }
              }
            }
          )

          //PokemonDetailScreen
          animatedComposable(
            route = "${POKEMON_DETAIL_SCREEN}/{$KEY_POKEMON_ID}",
            arguments = listOf(navArgument(KEY_POKEMON_ID) { type = NavType.IntType }),
            content = { navBackStackEntry ->
              Surface(modifier = Modifier.fillMaxSize()) {
                navBackStackEntry.arguments?.let { bundle ->
                  PokemonDetailScreen(
                    globalId = bundle.getInt(KEY_POKEMON_ID),
                    detailViewModel = detailViewModel,
                    onPokemonItemClick = { navController.navigate("$POKEMON_DETAIL_SCREEN/${it.globalId}") },
                    onBack = { navController.popBackStack() },
                    onAbilityClick = { ability -> navController.navigate("AbilityScreen/${ability.id}") },
                    onTypeClick = { type ->
                      navController.navigate("$TYPE_DETAIL_SCREEN/${type.typeId}")
                    }
                  )
                }
              }
            }
          )

          //TypeDetailScreen
          animatedComposable(
            route = "$TYPE_DETAIL_SCREEN/{$KEY_TYPE_IDS}",
            arguments = listOf(navArgument(KEY_TYPE_IDS) { type = NavType.StringType }),
            content = {
              Surface(modifier = Modifier.fillMaxSize()) {
                it.arguments?.getString(KEY_TYPE_IDS)?.let { ids ->
                  val typeList = remember { mutableStateOf(ids.split(",").toList().map { Type.values()[it.toInt() - 1] }) }
                  TypeDetailScreen(
                    types = typeList.value.toList(),
                    onTypesChange = {
                      typeList.value = it
                    },
                    onPokemonClick = { navController.navigate("$POKEMON_DETAIL_SCREEN/${it}") })
                }
              }
            }
          )

          //TypeDetailScreen
          animatedComposable(
            route = TYPE_DETAIL_SCREEN,
            content = {
              Surface(modifier = Modifier.fillMaxSize()) {
                val typeList = remember { mutableStateListOf<Type>().apply { addAll(typeDetailScreenViewModel.curTypes) } }
                TypeDetailScreen(
                  types = typeList.toList(),
                  onTypesChange = {
                    typeList.clear()
                    typeList.addAll(it)
                    typeDetailScreenViewModel.curTypes = it
                  },
                  onPokemonClick = { navController.navigate("$POKEMON_DETAIL_SCREEN/${it}") })
              }
            }
          )

        })
      }
    }
  }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.animatedComposable(
  route: String,
  arguments: List<NamedNavArgument> = emptyList(),
  content: @Composable AnimatedVisibilityScope.(NavBackStackEntry) -> Unit
) {
  composable(
    route = route,
    arguments = arguments,
    enterTransition = {
      fadeIn()
    },
    exitTransition = {
      fadeOut()
    },
    content = content
  )
}