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
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.*
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.heinika.pokeg.info.Ability
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.module.ability.AbilitiesColumn
import com.heinika.pokeg.module.ability.AbilityScreen
import com.heinika.pokeg.module.ability.AbilityViewModel
import com.heinika.pokeg.module.detail.DetailViewModel
import com.heinika.pokeg.module.detailcompose.PokemonDetailScreen
import com.heinika.pokeg.module.typedetail.TypeDetailScreenViewModel
import com.heinika.pokeg.module.typedetail.compose.TypeDetailScreen
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.SystemBar
import dagger.hilt.android.AndroidEntryPoint

const val KEY_POKEMON_ID = "pokemonId"
const val KEY_ABILITY_ID = "abilityId"

const val START_SCREEN = "START_SCREEN"

const val ABILITIES_SCREEN = "AbilitiesScreen"
const val ABILITY_SCREEN = "AbilityScreen"
const val TYPE_DETAIL_SCREEN = "TypeDetailScreen"
const val POKEMON_DETAIL_SCREEN = "PokemonDetailScreen"

@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {
  private val abilityViewModel: AbilityViewModel by viewModels()
  private val detailViewModel: DetailViewModel by viewModels()
  private val typeDetailScreenViewModel: TypeDetailScreenViewModel by viewModels()

  @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    SystemBar.initStatusBarHeight(this)
    val startScreen = intent.getStringExtra(START_SCREEN) ?: ABILITIES_SCREEN
    setContent {
      PokeGTheme {
        val navController = rememberAnimatedNavController()
        AnimatedNavHost(navController = navController, startDestination = startScreen, builder = {
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
              Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                navBackStackEntry.arguments?.let { bundle ->
                  PokemonDetailScreen(
                    globalId = bundle.getInt(KEY_POKEMON_ID),
                    detailViewModel = detailViewModel,
                    onPokemonItemClick = {},
                    onBack = { navController.popBackStack() },
                    onAbilityClick = { ability -> navController.navigate("AbilityScreen/${ability.id}") },
                    onTypeClick = { type ->
                      typeDetailScreenViewModel.curTypes = listOf(type)
                      navController.navigate(TYPE_DETAIL_SCREEN)
                    }
                  )
                }
              }
            }
          )

          //TypeDetailScreen
          animatedComposable(
            route = TYPE_DETAIL_SCREEN,
            content = {
              Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                val typeList = remember { mutableStateListOf<Type>().apply { addAll(typeDetailScreenViewModel.curTypes) } }
                TypeDetailScreen(
                  types = typeList.toList(),
                  onTypesChange = {
                    typeList.clear()
                    typeList.addAll(it)
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