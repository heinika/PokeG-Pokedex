package com.heinika.pokeg.module.typedetail

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
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.heinika.pokeg.info.Ability
import com.heinika.pokeg.module.ability.AbilityScreen
import com.heinika.pokeg.module.ability.AbilityViewModel
import com.heinika.pokeg.module.detail.DetailViewModel
import com.heinika.pokeg.module.detailcompose.PokemonDetailScreen
import com.heinika.pokeg.module.typedetail.compose.TypeDetailScreen
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.SystemBar
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class TypeDetailActivity : ComponentActivity() {
  private val typeDetailScreenViewModel: TypeDetailScreenViewModel by viewModels()
  private val detailViewModel: DetailViewModel by viewModels()
  private val abilityViewModel: AbilityViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    SystemBar.initStatusBarHeight(this)

    setContent {
      PokeGTheme {
        ProvideWindowInsets {
          // A surface container using the 'background' color from the theme
          val systemUiController = rememberSystemUiController()
          systemUiController.setSystemBarsColor(Color.Transparent)
          systemUiController.setStatusBarColor(Color.Transparent)

          val navController = rememberAnimatedNavController()
          AnimatedNavHost(navController, startDestination = "TypeDetailScreen") {
            //TypeDetailScreen
            composable(
              route = "TypeDetailScreen",
              enterTransition = {
                fadeIn()
              },
              exitTransition = {
                fadeOut()
              },
              content = {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                  TypeDetailScreen(typeDetailScreenViewModel, onPokemonClick = { navController.navigate("PokemonDetailScreen/${it}") })
                }
              })

            //PokemonDetailScreen
            composable(route = "PokemonDetailScreen/{pokemonId}",
              arguments = listOf(navArgument("pokemonId") { type = NavType.IntType }),
              enterTransition = {
                fadeIn()
              }, exitTransition = {
                fadeOut()
              }, content = { navBackStackEntry ->
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                  navBackStackEntry.arguments?.let {
                    PokemonDetailScreen(
                      globalId = it.getInt("pokemonId"),
                      detailViewModel = detailViewModel,
                      onPokemonItemClick = {},
                      onBack = { navController.popBackStack() },
                      onAbilityClick = { ability -> navController.navigate("AbilityScreen/${ability.id}") }
                    )
                  }
                }
              })

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

          }
        }
      }
    }
  }
}

