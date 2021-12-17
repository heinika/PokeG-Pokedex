package com.heinika.pokeg.ui.moves.compose

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.heinika.pokeg.R
import com.heinika.pokeg.model.Move
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.ui.theme.grassColor

@ExperimentalMaterialApi
@Composable
fun MoveCard(move: Move, onClick: () -> Unit) {
  Card(
    onClick = { onClick() }, modifier = Modifier
      .padding(12.dp)
      .fillMaxWidth()
      .height(130.dp)
  ) {
    val context = LocalContext.current

    ConstraintLayout {
      // Create references for the composables to constrain
      val (nameLabel, formatIdLabel, typeLabel, damageClassLabel,
        powerLabel, accuracyLabel, ppLabel, generationLabel, flavorLabel) = createRefs()

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
        text = move.flavorText,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier
          .fillMaxWidth()
          .padding(16.dp, 0.dp)
          .constrainAs(flavorLabel) {
            top.linkTo(powerLabel.bottom)
            bottom.linkTo(parent.bottom)
          }
      )

      val powerTextColor = move.power.toPowerColor()
      Text(
        text = "威力:${move.power}",
        color = powerTextColor,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(powerLabel) {
          top.linkTo(nameLabel.bottom)
          bottom.linkTo(flavorLabel.top)
          start.linkTo(nameLabel.start)
        })


      val accuracyTextColor = move.accuracy.toAccuracyColor()
      Text(
        text = "命中率:${move.accuracy}",
        color = accuracyTextColor,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(accuracyLabel) {
          top.linkTo(nameLabel.bottom)
          bottom.linkTo(flavorLabel.top)
          start.linkTo(powerLabel.end)
          end.linkTo(ppLabel.start)
        })

      val ppTextColor = move.pp.toPPColor()
      Text(
        text = "PP:${move.pp}",
        color = ppTextColor,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(ppLabel) {
          top.linkTo(nameLabel.bottom)
          bottom.linkTo(flavorLabel.top)
          start.linkTo(accuracyLabel.end)
          end.linkTo(generationLabel.start)
        })

      Text(
        text = "世代:${move.generationId}",
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(generationLabel) {
          top.linkTo(nameLabel.bottom)
          bottom.linkTo(flavorLabel.top)
          end.linkTo(parent.end, 16.dp)
        })
    }
  }
}

private fun String.toPowerColor(): Color {
  return try {
    when(this.toInt()){
      in 0..50 -> Color.Red
      in 51..100 -> Color.Yellow
      else -> Color.Green
    }
  }catch (e:Exception){
    Color.Gray
  }
}

private fun String.toAccuracyColor(): Color {
  return try {
    when(this.toInt()){
      100 -> Color.Green
      in 80..100 -> Color.Yellow
      else -> Color.Red
    }
  }catch (e:Exception){
    Color.Gray
  }
}

private fun String.toPPColor(): Color {
  return try {
    when(this.toInt()){
      in 0..5 -> Color.Red
      in 6..10 -> Color.Yellow
      else -> Color.Green
    }
  }catch (e:Exception){
    Color.Gray
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
        damageClassId = 1,
        generationId = 1,
        id = 1,
        eName = "撞击",
        power = "40",
        pp = "25",
        typeId = 1,
        flavorText = "对对手进行打击"
      ),
      onClick = {}
    )
  }
}