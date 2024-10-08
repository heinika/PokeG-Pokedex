package com.heinika.pokeg.module.versions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.heinika.pokeg.info.Generation
import com.heinika.pokeg.info.Version
import com.heinika.pokeg.utils.SystemBar


@Preview
@Composable
fun VersionsScreen() {
  val versionMap = Version.values().groupBy { it.generation }
  val scrollState = rememberScrollState()
  Column(modifier = Modifier.verticalScroll(scrollState)) {
    Spacer(modifier = Modifier.height(Dp(SystemBar.statusBarHeightDp + 8)))
    versionMap.keys.forEach { generation ->
      GenerationCard(generation)
      FlowRow(modifier = Modifier.fillMaxWidth(), mainAxisAlignment = FlowMainAxisAlignment.SpaceAround) {
        versionMap[generation]!!.forEach {
          VersionCard(
            it.resDrawable, Modifier
              .padding(6.dp)
              .width(160.dp)
              .weight(1f)
          )
        }
      }
    }
  }
}

@Composable
private fun VersionCard(drawableId: Int, modifier: Modifier = Modifier) {
  Card(modifier) {
    Image(
      painter = painterResource(drawableId), contentDescription = "",
      Modifier
        .fillMaxWidth(), contentScale = ContentScale.FillWidth
    )
  }
}

@Composable
fun GenerationCard(gen: Generation) {
  Card(
    Modifier
      .padding(12.dp)
      .fillMaxWidth(), backgroundColor = colorResource(gen.resColor)
  ) {
    Text(
      text = stringResource(gen.resId),
      Modifier
        .padding(8.dp)
        .fillMaxWidth(), textAlign = TextAlign.Center
    )
  }
}