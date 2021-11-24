package com.heinika.pokeg.ui.moves

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.SystemBar

class MoveListActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent {
      PokeGTheme {
        ProvideWindowInsets {
          // A surface container using the 'background' color from the theme
          val systemUiController = rememberSystemUiController()
          systemUiController.setSystemBarsColor(MaterialTheme.colors.background)
          Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Greeting("Android")
          }
        }
      }
    }
  }
}

@Composable
fun Greeting(name: String) {
  Text(text = "撞击",Modifier.padding(top = Dp(SystemBar.statusBarHeight.toFloat()),start = 12.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  PokeGTheme {
    Greeting("Android")
  }
}