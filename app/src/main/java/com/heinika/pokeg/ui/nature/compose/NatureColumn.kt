package com.heinika.pokeg.ui.nature.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.heinika.pokeg.R
import com.heinika.pokeg.model.Nature
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.ui.theme.Red200
import com.heinika.pokeg.ui.theme.grassColor
import com.heinika.pokeg.utils.SystemBar
import kotlinx.coroutines.NonDisposableHandle.parent


@Composable
fun NatureColumn(natureList: List<Nature>) {
  LazyColumn(modifier = Modifier.padding(top = Dp(SystemBar.statusBarHeightDp)),contentPadding = PaddingValues(bottom = 8.dp)) {
    items(natureList) {
      NatureCard(nature = it)
    }
  }
}

@Composable
fun NatureCard(nature: Nature) {
  Card(
    modifier = Modifier
      .padding(12.dp)
      .fillMaxWidth()
      .height(80.dp)
      .fillMaxWidth()
  ) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
      val (nameLabel, propLabel, tasteLabel, propRow1, propRow2, taste1, taste2) = createRefs()
      Text(
        text = nature.cname,
        style = MaterialTheme.typography.h6,
        modifier = Modifier.constrainAs(nameLabel) {
          top.linkTo(parent.top, 12.dp)
          start.linkTo(parent.start, 12.dp)
        })

      Text(text = "能力", Modifier.constrainAs(propLabel) {
        top.linkTo(parent.top)
        bottom.linkTo(tasteLabel.top)
        start.linkTo(parent.start,100.dp)
      })

      Row(Modifier.constrainAs(propRow1) {
        top.linkTo(propLabel.top)
        bottom.linkTo(propLabel.bottom)
        start.linkTo(propLabel.end)
        end.linkTo(propRow2.start)
      }, verticalAlignment = Alignment.CenterVertically) {
        Text(text = nature.grow_easy)
        Image(
          painter = painterResource(id = R.drawable.up), "", Modifier
            .size(18.dp)
        )
      }

      Row(Modifier.constrainAs(propRow2) {
        top.linkTo(propLabel.top)
        bottom.linkTo(propLabel.bottom)
        start.linkTo(propRow1.end)
        end.linkTo(parent.end)
      }, verticalAlignment = Alignment.CenterVertically) {
        Text(text = nature.grow_hard)
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
            .size(18.dp)
        )
      }

      Text(text = "口味", Modifier.constrainAs(tasteLabel) {
        top.linkTo(propLabel.bottom)
        bottom.linkTo(parent.bottom)
        start.linkTo(propLabel.start)
      })

      TasteRow(Modifier.constrainAs(taste1) {
        top.linkTo(tasteLabel.top)
        bottom.linkTo(tasteLabel.bottom)
        start.linkTo(tasteLabel.end)
        end.linkTo(taste2.start)
      }, taste = nature.taste_like.toTaste)

      TasteRow(Modifier.constrainAs(taste2) {
        top.linkTo(tasteLabel.top)
        bottom.linkTo(tasteLabel.bottom)
        start.linkTo(taste1.end)
        end.linkTo(parent.end)
      }, taste = nature.taste_dislike.toTaste)
    }
  }
}

val String.toTaste: Taste
  get() = when (this) {
    "酸" -> Taste.Acid
    "甜" -> Taste.Sweet
    "苦" -> Taste.Bitter
    "辣" -> Taste.Hot
    "涩" -> Taste.Astringent
    else -> Taste.NoSpecial
  }

@Preview
@Composable
fun NatureCardPreview() {
  PokeGTheme {
    NatureCard(
      Nature(
        cname = "慢吞吞",
        ename = "",
        jname = "",
        grow_easy = "特攻",
        grow_hard = "防御",
        taste_dislike = "辣",
        taste_like = "酸",
      )
    )
  }
}

enum class Taste(val stringId: Int, val drawableId: Int) {
  Acid(R.string.Acid, R.drawable.lemon),
  Sweet(R.string.Sweet, R.drawable.donut),
  Bitter(R.string.Bitter, R.drawable.kugua),
  Hot(R.string.Hot, R.drawable.chili),
  Astringent(R.string.Astringent, R.drawable.persimmon),
  NoSpecial(R.string.no_special, R.drawable.ic_search)
}


@Preview
@Composable
fun TasteRow(modifier: Modifier = Modifier, taste: Taste = Taste.Acid) {
  Row(modifier, verticalAlignment = Alignment.CenterVertically) {
    Text(text = stringResource(id = taste.stringId))
    Spacer(modifier = Modifier.width(8.dp))
    Image(
      painter = painterResource(taste.drawableId), "", modifier = Modifier
        .size(24.dp)
    )
  }
}