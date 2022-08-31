package com.heinika.pokeg.module.moves.compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.heinika.pokeg.R
import com.heinika.pokeg.info.Generation
import com.heinika.pokeg.info.Move
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.ui.theme.grassColor

@ExperimentalMaterialApi
@Composable
fun MoveCard(move: Move, level: Int? = null, onClick: () -> Unit) {
  val context = LocalContext.current
  Card(
    onClick = { onClick() }, modifier = Modifier
      .padding(12.dp, 6.dp)
      .fillMaxWidth()
      .height(130.dp),
    border = BorderStroke(2.dp,move.getDarkTypeColor())
  ) {

    ConstraintLayout {
      val (nameLabel, formatIdLabel, typeLabel, damageClassLabel,
        powerLabel, accuracyLabel, ppLabel, generationLabel, flavorLabel) = createRefs()

      val name = if (level == null || level == 0) move.getName(context) else "${move.getName(context)} ${stringResource(id = R.string.level)}:$level"
      Text(
        name,
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
        text = stringResource(id = move.flavorResId),
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp, 0.dp, 16.dp, 12.dp)
          .constrainAs(flavorLabel) {
            top.linkTo(powerLabel.bottom)
            bottom.linkTo(parent.bottom)
          }
      )

      val powerTextColor = move.powerColor
      Text(
        text = stringResource(R.string.power) + ":${move.power}",
        color = powerTextColor,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(powerLabel) {
          top.linkTo(nameLabel.bottom)
          bottom.linkTo(flavorLabel.top)
          start.linkTo(nameLabel.start)
        })


      val accuracy = if (move.accuracy != 0) move.accuracy.toString() else "--"
      Text(
        text = stringResource(R.string.accuracy) + ":$accuracy",
        color = move.accuracyColor,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(accuracyLabel) {
          top.linkTo(nameLabel.bottom)
          bottom.linkTo(flavorLabel.top)
          start.linkTo(powerLabel.end)
          end.linkTo(ppLabel.start)
        })


      Text(
        text = stringResource(R.string.pp) + ":${move.pp}",
        color = move.ppColor,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(ppLabel) {
          top.linkTo(nameLabel.bottom)
          bottom.linkTo(flavorLabel.top)
          start.linkTo(accuracyLabel.end)
          end.linkTo(generationLabel.start)
        })

      Text(
        text = Generation.values()[move.generationId - 1].filterString,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(generationLabel) {
          top.linkTo(nameLabel.bottom)
          bottom.linkTo(flavorLabel.top)
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
      Move.Move1,
      level = 1,
      onClick = {}
    )
  }
}