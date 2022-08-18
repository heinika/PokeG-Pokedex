package com.heinika.pokeg.module.typedetail

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.heinika.pokeg.module.typedetail.compose.TypeDetailScreen
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.SystemBar


@ExperimentalCoilApi
@ExperimentalMaterialApi
class TypeDetailActivity : ComponentActivity() {
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
          Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
          ) {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
              TypeDetailScreen()
            }
          }
        }
      }
    }
  }
}

