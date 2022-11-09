@file:Suppress("KotlinConstantConditions")

package com.heinika.pokeg.utils


import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.heinika.pokeg.BuildConfig

@ExperimentalCoilApi
@Composable
fun rememberFlavorPainter(data: String): Painter {
  return rememberImagePainter(data = data, builder = {
    crossfade(true)
    if (isGooglePlayFlavor()) {
      transformations(coil.transform.BlurTransformation(LocalContext.current, 7f, 3f))
    }
  }
  )
}

fun isGooglePlayFlavor() = BuildConfig.FLAVOR == "google"
