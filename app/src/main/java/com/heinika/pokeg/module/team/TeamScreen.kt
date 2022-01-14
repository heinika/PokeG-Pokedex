//package com.heinika.pokeg.module.team
//
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.animation.ExperimentalAnimationApi
//import androidx.compose.animation.fadeIn
//import androidx.compose.animation.fadeOut
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.constraintlayout.compose.ConstraintLayout
//import coil.annotation.ExperimentalCoilApi
//import coil.compose.rememberImagePainter
//import com.google.accompanist.flowlayout.FlowRow
//import com.heinika.pokeg.R
//import com.heinika.pokeg.model.Move
//import com.heinika.pokeg.model.Nature
//import com.heinika.pokeg.model.TeamNumberInfo
//import com.heinika.pokeg.repository.res.ResUtils
//import com.heinika.pokeg.module.gameprops.props.CarryProps
//import com.heinika.pokeg.module.moves.compose.MoveCard
//import com.heinika.pokeg.ui.theme.*
//
//import com.heinika.pokeg.utils.SystemBar
//import com.heinika.pokeg.utils.getPokemonImageUrl
//import com.heinika.pokeg.utils.toTypeColor
//
//@ExperimentalAnimationApi
//@ExperimentalMaterialApi
//@ExperimentalCoilApi
//@Composable
//fun TeamScreen(teamViewModel: TeamViewModel) {
//  val teamNumberList = listOf(TeamNumberInfo(), TeamNumberInfo(), TeamNumberInfo(), TeamNumberInfo(), TeamNumberInfo(), TeamNumberInfo())
//  var selectedIndex by remember { mutableStateOf(0) }
//  PokeGTheme {
//    Surface {
//      Column(
//        Modifier
//          .fillMaxSize()
//          .background(color = TeamBackgroundColor)
//      ) {
//        Spacer(modifier = Modifier.height(Dp(SystemBar.statusBarHeightDp)))
//        Text(text = "Team1", Modifier.padding(start = 12.dp))
//        Divider(
//          Modifier
//            .padding(12.dp, 4.dp)
//            .height(2.dp)
//        )
//
//        TeamNumberDetail(Modifier.fillMaxWidth(), teamNumberList[selectedIndex])
//
//        TeamRow(modifier = Modifier.padding(6.dp), teamNumberList.map { it.id },
//          initSelectedIndex = selectedIndex,
//          onSelectChange = {
//            selectedIndex = it
//          })
//      }
//    }
//  }
//}
//
//@Composable
//fun TeamNumberDetail(modifier: Modifier = Modifier, teamNumberInfo: TeamNumberInfo) {
//  ConstraintLayout(modifier) {
//    val (image, nameLabel, typeRow, carryTag,moveRow) = createRefs()
//    Image(
//      painter = painterResource(id = R.drawable.meotwo),
//      contentDescription = "",
//      modifier = Modifier
//        .constrainAs(image) {
//          top.linkTo(parent.top)
//          start.linkTo(parent.start)
//        }
//        .size(180.dp)
//        .padding(12.dp)
//    )
//
//    Text(text = ResUtils.getNameById(teamNumberInfo.id, context = LocalContext.current), style = MaterialTheme.typography.h4, modifier = Modifier.constrainAs(nameLabel) {
//      top.linkTo(parent.top)
//      start.linkTo(image.end)
//    })
//
//    CarryCard(
//      modifier = Modifier.constrainAs(carryTag) {
//        top.linkTo(nameLabel.top)
//        bottom.linkTo(nameLabel.bottom)
//        start.linkTo(nameLabel.end, 4.dp)
//      },
//      teamNumberInfo.carry
//    )
//
//    TypeRow(
//      Modifier
//        .constrainAs(typeRow) {
//          top.linkTo(nameLabel.bottom, 4.dp)
//          start.linkTo(nameLabel.start)
//        }
//        .fillMaxWidth(),teamNumberInfo.typeIdList,teamNumberInfo.nature)
//
//    FlowRow(Modifier
//      .constrainAs(moveRow) {
//        top.linkTo(typeRow.bottom)
//        start.linkTo(typeRow.start)
//      }) {
//      for (move in teamNumberInfo.moveIdList) {
//        MoveCard(move = move)
//      }
//    }
//  }
//}
//
//@Composable
//private fun TypeRow(modifier: Modifier = Modifier, typeList: List<Type>, nature: Nature) {
//  Row(modifier) {
//    typeList.forEach {
//      TypeCard(color = it.typeId.toTypeColor, typeName = it.getName(LocalContext.current))
//      Spacer(modifier = Modifier.width(4.dp))
//    }
//    NatureCard(typeName = nature.cname, color = fireColor)
//  }
//}
//
//@Composable
//fun CarryCard(modifier: Modifier = Modifier, carry: CarryProps, color: Color = waterColor) {
//  Row(
//    modifier
//      .height(30.dp)
//      .clip(RoundedCornerShape(12.dp))
//      .background(color.copy(alpha = 0.5f)),
//    verticalAlignment = Alignment.CenterVertically
//  ) {
//    Image(painter = painterResource(carry.imageResId), "")
//    Text(
//      text = stringResource(carry.nameResId),
//      color = Color.White,
//      style = MaterialTheme.typography.body2,
//      modifier = Modifier.padding(end = 12.dp)
//    )
//  }
//}
//
//@Composable
//fun TypeCard(modifier: Modifier = Modifier, typeName: String = "草", color: Color = grassColor) {
//  Box(
//    modifier
//      .width(56.dp)
//      .height(20.dp)
//      .clip(RoundedCornerShape(12.dp))
//      .background(color.copy(alpha = 0.5f))
//  ) {
//    Text(
//      text = typeName,
//      Modifier.align(Alignment.Center),
//      color = Color.White,
//      style = MaterialTheme.typography.body2
//    )
//  }
//}
//
//@Composable
//fun NatureCard(modifier: Modifier = Modifier, typeName: String = "草", color: Color = grassColor) {
//  Box(
//    modifier
//      .width(66.dp)
//      .height(20.dp)
//      .clip(RoundedCornerShape(12.dp))
//      .background(color.copy(alpha = 0.5f))
//  ) {
//    Text(
//      text = typeName,
//      Modifier.align(Alignment.Center),
//      color = Color.White,
//      style = MaterialTheme.typography.body2
//    )
//  }
//}
//
//@Composable
//fun MoveCard(modifier: Modifier = Modifier, move: Move) {
//  Box(
//    modifier
//      .width(80.dp)
//      .height(40.dp)
//      .clip(RoundedCornerShape(12.dp))
//      .background(move.typeColor.copy(alpha = 0.5f))
//  ) {
//    Text(
//      text = move.getName(LocalContext.current),
//      Modifier.align(Alignment.Center),
//      color = Color.White,
//      style = MaterialTheme.typography.body2
//    )
//  }
//}
//
//
//@ExperimentalAnimationApi
//@ExperimentalMaterialApi
//@ExperimentalCoilApi
//@Preview
//@Composable
//fun TeamNumberCardPreview() {
//  TeamRow(modifier = Modifier.padding(6.dp), listOf(151, 1, 150, 9, 250, 149), onSelectChange = {})
//}
//
//@ExperimentalAnimationApi
//@ExperimentalMaterialApi
//@ExperimentalCoilApi
//@Composable
//fun TeamRow(
//  modifier: Modifier = Modifier, idList: List<Int>,
//  initSelectedIndex: Int = 0, onSelectChange: (index: Int) -> Unit
//) {
//  var selectedIndex by remember { mutableStateOf(initSelectedIndex) }
//  Row(modifier) {
//    for (index in 0..5) {
//      if (index < idList.size) {
//        TeamNumberCard(
//          id = idList[index],
//          Modifier
//            .weight(1f)
//            .padding(3.dp),
//          isSelected = selectedIndex == index,
//          onClick = {
//            selectedIndex = index
//            onSelectChange(index)
//          }
//        )
//      } else {
//        Spacer(
//          modifier = Modifier
//            .weight(1f)
//            .padding(3.dp)
//        )
//      }
//    }
//
//  }
//}
//
//@ExperimentalAnimationApi
//@ExperimentalCoilApi
//@ExperimentalMaterialApi
//@Composable
//private fun TeamNumberCard(
//  id: Int,
//  modifier: Modifier = Modifier,
//  isSelected: Boolean = false,
//  onClick: () -> Unit
//) {
//  Box(
//    modifier = modifier.height(140.dp)
//  ) {
//    Card(modifier = Modifier
//      .fillMaxWidth(0.95f)
//      .align(Alignment.TopEnd)
//      .padding(top = 2.dp),
//      shape = RoundedCornerShape(4.dp),
//      onClick = { onClick() }) {
//      Image(
//        painter = painterResource(id = R.drawable.team_card_bg),
//        contentDescription = "",
//        modifier = Modifier
//          .fillMaxWidth(),
//        contentScale = ContentScale.FillWidth
//      )
//
//      Row(
//        Modifier
//          .fillMaxWidth()
//          .fillMaxHeight(0.12f)
//          .align(Alignment.BottomCenter)
//          .clip(RoundedCornerShape(4.dp))
//          .background(Color.White),
//        verticalAlignment = Alignment.CenterVertically
//      ) {
//        Text(
//          text = ResUtils.getNameById(id, "", LocalContext.current), color = Color.Black,
//          style = TextStyle(fontSize = 8.sp),
//          modifier = Modifier
//            .weight(1f)
//            .padding(start = 2.dp),
//          maxLines = 1
//        )
//
//        Image(
//          painter = painterResource(id = R.drawable.golden_pokeball),
//          contentDescription = "",
//          Modifier
//            .size(12.dp)
//            .padding(end = 2.dp)
//        )
//      }
//    }
//
//    Image(
//      painter = rememberImagePainter(data = getPokemonImageUrl(id, "")),
//      contentDescription = "",
//      modifier = Modifier
//        .align(Alignment.TopStart)
//        .fillMaxWidth(0.98f)
//    )
//
//    AnimatedVisibility(visible = !isSelected, enter = fadeIn(), exit = fadeOut()) {
//      Canvas(modifier = Modifier.fillMaxSize()) {
//        drawRect(TeamBackgroundColor, alpha = 0.5f)
//      }
//    }
//  }
//}
