package com.heinika.pokeg.module.mypokemon


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
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
import com.heinika.pokeg.ui.theme.TeamTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
@Composable
fun TeamScreen(viewModel: MyPokemonViewModel) {
  TeamTheme {
    ProvideWindowInsets {
      val systemUiController = rememberSystemUiController()
      systemUiController.setSystemBarsColor(Color.Transparent)
      systemUiController.setStatusBarColor(Color.Transparent)
      val navController = rememberNavController()

      NavHost(
        navController = navController, startDestination = "MyPokemonHome"
      ) {
        composable("MyPokemonDetailPage/{pokemonName}") {
          val name = it.arguments?.getString("pokemonName")!!
          val context = LocalContext.current
          LaunchedEffect(true) {
            if (name.contains("newRandom")) {
              val id = name.replace("newRandom", "").toInt()
              viewModel.requestInitDetailPokemon(
                id,
                ResUtils.getNameById(id, context = context)
              )
            } else {
              viewModel.requestExistMyPokemon(name)
            }
          }
          MyPokemonDetailScreen(viewModel, navController)
        }

        composable("MyPokemonHome") {
          LaunchedEffect(true) {
            viewModel.refreshAllPokemonList(onFinish = {})
          }
          MyPokemonScreen(viewModel, navController)
        }
      }
    }
  }
}