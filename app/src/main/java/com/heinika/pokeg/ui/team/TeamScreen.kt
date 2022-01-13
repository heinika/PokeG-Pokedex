package com.heinika.pokeg.ui.team

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.ImagePainter.State.Empty.painter
import coil.compose.rememberImagePainter
import com.heinika.pokeg.R
import com.heinika.pokeg.repository.res.ResUtils
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.ui.theme.TeamBackgroundColor
import com.heinika.pokeg.utils.SystemBar
import com.heinika.pokeg.utils.getPokemonImageUrl
import kotlinx.coroutines.NonDisposableHandle.parent

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun TeamScreen() {
  val idList = listOf(151, 1, 150, 9, 250, 149)
  var selectedIndex by remember { mutableStateOf(0) }
  PokeGTheme {
    Surface {
      Column(
        Modifier
          .fillMaxSize()
          .background(color = TeamBackgroundColor)
      ) {
        Spacer(modifier = Modifier.height(Dp(SystemBar.statusBarHeightDp)))
        Text(text = "Team1", Modifier.padding(start = 12.dp))
        Divider(
          Modifier
            .padding(12.dp, 4.dp)
            .height(2.dp)
        )
        Row {
          Image(
            painter = rememberImagePainter(data = getPokemonImageUrl(idList[selectedIndex])),
            contentDescription = "",
            modifier = Modifier
              .size(180.dp)
              .padding(12.dp)
          )
        }
        TeamRow(modifier = Modifier.padding(6.dp), idList,
          initSelectedIndex = selectedIndex,
          onSelectChange = {
            selectedIndex = it
          })
      }
    }
  }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Preview
@Composable
fun TeamNumberCardPreview() {
  TeamRow(modifier = Modifier.padding(6.dp), listOf(151, 1, 150, 9, 250, 149), onSelectChange = {})
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun TeamRow(
  modifier: Modifier = Modifier, idList: List<Int>,
  initSelectedIndex: Int = 0, onSelectChange: (index: Int) -> Unit
) {
  var selectedIndex by remember { mutableStateOf(initSelectedIndex) }
  Row(modifier) {
    for (index in 0..5) {
      if (index < idList.size) {
        TeamNumberCard(
          id = idList[index],
          Modifier
            .weight(1f)
            .padding(3.dp),
          isSelected = selectedIndex == index,
          onClick = {
            selectedIndex = index
            onSelectChange(index)
          }
        )
      } else {
        Spacer(
          modifier = Modifier
            .weight(1f)
            .padding(3.dp)
        )
      }
    }

  }
}

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
private fun TeamNumberCard(
  id: Int,
  modifier: Modifier = Modifier,
  isSelected: Boolean = false,
  onClick: () -> Unit
) {
  Box(
    modifier = modifier.height(140.dp)
  ) {
    Card(modifier = Modifier
      .fillMaxWidth(0.95f)
      .align(Alignment.TopEnd)
      .padding(top = 2.dp),
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
          .fillMaxHeight(0.12f)
          .align(Alignment.BottomCenter)
          .clip(RoundedCornerShape(4.dp))
          .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = ResUtils.getNameById(id, "", LocalContext.current), color = Color.Black,
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
      painter = rememberImagePainter(data = getPokemonImageUrl(id, "")),
      contentDescription = "",
      modifier = Modifier
        .align(Alignment.TopStart)
        .fillMaxWidth(0.98f)
    )

    AnimatedVisibility(visible = !isSelected, enter = fadeIn(), exit = fadeOut()) {
      Canvas(modifier = Modifier.fillMaxSize()) {
        drawRect(TeamBackgroundColor, alpha = 0.5f)
      }
    }
  }
}
