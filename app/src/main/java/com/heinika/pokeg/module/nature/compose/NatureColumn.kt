package com.heinika.pokeg.module.nature.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.heinika.pokeg.R
import com.heinika.pokeg.info.Nature
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.ui.theme.Red200
import com.heinika.pokeg.utils.SystemBar

@ExperimentalMaterialApi
@Composable
fun NatureColumn() {
  LazyColumn(
    modifier = Modifier.padding(top = Dp(SystemBar.statusBarHeightDp)),
    contentPadding = PaddingValues(bottom = 8.dp)
  ) {
    items(Nature.values()) {
      NatureCard(nature = it)
    }
  }
}

@ExperimentalMaterialApi
@Composable
fun NatureCard(nature: Nature, onClick: (() -> Unit)? = null) {
  val modifier = Modifier.padding(12.dp).fillMaxWidth().height(80.dp).fillMaxWidth()
  if (onClick == null){
    Card(modifier = modifier, backgroundColor = nature.color) {
      NatureCardContent(nature)
    }
  }else{
    Card(modifier = modifier, backgroundColor = nature.color, onClick = { onClick.invoke()}) {
      NatureCardContent(nature)
    }
  }
}

@Composable
fun NatureCardContent(nature: Nature) {
  ConstraintLayout(modifier = Modifier.fillMaxSize()) {
    val (nameLabel, propLabel, tasteLabel, propRow1, propRow2, taste1, taste2) = createRefs()
    Text(
      text = stringResource(id = nature.stringId),
      style = MaterialTheme.typography.h6,
      modifier = Modifier.constrainAs(nameLabel) {
        top.linkTo(parent.top, 12.dp)
        start.linkTo(parent.start, 12.dp)
      })

    Text(text = "能力", Modifier.constrainAs(propLabel) {
      top.linkTo(parent.top)
      bottom.linkTo(tasteLabel.top)
      start.linkTo(parent.start, 100.dp)
    })

    Row(Modifier.constrainAs(propRow1) {
      top.linkTo(propLabel.top)
      bottom.linkTo(propLabel.bottom)
      start.linkTo(propLabel.end)
      end.linkTo(propRow2.start)
    }, verticalAlignment = Alignment.CenterVertically) {
      Text(text = stringResource(nature.growEasyStringId))
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
      Text(text = stringResource(id = nature.growHardStringId))
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
    }, taste = nature.tasteLikeId.toTaste)

    TasteRow(Modifier.constrainAs(taste2) {
      top.linkTo(tasteLabel.top)
      bottom.linkTo(tasteLabel.bottom)
      start.linkTo(taste1.end)
      end.linkTo(parent.end)
    }, taste = nature.tasteDislikeId.toTaste)
  }
}

val Int.toTaste: Taste
  get() = when (this) {
    R.string.acid -> Taste.Acid
    R.string.sweet -> Taste.Sweet
    R.string.bitter -> Taste.Bitter
    R.string.hot -> Taste.Hot
    R.string.astringent -> Taste.Astringent
    else -> Taste.NoSpecial
  }

@ExperimentalMaterialApi
@Preview
@Composable
fun NatureCardPreview() {
  PokeGTheme {
    NatureCard(Nature.Adamant)
  }
}

enum class Taste(val stringId: Int, val drawableId: Int) {
  Acid(R.string.acid, R.drawable.lemon),
  Sweet(R.string.sweet, R.drawable.donut),
  Bitter(R.string.bitter, R.drawable.kugua),
  Hot(R.string.hot, R.drawable.chili),
  Astringent(R.string.astringent, R.drawable.persimmon),
  NoSpecial(R.string.teste_equal, R.drawable.ic_search)
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