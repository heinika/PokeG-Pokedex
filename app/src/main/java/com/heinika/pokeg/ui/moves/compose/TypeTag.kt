package com.heinika.pokeg.ui.moves.compose

import android.content.Context
import android.util.AttributeSet
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.heinika.pokeg.repository.res.ResUtils
import com.heinika.pokeg.ui.theme.BlackColor
import com.heinika.pokeg.ui.theme.DisableColor
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.PokemonProp
import com.heinika.pokeg.utils.toTypeColor


@ExperimentalMaterialApi
@Composable
fun TypeChip(
  typeChipStatus: ChipStatus,
  modifier: Modifier = Modifier,
  typeId: Int,
  onClick: () -> Unit
) {
  val typeColor = typeId.toTypeColor
  val text = ResUtils.getTypeString(typeId, LocalContext.current)
  BaseChip(typeChipStatus, typeColor, modifier.width(68.dp), onClick, text)
}


@ExperimentalMaterialApi
@Composable
private fun BaseChip(
  chipStatus: ChipStatus,
  typeColor: Color,
  modifier: Modifier,
  onClick: () -> Unit,
  text: String
) {
  Card(
    backgroundColor = when (chipStatus) {
      ChipStatus.Selected -> typeColor
      ChipStatus.UnSelected -> Color.Transparent
      ChipStatus.Disable -> Color.Transparent
    },
    contentColor = when (chipStatus) {
      ChipStatus.Selected -> BlackColor
      ChipStatus.UnSelected -> typeColor
      ChipStatus.Disable -> DisableColor
    },
    shape = CircleShape,
    border = BorderStroke(
      width = 1.dp,
      color = when (chipStatus) {
        ChipStatus.Selected -> typeColor
        ChipStatus.UnSelected -> typeColor
        ChipStatus.Disable -> DisableColor
      }
    ),
    modifier = modifier,
    onClick = { onClick() }
  ) {
    Text(
      text = text,
      textAlign = TextAlign.Center,
      style = MaterialTheme.typography.body2,
      modifier = Modifier.padding(8.dp)
    )

  }
}

enum class ChipStatus {
  Selected(),
  UnSelected(),
  Disable()
}

@ExperimentalMaterialApi
@Composable
fun TypeClipList() {
//  var typeChipStatus by remember { mutableStateOf(ChipStatus.UnSelected) }
//  var typeChipsStatus by remember {
//    mutableStateOf(mutableListOf<ChipStatus>().apply {
//      repeat(PokemonProp.Type.values().size) {
//        add(ChipStatus.UnSelected)
//      }
//    }.toList())
//  }

  val typeChipsStatus = remember {
    mutableListOf<ChipStatus>().apply {
      repeat(PokemonProp.Type.values().size) {
        add(ChipStatus.UnSelected)
      }
    }.toMutableStateList()
  }

  FlowRow(
    Modifier
      .fillMaxWidth()
      .padding(8.dp),
    mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
    lastLineMainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly,
    mainAxisSpacing = 8.dp,
    crossAxisSpacing = 8.dp
  ) {
    PokemonProp.Type.values().forEachIndexed { index, it ->
      TypeChip(typeChipStatus = typeChipsStatus[index], typeId = it.typeId, onClick = {
        when (typeChipsStatus[index]) {
          ChipStatus.Selected -> {
            typeChipsStatus[index] = ChipStatus.UnSelected
            if (typeChipsStatus.filter { it == ChipStatus.Selected }.size == 1){
              for (typeChipsIndex in typeChipsStatus.indices){
                if (typeChipsStatus[typeChipsIndex] != ChipStatus.Selected){
                  typeChipsStatus[typeChipsIndex] = ChipStatus.UnSelected
                }
              }
            }
          }
          ChipStatus.UnSelected -> {
            typeChipsStatus[index] = ChipStatus.Selected
            if (typeChipsStatus.filter { it == ChipStatus.Selected }.size >= 2){
              for (typeChipsIndex in typeChipsStatus.indices){
                if (typeChipsStatus[typeChipsIndex] != ChipStatus.Selected){
                  typeChipsStatus[typeChipsIndex] = ChipStatus.Disable
                }
              }
            }
          }
          ChipStatus.Disable -> {}
        }
      })
    }
  }
}

@ExperimentalMaterialApi
class TypeClipListView @JvmOverloads constructor(
  context: Context,
  attrs: AttributeSet? = null,
  defStyle: Int = 0
) : AbstractComposeView(context, attrs, defStyle) {

  @Composable
  override fun Content() {
    PokeGTheme {
      Text("Hello")
    }
  }

}


@ExperimentalMaterialApi
@Composable
@Preview
fun ChipPreview() {
  var typeChipStatus by remember { mutableStateOf(ChipStatus.UnSelected) }
  TypeChip(typeChipStatus = typeChipStatus, typeId = 12, onClick = {
    when (typeChipStatus) {
      ChipStatus.Selected -> typeChipStatus = ChipStatus.UnSelected
      ChipStatus.UnSelected -> typeChipStatus = ChipStatus.Selected
      ChipStatus.Disable -> ChipStatus.Disable
    }
  })
}