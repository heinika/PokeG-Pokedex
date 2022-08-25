package com.heinika.pokeg.module.ability

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.heinika.pokeg.info.Ability
import com.heinika.pokeg.module.detail.DetailViewModel
import com.heinika.pokeg.module.detailcompose.PokemonDetailScreen
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.SystemBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AbilitiesActivity : ComponentActivity() {
  private val abilityViewModel: AbilityViewModel by viewModels()
  private val detailViewModel: DetailViewModel by viewModels()

  @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    SystemBar.initStatusBarHeight(this)
    setContent {
      PokeGTheme {
        val navController = rememberAnimatedNavController()
        AnimatedNavHost(navController = navController, startDestination = "AbilitiesScreen", builder = {
          composable(
            route = "AbilitiesScreen",
            enterTransition = {
              fadeIn()
            },
            exitTransition = {
              fadeOut()
            },
            content = {
              AbilitiesColumn(onItemClick = { abilityId ->
                navController.navigate("AbilityScreen/${abilityId}")
              })
            }
          )

          //AbilityScreen
          composable(
            route = "AbilityScreen/{abilityId}",
            arguments = listOf(navArgument("abilityId") { type = NavType.IntType }),
            enterTransition = {
              fadeIn()
            },
            exitTransition = {
              fadeOut()
            },
            content = { navBackStackEntry ->
              navBackStackEntry.arguments?.let { bundle ->
                val ability = Ability.values()[bundle.getInt("abilityId") - 1]
                Surface(modifier = Modifier.fillMaxSize()) {
                  AbilityScreen(
                    ability,
                    onBack = { navController.popBackStack() },
                    onPokemonClick = { navController.navigate("PokemonDetailScreen/${it}") },
                    abilityViewModel
                  )
                }
              }
            }
          )

          //PokemonDetailScreen
          composable(route = "PokemonDetailScreen/{pokemonId}",
            arguments = listOf(navArgument("pokemonId") { type = NavType.IntType }),
            enterTransition = {
              fadeIn()
            }, exitTransition = {
              fadeOut()
            }, content = { navBackStackEntry ->
              Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                navBackStackEntry.arguments?.let { bundle ->
                  PokemonDetailScreen(
                    globalId = bundle.getInt("pokemonId"),
                    detailViewModel = detailViewModel,
                    onPokemonItemClick = {},
                    onBack = { navController.popBackStack() },
                    onAbilityClick = { ability -> navController.navigate("AbilityScreen/${ability.id}") }
                  )
                }
              }
            })
        })
      }
    }
  }
}