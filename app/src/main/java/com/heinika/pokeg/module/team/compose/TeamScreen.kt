package com.heinika.pokeg.module.team.compose

import android.widget.Toast

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.flowlayout.FlowRow
import com.heinika.pokeg.R
import com.heinika.pokeg.info.Move
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.model.MyPokemon
import com.heinika.pokeg.module.gameprops.props.CarryProps
import com.heinika.pokeg.module.team.TeamViewModel
import com.heinika.pokeg.ui.theme.BlackBackgroundColor
import com.heinika.pokeg.ui.theme.grassColor
import com.heinika.pokeg.ui.theme.waterColor
import com.heinika.pokeg.utils.SystemBar
import com.heinika.pokeg.utils.getPokemonImageUrl
import com.heinika.pokeg.utils.rememberFlavorPainter
import com.heinika.pokeg.utils.toTypeColor
import kotlin.random.Random



@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun TeamScreen(teamViewModel: TeamViewModel) {
  var isAddTeamDialogVisible by remember { mutableStateOf(false) }
  var selectedTeamName by remember { mutableStateOf("") }

  Scaffold(floatingActionButton = {
    FloatingActionButton(onClick = {

    }) {
      Image(Icons.Default.Add, "")
    }
  }) { paddingValues ->
    paddingValues.calculateTopPadding()
    Box(modifier = Modifier.fillMaxSize()) {
      val context = LocalContext.current
      val teamNumberMap by remember { teamViewModel.teamNumberMap }

      LazyColumn(
        Modifier.fillMaxSize()
      ) {
        item {
          Spacer(modifier = Modifier.height(Dp(SystemBar.statusBarHeightDp)))
        }

        if (teamNumberMap.isNotEmpty()) {
          items(teamNumberMap.entries.toList()) {
            TeamItemCard(it.key, mutableListOf<MyPokemon?>().apply {
              addAll(it.value)
              if (it.value.size <6){
                repeat(6 - it.value.size){
                  add(null)
                }
              }
            }, onAddClick = {
              isAddTeamDialogVisible = true
            })
          }
        } else {
          item {
            TeamItemCard(
              null,
              teamList = listOf(null, null, null, null, null, null),
              onAddClick = { teamName: String? ->
                if (teamName == null) {
                  Toast.makeText(context, "请先添加队名", Toast.LENGTH_SHORT).show()
                } else {
                  isAddTeamDialogVisible = true
                  selectedTeamName = teamName
                }
              })
          }
        }
      }

      if (isAddTeamDialogVisible) {
        AlertDialog(onDismissRequest = {
          isAddTeamDialogVisible = false
        }, buttons = {
          Text(text = "我的宝可梦")
          LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items(teamViewModel.allMyPokemonList.value) {
              TeamNumberCard(id = it.id, name = it.name) {
                if (it.teamName.isEmpty() || it.teamName == selectedTeamName){
                  teamViewModel.updateMyPokemon(it.copy(teamName = selectedTeamName))
                }else{
                  teamViewModel.updateMyPokemon(it.copy(teamName = it.teamName + ";" + selectedTeamName))
                }
              }
            }
          }
        })
      }

    }
  }
}


@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
private fun TeamItemCard(
  teamName: String?,
  teamList: List<MyPokemon?>,
  onAddClick: (teamName: String?) -> Unit
) {
  var selectedIndex by remember { mutableStateOf(0) }
  val teamNameState = remember { mutableStateOf(teamName) }
  var editable by remember { mutableStateOf(teamName == null) }

  Row(Modifier.padding(12.dp, 0.dp), verticalAlignment = Alignment.CenterVertically) {
    TextField(
      value = teamNameState.value ?: "未命名",
      enabled = editable,
      onValueChange = { teamNameState.value = it },
      textStyle = MaterialTheme.typography.h5,
      trailingIcon = {
        Image(
          painter = painterResource(id = R.drawable.baseline_edit_24),
          contentDescription = "",
          Modifier.clickable {
            editable = true
          })
      },
      colors = TextFieldDefaults.textFieldColors(backgroundColor = BlackBackgroundColor),
      modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 12.dp)
    )
  }

  TeamNumberDetail(
    Modifier
      .fillMaxWidth()
      .height(180.dp), teamList[selectedIndex],
    onAddClick = {
      onAddClick(teamNameState.value)
    }
  )

  TeamRow(modifier = Modifier
    .height(110.dp)
    .padding(6.dp), teamList.map { it?.id ?: -1 },
    initSelectedIndex = selectedIndex,
    nameList = teamList.map { it?.name ?: "" },
    onSelectChange = {
      selectedIndex = it
    })
}

@ExperimentalCoilApi
@Composable
fun TeamNumberDetail(
  modifier: Modifier = Modifier,
  teamNumberInfo: MyPokemon?,
  onAddClick: () -> Unit
) {
  if (teamNumberInfo != null) {
    ConstraintLayout(modifier) {
      val (image, nameLabel, typeRow, carryTag, natureTag, abilityTag, moveRow) = createRefs()
      Image(
        painter = rememberFlavorPainter(getPokemonImageUrl(teamNumberInfo.id)),
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

      Text(teamNumberInfo.name,
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
          }, teamNumberInfo.typeList
      )

      TagCard(
        modifier = Modifier.constrainAs(natureTag) {
          top.linkTo(nameLabel.bottom, 4.dp)
          start.linkTo(nameLabel.start)
        },
        typeName = stringResource(id = teamNumberInfo.nature.stringId),
        color = teamNumberInfo.nature.color
      )

      val randomColor =
        Color(Random.nextInt(0, 255), Random.nextInt(0, 255), Random.nextInt(0, 255), 255)
      TagCard(
        modifier = Modifier.constrainAs(abilityTag) {
          top.linkTo(nameLabel.bottom, 4.dp)
          start.linkTo(natureTag.end, 4.dp)
        },
        typeName = stringResource(teamNumberInfo.ability.nameResId),
        color = randomColor
      )

      CarryCard(
        modifier = Modifier.constrainAs(carryTag) {
          top.linkTo(natureTag.bottom, 4.dp)
          start.linkTo(nameLabel.start)
        },
        teamNumberInfo.carryProps
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
  } else {
    Box(modifier) {
      Button(onClick = { onAddClick() }, modifier = Modifier.align(Alignment.Center)) {
        Image(
          imageVector = Icons.Default.Add,
          contentDescription = "",
          colorFilter = ColorFilter.tint(Color.White)
        )
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
    Image(
      painter = painterResource(carry?.imageResId ?: R.drawable.ic_search),
      "",
      modifier = Modifier.size(32.dp)
    )
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


@ExperimentalMaterialApi
@ExperimentalCoilApi
@Preview
@Composable
fun TeamNumberCardPreview() {
  TeamRow(
    modifier = Modifier.padding(6.dp),
    listOf(151, 1, 150, 9, 250, 149),
    nameList = listOf("1", "1", "1", "1", "1", "1"),
    onSelectChange = {})
}


@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun TeamRow(
  modifier: Modifier = Modifier, idList: List<Int>,
  nameList: List<String>,
  initSelectedIndex: Int = 0, onSelectChange: (index: Int) -> Unit
) {
  var selectedIndex by remember { mutableStateOf(initSelectedIndex) }
  Row(modifier) {
    for (index in 0..5) {
      if (index < idList.size) {
        TeamNumberCard(
          id = idList[index],
          name = nameList[index],
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
