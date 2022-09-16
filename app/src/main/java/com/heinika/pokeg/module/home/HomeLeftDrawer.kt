package com.heinika.pokeg.module.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.*
import com.heinika.pokeg.R
import com.heinika.pokeg.info.MoveVersion
import com.heinika.pokeg.ui.theme.*
import com.heinika.pokeg.utils.SystemBar

enum class DrawerScreens(val nameStringId: Int, val screenName: String, val color: Color) {
  VersionsScreen(R.string.version_list, VERSION_LIST_SCREEN, RelaxedColor),
  TypesDetailScreen(R.string.type_detail, TYPE_DETAIL_SCREEN, MildColor),
  AbilitiesScreen(R.string.abilities_detail, ABILITIES_SCREEN, HardyColor),
  MovesScreen(R.string.move_list, MOVES_SCREEN, BraveColor),
  MyPokemonScreen(R.string.my_pokemon, MY_POKEMON_SCREEN, DocileColor),
  TeamScreen(R.string.team, TEAM_SCREEN, ImpishColor),
  GamePropsScreen(R.string.props_list, GAME_PROPS_SCREEN, QuietColor),
  NaturesScreen(R.string.nature_list, NATURE_SCREEN, CalmColor),
  AboutScreen(R.string.about, ABOUT_SCREEN, LonelyColor),
  DonationScreen(R.string.donation, DONATION_SCREEN, SassyColor),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeLeftDrawer(
  moveVersion: MoveVersion,
  onChangeVersionClick: () -> Unit,
  onDrawerItemClick: (screen: String) -> Unit,
  selectedColorTheme: ColorTheme,
  onColorThemeChange: (ColorTheme) -> Unit
) {
  LazyColumn(modifier = Modifier.padding(top = SystemBar.statusBarHeightDp.dp)) {
    item {
      Card(
        modifier = Modifier
          .padding(12.dp, 6.dp)
          .height(42.dp)
          .fillMaxWidth(),
        shape = MaterialTheme.shapes.small
      ) {
        Row(Modifier.fillMaxWidth()) {
          ColorTheme.values().forEach {
            val isSelected = it == selectedColorTheme
            val border = BorderStroke(if (isSelected) 2.dp else 0.dp, if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent)
            Box(
              modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .border(border)
                .clickable {
                  onColorThemeChange(it)
                }
            ) {
              Text(text = stringResource(id = it.stringId), modifier = Modifier.align(Alignment.Center))
            }
          }
        }
      }
    }

    item {
      Card(modifier = Modifier
        .padding(12.dp, 6.dp)
        .height(42.dp)
        .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(containerColor = JollyColor),
        onClick = { onChangeVersionClick() }
      ) {
        Box(Modifier.fillMaxSize()) {
          Text(
            text = "默认技能版本：${stringResource(moveVersion.stringId)}",
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
          )
        }
      }
    }

    items(DrawerScreens.values()) {
      Card(modifier = Modifier
        .padding(12.dp, 6.dp)
        .height(42.dp)
        .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        colors = CardDefaults.cardColors(containerColor = it.color),
        onClick = { onDrawerItemClick(it.screenName) }) {
        Box(Modifier.fillMaxSize()) {
          Text(
            text = stringResource(id = it.nameStringId),
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
          )
        }
      }
    }
  }
}