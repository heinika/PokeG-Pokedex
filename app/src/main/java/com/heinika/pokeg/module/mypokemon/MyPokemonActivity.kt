package com.heinika.pokeg.module.mypokemon

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.Dp
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.heinika.pokeg.module.mypokemon.compose.MyPokemonScreen
import com.heinika.pokeg.ui.theme.BlackBackgroundColor
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.SystemBar

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
class MyPokemonActivity : ComponentActivity() {

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
          Scaffold(
            topBar = {
              TopAppBar(modifier = Modifier.padding(top = Dp(SystemBar.statusBarHeightDp)),
                backgroundColor = BlackBackgroundColor,
                contentColor = Color.White,
                title = { Text(text = "My Pokemon") },
                actions = {
                  Image(
                    imageVector = Icons.Default.Add,
                    contentDescription = "",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.clickable {

                    }
                  )
                })
            },
            backgroundColor = BlackBackgroundColor
          ) {
            MyPokemonScreen()
          }
        }
      }
    }
  }
}