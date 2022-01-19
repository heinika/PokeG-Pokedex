package com.heinika.pokeg.module.mypokemon.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.imePadding
import com.heinika.pokeg.R
import com.heinika.pokeg.ui.theme.DarkRedLinearEndColor
import com.heinika.pokeg.ui.theme.DarkRedLinearStartColor
import com.heinika.pokeg.ui.theme.RedLinearEndColor
import com.heinika.pokeg.ui.theme.RedLinearStartColor
import com.heinika.pokeg.utils.SystemBar

@Composable
fun MyPokemonDetailScreen() {
  Column(
    Modifier
      .fillMaxSize()
      .background(Brush.horizontalGradient(listOf(RedLinearEndColor, RedLinearStartColor)))
  ) {
    TopAppBar(modifier = Modifier.padding(
      top = Dp(SystemBar.statusBarHeightDp),
      start = 8.dp,
      end = 8.dp
    ),
      backgroundColor = Color.Transparent,
      contentColor = Color.White,
      title = { Text(text = "My Pokemon") },
      navigationIcon = {
        Image(
          imageVector = Icons.Default.ArrowBack,
          contentDescription = "",
          colorFilter = ColorFilter.tint(Color.White),
          modifier = Modifier
            .padding(start = 8.dp)
            .clickable {

            }
        )
      },
      actions = {
        Image(
          imageVector = Icons.Default.Edit,
          contentDescription = "",
          colorFilter = ColorFilter.tint(Color.White),
          modifier = Modifier
            .padding(end = 8.dp)
            .clickable {

            }
        )
      })

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
      val (contentColumn,contentColumnBg,image) = createRefs()
      Box(
        modifier = Modifier
          .constrainAs(contentColumnBg) {
            top.linkTo(image.bottom)
            bottom.linkTo(parent.bottom)
          }
          .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
          .background(
            Brush.verticalGradient(
              listOf(
                DarkRedLinearStartColor, DarkRedLinearEndColor
              )
            )
          )
      )
      
      Column() {

      }

      Image(
        painter = painterResource(id = R.drawable.penbuolong),
        contentDescription = "",
        Modifier
          .constrainAs(image) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
          }
          .fillMaxWidth(0.8f),
        contentScale = ContentScale.FillWidth
      )
    }
  }
}


@Preview
@Composable
fun MyPokemonDetailScreenPreview() {
  MyPokemonDetailScreen()
}