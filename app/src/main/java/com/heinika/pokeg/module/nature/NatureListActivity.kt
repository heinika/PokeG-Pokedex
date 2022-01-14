package com.heinika.pokeg.module.nature;

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.graphics.Color
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.heinika.pokeg.module.nature.compose.NatureColumn
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.SystemBar
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@ExperimentalAnimationApi
@AndroidEntryPoint
internal class NatureListActivity : ComponentActivity() {
  private val natureViewModel: NatureViewModel by viewModels()

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
          Surface(color = MaterialTheme.colors.background) {
            val natureList = natureViewModel.natureList
            NatureColumn(natureList = natureList.value)
          }
        }
      }
    }
  }
}