package com.heinika.pokeg.module.team.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.heinika.pokeg.R
import com.heinika.pokeg.ui.theme.BlackBackgroundColor
import com.heinika.pokeg.utils.getPokemonImageUrl
import com.heinika.pokeg.utils.rememberFlavorPainter
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@ExperimentalCoilApi
@Composable
fun TeamNumberCard(
  id: Int,
  name: String,
  modifier: Modifier = Modifier,
  isSelected: Boolean = false,
  onClick: () -> Unit
) {
  Timber.i("id : $id")
  Box(
    modifier = modifier.fillMaxHeight()
  ) {
    Card(modifier = Modifier
      .fillMaxWidth(0.95f)
      .align(Alignment.TopEnd)
      .padding(top = 2.dp),
      shape = RoundedCornerShape(4.dp),
      onClick = { onClick() }) {
      Box {
        Image(
          painter = painterResource(id = R.drawable.team_card_bg),
          contentDescription = "",
          modifier = Modifier
            .fillMaxWidth(),
          contentScale = ContentScale.FillWidth
        )

        Row(
          Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.12f)
            .align(Alignment.BottomCenter)
            .clip(RoundedCornerShape(4.dp))
            .background(Color.White),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text(
            text = name, color = Color.Black,
            style = TextStyle(fontSize = 8.sp),
            modifier = Modifier
              .weight(1f)
              .padding(start = 2.dp),
            maxLines = 1
          )

          Image(
            painter = painterResource(id = R.drawable.golden_pokeball),
            contentDescription = "",
            Modifier
              .size(12.dp)
              .padding(end = 2.dp)
          )
        }
      }
    }

    val painter = if (id == -1) painterResource(id = R.drawable.golden_pokeball) else
      rememberFlavorPainter(getPokemonImageUrl(id, ""))

    Image(
      painter = painter,
      contentDescription = "",
      modifier = Modifier
        .align(Alignment.TopStart)
        .fillMaxWidth(0.98f)
    )

    AnimatedVisibility(visible = !isSelected, enter = fadeIn(), exit = fadeOut()) {
      Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(BlackBackgroundColor, alpha = 0.5f)
      }
    }
  }
}