package com.heinika.pokeg.module.team

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.heinika.pokeg.R
import com.heinika.pokeg.info.Move
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.model.MyPokemonInfo
import com.heinika.pokeg.module.gameprops.props.CarryProps
import com.heinika.pokeg.repository.res.ResUtils
import com.heinika.pokeg.ui.theme.BlackBackgroundColor
import com.heinika.pokeg.ui.theme.grassColor
import com.heinika.pokeg.ui.theme.waterColor
import com.heinika.pokeg.utils.SystemBar
import com.heinika.pokeg.utils.getPokemonImageUrl
import com.heinika.pokeg.utils.toTypeColor
import kotlin.random.Random

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun TeamScreen(teamViewModel: TeamViewModel) {
  val teamNumberMap by remember { teamViewModel.teamNumberMap }
  if (teamNumberMap.isNotEmpty()) {
    LazyColumn(
      Modifier.fillMaxSize()
    ) {
      item {
        Spacer(modifier = Modifier.height(Dp(SystemBar.statusBarHeightDp)))
      }

      items(teamNumberMap.entries.toList()) {
        TeamItemCard(it.key, it.value)
      }
    }
  }
}

@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
private fun TeamItemCard(teamName: String, teamList: List<MyPokemonInfo>) {
  var selectedIndex by remember { mutableStateOf(0) }
  Row(Modifier.padding(12.dp, 0.dp), verticalAlignment = Alignment.CenterVertically) {
    Text(text = teamName, Modifier.weight(1f), style = MaterialTheme.typography.h5)
    val context = LocalContext.current
    Image(painter = painterResource(id = R.drawable.baseline_edit_24), contentDescription = "", Modifier.clickable {
      Toast.makeText(context.applicationContext,"暂时只有展示，编辑功能开发中...信息错误请忽略",Toast.LENGTH_SHORT).show()
    })
  }
  Divider(
    Modifier
      .padding(12.dp, 4.dp)
      .height(2.dp)
  )

  TeamNumberDetail(
    Modifier
      .fillMaxWidth()
      .height(180.dp), teamList[selectedIndex]
  )

  TeamRow(modifier = Modifier
    .height(110.dp)
    .padding(6.dp), teamList.map { it.id },
    initSelectedIndex = selectedIndex,
    onSelectChange = {
      selectedIndex = it
    })
}

@ExperimentalCoilApi
@Composable
fun TeamNumberDetail(modifier: Modifier = Modifier, teamNumberInfo: MyPokemonInfo) {
  ConstraintLayout(modifier) {
    val (image, nameLabel, typeRow, carryTag, natureTag, abilityTag, moveRow) = createRefs()
    Image(
      painter = rememberImagePainter(getPokemonImageUrl(teamNumberInfo.id)),
      contentDescription = "",
      modifier = Modifier
        .constrainAs(image) {
          top.linkTo(parent.top)
          start.linkTo(parent.start)
          bottom.linkTo(parent.bottom)
        }
        .fillMaxWidth(0.45f)
        .padding(12.dp)
    )

    Text(text = ResUtils.getNameById(teamNumberInfo.id, context = LocalContext.current),
      style = MaterialTheme.typography.h4, modifier = Modifier.constrainAs(nameLabel) {
        top.linkTo(parent.top)
        start.linkTo(image.end)
      })

    TypeColumn(
      Modifier
        .constrainAs(typeRow) {
          top.linkTo(nameLabel.top)
          bottom.linkTo(nameLabel.bottom)
          start.linkTo(nameLabel.end, 4.dp)
        }, teamNumberInfo.typeIdList
    )

    TagCard(
      modifier = Modifier.constrainAs(natureTag) {
        top.linkTo(nameLabel.bottom, 4.dp)
        start.linkTo(nameLabel.start)
      },
      typeName = stringResource(id = teamNumberInfo.nature.stringId),
      color = teamNumberInfo.nature.color
    )

    val randomColor = Color(Random.nextInt(0,255),Random.nextInt(0,255),Random.nextInt(0,255),255)
    TagCard(
      modifier = Modifier.constrainAs(abilityTag) {
        top.linkTo(nameLabel.bottom, 4.dp)
        start.linkTo(natureTag.end, 4.dp)
      },
      typeName = teamNumberInfo.ability.cname,
      color = randomColor
    )

    CarryCard(
      modifier = Modifier.constrainAs(carryTag) {
        top.linkTo(natureTag.bottom, 4.dp)
        start.linkTo(nameLabel.start)
      },
      teamNumberInfo.carry
    )

    FlowRow(
      Modifier
        .fillMaxWidth(0.55f)
        .constrainAs(moveRow) {
          top.linkTo(carryTag.bottom)
          start.linkTo(nameLabel.start)
        }) {
      for (move in teamNumberInfo.moveList) {
        MoveCard(Modifier.padding(top = 4.dp, end = 4.dp), move = move)
      }
    }
  }
}

@Composable
private fun TypeColumn(modifier: Modifier = Modifier, typeList: List<Type>) {
  Column(modifier, Arrangement.Center) {
    typeList.forEach {
      TypeCard(color = it.typeId.toTypeColor, typeName = it.getName(LocalContext.current))
      Spacer(modifier = Modifier.height(4.dp))
    }
  }
}

@Composable
fun CarryCard(modifier: Modifier = Modifier, carry: CarryProps?, color: Color = waterColor) {
  Row(
    modifier
      .height(30.dp)
      .clip(RoundedCornerShape(12.dp))
      .background(color.copy(alpha = 0.2f)),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Image(painter = painterResource(carry?.imageResId ?: R.drawable.ic_search), "", modifier = Modifier.size(32.dp))
    Text(
      text = stringResource(carry?.nameResId ?: R.string.type_unknown),
      color = Color.White,
      style = MaterialTheme.typography.body2,
      modifier = Modifier.padding(end = 12.dp)
    )
  }
}

@Composable
fun TypeCard(modifier: Modifier = Modifier, typeName: String = "草", color: Color = grassColor) {
  Box(
    modifier
      .width(56.dp)
      .height(20.dp)
      .clip(RoundedCornerShape(12.dp))
      .background(color.copy(alpha = 0.2f))
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
fun TagCard(modifier: Modifier = Modifier, typeName: String = "慢吞吞", color: Color) {
  Box(
    modifier
      .width(66.dp)
      .height(25.dp)
      .clip(RoundedCornerShape(12.dp))
      .background(color.copy(alpha = 0.6f))
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
fun MoveCard(modifier: Modifier = Modifier, move: Move) {
  Box(
    modifier
      .width(90.dp)
      .height(30.dp)
      .clip(RoundedCornerShape(12.dp))
      .background(move.typeColor.copy(alpha = 0.2f))
  ) {
    Text(
      text = "${move.getName(LocalContext.current)} ${move.power}",
      Modifier.align(Alignment.Center),
      color = Color.White,
      style = MaterialTheme.typography.body2
    )
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
    modifier = modifier.fillMaxHeight()
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
        drawRect(BlackBackgroundColor, alpha = 0.5f)
      }
    }
  }
}
