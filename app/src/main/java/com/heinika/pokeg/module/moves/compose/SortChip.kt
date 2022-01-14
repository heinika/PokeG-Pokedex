package com.heinika.pokeg.module.moves.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.ui.theme.BlackColor
import com.heinika.pokeg.ui.theme.DisableColor
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.ui.theme.grassColor
import com.heinika.pokeg.info.MoveProp

@ExperimentalMaterialApi
@Composable
fun BaseSortChip(
  chipStatus: SortChipStatus,
  color: Color,
  modifier: Modifier = Modifier,
  onClick: () -> Unit,
  text: String
) {
  Card(
    backgroundColor = when (chipStatus) {
      SortChipStatus.Ascending -> color
      SortChipStatus.Descending -> color
      SortChipStatus.Disable -> Color.Transparent
    },
    contentColor = when (chipStatus) {
      SortChipStatus.Ascending -> BlackColor
      SortChipStatus.Descending -> BlackColor
      SortChipStatus.Disable -> DisableColor
    },
    shape = CircleShape,
    border = BorderStroke(
      width = 1.dp,
      color = when (chipStatus) {
        SortChipStatus.Ascending -> color
        SortChipStatus.Descending -> color
        SortChipStatus.Disable -> DisableColor
      }
    ),
    modifier = modifier,
    onClick = { onClick() }
  ) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      Text(
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.body2,
        modifier = Modifier.padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
      )
      val animatorRotate = animateFloatAsState(
        targetValue = when (chipStatus) {
          SortChipStatus.Ascending -> 180f
          SortChipStatus.Descending -> 0f
          SortChipStatus.Disable -> 0f
        }
      )
      when (chipStatus) {
        SortChipStatus.Ascending -> {
          Image(
            imageVector = Icons.Default.ArrowDropDown,
            modifier = Modifier.graphicsLayer {
              rotationX = animatorRotate.value
            },
            colorFilter = ColorFilter.tint(BlackColor),
            contentDescription = ""
          )
        }
        SortChipStatus.Descending -> {
          Image(
            imageVector = Icons.Default.ArrowDropDown,
            colorFilter = ColorFilter.tint(BlackColor),
            modifier = Modifier.graphicsLayer {
              rotationX = animatorRotate.value
            },
            contentDescription = ""
          )
        }
        SortChipStatus.Disable -> {
          Image(
            imageVector = Icons.Default.ArrowDropDown,
            modifier = Modifier.graphicsLayer {
              rotationX = animatorRotate.value
            },
            colorFilter = ColorFilter.tint(DisableColor),
            contentDescription = ""
          )
        }
      }

    }
  }
}

enum class SortChipStatus {
  Ascending,
  Descending,
  Disable
}

@ExperimentalMaterialApi
@Composable
fun MoveSortChip(
  sortType: MoveProp.SortType,
  onTypeSortClick: (MoveProp.SortType, SortChipStatus) -> Unit,
  modifier: Modifier = Modifier,
  initState: SortChipStatus = SortChipStatus.Disable
) {
  var chipStatus by remember { mutableStateOf(initState) }
  BaseSortChip(
    modifier = modifier,
    chipStatus = chipStatus,
    color = sortType.color,
    onClick = {
      chipStatus = when (chipStatus) {
        SortChipStatus.Ascending -> SortChipStatus.Disable
        SortChipStatus.Descending -> SortChipStatus.Ascending
        SortChipStatus.Disable -> SortChipStatus.Descending
      }
      onTypeSortClick(sortType, chipStatus)
    },
    text = stringResource(id = sortType.resStringId)
  )
}

@ExperimentalMaterialApi
@Preview
@Composable
fun SortChipPreview() {
  var chipStatus by remember { mutableStateOf(SortChipStatus.Disable) }
  PokeGTheme() {
    Surface() {
      BaseSortChip(
        chipStatus = chipStatus,
        color = grassColor,
        onClick = {
          chipStatus = when (chipStatus) {
            SortChipStatus.Ascending -> SortChipStatus.Disable
            SortChipStatus.Descending -> SortChipStatus.Ascending
            SortChipStatus.Disable -> SortChipStatus.Descending
          }
        },
        text = "power"
      )
    }
  }
}