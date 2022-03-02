package com.heinika.pokeg.module.mypokemon.compose

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.heinika.pokeg.R
import com.heinika.pokeg.module.moves.compose.DamageClassImage
import com.heinika.pokeg.module.mypokemon.MyPokemonViewModel
import com.heinika.pokeg.ui.theme.*
import com.heinika.pokeg.utils.SystemBar
import com.heinika.pokeg.utils.getPokemonImageUrl

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun MyPokemonDetailScreen(viewModel: MyPokemonViewModel, navController: NavHostController) {
  val context = LocalContext.current
  if (viewModel.myDetailPokemon.value == null){
    Box(modifier = Modifier
      .fillMaxSize()
      .background(BlackBackgroundColor))
  }
  viewModel.myDetailPokemon.value?.let { myPokemonInfo ->
    val scrollState = rememberScrollState()
    Column(
      Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)
        .background(Brush.horizontalGradient(listOf(RedLinearEndColor, RedLinearStartColor)))
    ) {
      TopAppBar(modifier = Modifier.padding(
        top = Dp(SystemBar.statusBarHeightDp),
        start = 8.dp,
        end = 8.dp
      ),
        backgroundColor = Color.Transparent,
        contentColor = Color.White,
        title = { Text(text = myPokemonInfo.name) },
        navigationIcon = {
          Image(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.White),
            modifier = Modifier
              .padding(start = 8.dp)
              .clickable {
                navController.popBackStack()
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
                Toast
                  .makeText(context, "以保存，编辑功能正在开发中...", Toast.LENGTH_SHORT)
                  .show()
                viewModel.saveMyPokemonToDataBase()
              }
          )
        })

      Box(modifier = Modifier.fillMaxSize()) {
        Column(
          modifier = Modifier
            .padding(top = 165.dp)
            .fillMaxSize()
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(
              Brush.verticalGradient(
                listOf(
                  DarkRedLinearStartColor, DarkRedLinearEndColor
                )
              )
            )
        ) {
          Row(
            Modifier
              .fillMaxWidth()
              .padding(top = 60.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Box(
              Modifier
                .padding(start = 12.dp, end = 8.dp)
                .size(41.dp)
                .background(color = YellowLight, shape = CircleShape),
              contentAlignment = Alignment.Center
            ) {
              Text(
                text = myPokemonInfo.formatId,
                textAlign = TextAlign.Center,
                color = Color.Black
              )
            }
            Text(
              text = stringResource(myPokemonInfo.gen.resId),
              textAlign = TextAlign.Start,
              color = Color.White,
              modifier = Modifier.weight(1f)
            )

            myPokemonInfo.typeIdList.forEachIndexed { index, type ->
              TypeCard(
                modifier = Modifier.padding(end = if (index == 0) 8.dp else 12.dp),
                stringResource(id = type.typeNameResId),
                colorResource(id = type.typeColorResId)
              )
            }
          }

          Card(
            modifier = Modifier
              .fillMaxWidth()
              .padding(start = 12.dp, end = 12.dp, top = 15.dp),
            backgroundColor = Color.White
          ) {
            Row(
              modifier = Modifier.padding(15.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text(text = stringResource(myPokemonInfo.nature.stringId), style = MaterialTheme.typography.h6, modifier = Modifier.weight(1f))
              Text(text = stringResource(myPokemonInfo.nature.growEasyStringId))
              Image(
                painter = painterResource(id = R.drawable.up), "",
                Modifier
                  .padding(8.dp)
                  .size(18.dp)
              )
              Text(text = stringResource(myPokemonInfo.nature.growHardStringId))
              Image(
                painter = painterResource(id = R.drawable.up),
                colorFilter = ColorFilter.tint(
                  Red200
                ),
                contentDescription = "",
                modifier = Modifier
                  .graphicsLayer {
                    rotationZ = 180f
                    rotationY = 180f
                  }
                  .padding(start = 8.dp)
                  .size(18.dp)
              )
            }
          }

          Card(
            modifier = Modifier
              .fillMaxWidth()
              .padding(start = 12.dp, end = 12.dp, top = 15.dp),
            backgroundColor = Color.White
          ) {
            Column(modifier = Modifier.padding(15.dp, 10.dp)) {
              Text(text = stringResource(myPokemonInfo.ability.nameResId), style = MaterialTheme.typography.h6)
              Text(
                text = stringResource(myPokemonInfo.ability.flavorResId),
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(top = 8.dp)
              )
            }
          }

          Card(
            modifier = Modifier
              .fillMaxWidth()
              .padding(start = 12.dp, end = 12.dp, top = 15.dp),
            backgroundColor = Color.White
          ) {
            Column(modifier = Modifier.padding(15.dp, 10.dp)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                  painter = painterResource(id = myPokemonInfo.carry.imageResId),
                  contentDescription = "",
                  Modifier.size(32.dp)
                )
                Text(text = stringResource(myPokemonInfo.carry.nameResId), style = MaterialTheme.typography.h6)
              }

              Text(
                text = stringResource(myPokemonInfo.carry.flavorResId),
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(8.dp)
              )
            }
          }

          Column(Modifier.padding(bottom = 15.dp)) {
            myPokemonInfo.moveList.forEach {
              MoveCard(move = it, onClick = {})
            }
          }
        }

        Image(
          painter = rememberImagePainter(getPokemonImageUrl(myPokemonInfo.id)),
          contentDescription = "",
          Modifier
            .size(250.dp)
            .align(Alignment.TopCenter),
          contentScale = ContentScale.FillWidth
        )
      }
    }

  }
}

@ExperimentalMaterialApi
@Composable
fun MoveCard(move: com.heinika.pokeg.info.Move, onClick: () -> Unit) {
  Card(
    onClick = { onClick() }, modifier = Modifier
      .padding(start = 12.dp, end = 12.dp, top = 15.dp)
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

      com.heinika.pokeg.module.moves.compose.TypeCard(
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
          .padding(16.dp, 0.dp)
          .constrainAs(flavorLabel) {
            top.linkTo(powerLabel.bottom)
            bottom.linkTo(parent.bottom)
          }
      )

      Text(
        text = "威力:${move.power}",
        color = move.powerColor,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(powerLabel) {
          top.linkTo(nameLabel.bottom)
          bottom.linkTo(flavorLabel.top)
          start.linkTo(nameLabel.start)
        })

      Text(
        text = "命中率:${move.accuracy}",
        color = move.accuracyColor,
        style = MaterialTheme.typography.subtitle1,
        modifier = Modifier.constrainAs(accuracyLabel) {
          top.linkTo(nameLabel.bottom)
          bottom.linkTo(flavorLabel.top)
          start.linkTo(powerLabel.end)
          end.linkTo(ppLabel.start)
        })

      Text(
        text = "PP:${move.pp}",
        color = move.ppColor,
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

@Composable
fun TypeCard(modifier: Modifier = Modifier, typeName: String = "草", color: Color = grassColor) {
  Box(
    modifier
      .width(76.dp)
      .height(25.dp)
      .clip(RoundedCornerShape(12.dp))
      .background(color)
  ) {
    Text(
      text = typeName,
      Modifier.align(Alignment.Center),
      color = Color.Black,
      style = MaterialTheme.typography.body2
    )
  }
}


@Preview
@Composable
fun MyPokemonDetailScreenPreview() {
  PokeGTheme {
//    MyPokemonDetailScreen()
  }
}