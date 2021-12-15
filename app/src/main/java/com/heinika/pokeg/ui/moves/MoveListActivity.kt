package com.heinika.pokeg.ui.moves

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.heinika.pokeg.R
import com.heinika.pokeg.model.Move
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.ui.theme.grassColor
import com.heinika.pokeg.utils.SystemBar
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MoveListActivity : ComponentActivity() {
  private val moveListViewModel: MoveListViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

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
            val moves = remember { moveListViewModel.allMovesState }
            LazyColumn {
              itemsIndexed(moves.value) { index, move ->
                if (index == 0) {
                  Spacer(modifier = Modifier.padding(top = Dp(SystemBar.statusBarHeight.toFloat())))
                  MoveCard(move)
                } else {
                  MoveCard(move)
                }
              }
            }
          }
        }
      }
    }
  }
}

@ExperimentalMaterialApi
@Composable
fun MoveCard(move: Move) {
  Card(
    onClick = { /*TODO*/ }, modifier = Modifier
      .padding(12.dp)
      .fillMaxWidth()
      .height(90.dp)
  ) {
    val context = LocalContext.current

    ConstraintLayout {
      // Create references for the composables to constrain
      val (nameLabel, formatIdLabel, typeLabel, damageClassLabel,
        powerLabel, accuracyLabel, ppLabel,generationLabel) = createRefs()

      // Assign reference "text" to the Text composable
      // and constrain it to the bottom of the Button composable
      Text(
        move.getName(context),
        style = MaterialTheme.typography.h6,
        modifier = Modifier.constrainAs(nameLabel) {
          start.linkTo(parent.start, 16.dp)
          top.linkTo(parent.top, 16.dp)
        })

      Text(
        text = move.formatId,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(formatIdLabel) {
          top.linkTo(nameLabel.top, 0.dp)
          bottom.linkTo(nameLabel.bottom, 0.dp)
          end.linkTo(parent.end, 16.dp)
        })

      TypeCard(
        Modifier.constrainAs(typeLabel) {
          top.linkTo(nameLabel.top, 0.dp)
          bottom.linkTo(nameLabel.bottom, 0.dp)
          end.linkTo(damageClassLabel.start, 8.dp)
        },
        typeName = move.getTypeName(context),
        color = move.getTypeColor(context)
      )

      DamageClassImage(damageClassId = move.damageClassId, Modifier.constrainAs(damageClassLabel) {
        top.linkTo(nameLabel.top, 0.dp)
        bottom.linkTo(nameLabel.bottom, 0.dp)
        end.linkTo(formatIdLabel.start, 8.dp)
      })

      Text(
        text = "威力:${move.power}",
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(powerLabel) {
          top.linkTo(nameLabel.bottom)
          bottom.linkTo(parent.bottom)
          start.linkTo(nameLabel.start)
        })

      Text(
        text = "命中率:${move.accuracy}",
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(accuracyLabel) {
          top.linkTo(nameLabel.bottom)
          bottom.linkTo(parent.bottom)
          start.linkTo(powerLabel.end)
          end.linkTo(ppLabel.start)
        })

      Text(
        text = "PP:${move.pp}",
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(ppLabel) {
          top.linkTo(nameLabel.bottom)
          bottom.linkTo(parent.bottom)
          start.linkTo(accuracyLabel.end)
          end.linkTo(generationLabel.start)
        })

      Text(
        text = "世代:${move.generation_id}",
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(generationLabel) {
          top.linkTo(nameLabel.bottom)
          bottom.linkTo(parent.bottom)
          end.linkTo(parent.end, 16.dp)
        })
    }
  }
}

@Composable
fun TypeCard(modifier: Modifier = Modifier, typeName: String = "草", color: Color = grassColor) {
  Box(
    modifier
      .width(56.dp)
      .height(20.dp)
      .clip(RoundedCornerShape(12.dp))
      .background(color)
  ) {
    Text(
      text = typeName,
      Modifier.align(Alignment.Center),
      color = Color.White,
      style = MaterialTheme.typography.body2
    )
  }
}

@Composable
fun DamageClassImage(damageClassId: Int, modifier: Modifier = Modifier) {
  val painter = when (damageClassId) {
    2 -> painterResource(id = R.drawable.physical)
    3 -> painterResource(id = R.drawable.special)
    1 -> painterResource(id = R.drawable.status)
    else -> painterResource(id = R.drawable.physical)
  }
  Image(
    painter = painter, contentDescription = "",
    modifier
      .height(20.dp)
      .clip(RoundedCornerShape(12.dp)), contentScale = ContentScale.Crop
  )
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  PokeGTheme {
    MoveCard(
      Move(
        accuracy = "40",
        contest_effect_id = "",
        contestTypeId = "",
        damageClassId = 1,
        effect_chance = "",
        effect_id = 1,
        generation_id = 1,
        id = 1,
        identifier = "撞击",
        power = "40",
        pp = "",
        priority = 1,
        super_contest_effect_id = "",
        target_id = 0,
        typeId = 1
      )
    )
  }
}