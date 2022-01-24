package com.heinika.pokeg.module.mypokemon

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.heinika.pokeg.module.mypokemon.compose.MyPokemonDetailScreen
import com.heinika.pokeg.module.mypokemon.compose.MyPokemonScreen
import com.heinika.pokeg.repository.res.ResUtils
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.SystemBar
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MyPokemonActivity : ComponentActivity() {
  private val viewModel: MyPokemonViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    SystemBar.initStatusBarHeight(this)

    setContent {
      PokeGTheme {
        ProvideWindowInsets {
          val systemUiController = rememberSystemUiController()
          systemUiController.setSystemBarsColor(Color.Transparent)
          systemUiController.setStatusBarColor(Color.Transparent)
          val navController = rememberNavController()

          NavHost(navController = navController, startDestination = "MyPokemonHome") {
            composable("MyPokemonHome") {
              LaunchedEffect(true){
                viewModel.refreshAllPokemonList()
              }
              MyPokemonScreen(viewModel,navController)
            }
            composable("MyPokemonDetailPage") {
              val context = LocalContext.current
              LaunchedEffect(true){
                val randomInt = Random.nextInt(0,890)
                viewModel.requestInitDetailPokemon(randomInt,ResUtils.getNameById(randomInt, context = context))
              }
              MyPokemonDetailScreen(viewModel,navController)
            }
          }
        }
      }
    }
  }
}