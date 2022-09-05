package com.heinika.pokeg.module.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.info.MoveVersion
import com.heinika.pokeg.ui.theme.JollyColor
import com.heinika.pokeg.utils.SystemBar

@ExperimentalMaterialApi
@Composable
fun HomeLeftDrawer(moveVersion: MoveVersion, onChangeVersionClick: () -> Unit, onDrawerItemClick: (screen: String) -> Unit) {
  LazyColumn(modifier = Modifier.padding(top = SystemBar.statusBarHeightDp.dp)) {

    item {
      Card(modifier = Modifier
        .padding(12.dp, 6.dp)
        .height(42.dp)
        .fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        backgroundColor = JollyColor,
        onClick = { onChangeVersionClick() }
      ) {
        Box {
          Text(
            text = "默认技能版本：${stringResource(moveVersion.stringId)}",
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1
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
        backgroundColor = it.color,
        onClick = { onDrawerItemClick(it.screenName) }) {
        Box {
          Text(
            text = stringResource(id = it.nameStringId),
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body1
          )
        }
      }
    }
  }
}