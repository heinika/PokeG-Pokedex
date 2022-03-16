package com.heinika.pokeg.module.mypokemon.compose


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import com.heinika.pokeg.R
import com.heinika.pokeg.module.mypokemon.MyPokemonViewModel
import com.heinika.pokeg.ui.theme.BlackBackgroundColor
import com.heinika.pokeg.utils.SystemBar
import com.heinika.pokeg.utils.getPokemonImageUrl
import com.heinika.pokeg.utils.rememberFlavorPainter


@ExperimentalFoundationApi
@Composable
@ExperimentalCoilApi
@ExperimentalMaterialApi
fun MyPokemonScreen(viewModel: MyPokemonViewModel, navController: NavHostController) {
  val myPokemonList by viewModel.myPokemonList
  var isShowDialog by remember { mutableStateOf(false) }
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
              isShowDialog = true
            }
          )
        })
    }
  ) {
    LazyVerticalGrid(cells = GridCells.Adaptive(90.dp)) {
      items(myPokemonList) {
        MyPokemonCard(it.id, it.name, Modifier) {
          navController.navigate("MyPokemonDetailPage/${it.name}")
        }
      }
    }

    AddMyPokemonDialog(dialogState = isShowDialog, onDismissRequest = { isShowDialog = false }) {
      navController.navigate("MyPokemonDetailPage/newRandom${it.globalId}")
    }
  }
}

@ExperimentalFoundationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Preview
@Composable
private fun TeamNumberCardPreview() {

  LazyVerticalGrid(cells = GridCells.Adaptive(90.dp)) {
    items(600) {
      MyPokemonCard(it + 1, "?", Modifier) {}
    }
  }
}


@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
private fun MyPokemonCard(
  id: Int,
  name: String,
  modifier: Modifier = Modifier,
  onClick: () -> Unit
) {
  Box(
    modifier = modifier.size(90.dp, 120.dp)
  ) {
    Card(modifier = Modifier
      .fillMaxWidth(0.95f)
      .align(Alignment.TopEnd)
      .padding(6.dp),
      shape = RoundedCornerShape(4.dp),
      onClick = { onClick() }) {
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
          .fillMaxHeight(0.15f)
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

    Image(
      painter = rememberFlavorPainter(data = getPokemonImageUrl(id, "")),
      contentDescription = "",
      modifier = Modifier
        .align(Alignment.TopStart)
        .fillMaxWidth(0.98f)
    )
  }
}