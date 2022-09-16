package com.heinika.pokeg.ui.compose

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.heinika.pokeg.ConfigMMKV
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.repository.res.ResUtils
import com.heinika.pokeg.ui.theme.ColorTheme
import com.heinika.pokeg.ui.theme.PokeGTheme


@ExperimentalMaterialApi
@Composable
fun TypeChip(
  typeChipStatus: ChipStatus,
  modifier: Modifier = Modifier,
  typeId: Int,
  onClick: () -> Unit
) {
  val type = Type.values()[typeId - 1]
  val typeColor = when(ConfigMMKV.colorTheme){
    ColorTheme.AUTO -> if(isSystemInDarkTheme()) type.endColor else type.darkEndColor
    ColorTheme.LIGHT -> type.darkEndColor
    ColorTheme.DARK -> type.endColor
  }
  val text = ResUtils.getTypeString(typeId, LocalContext.current)
  BaseChip(typeChipStatus, typeColor, modifier.width(68.dp), onClick, text)
}

enum class ChipStatus {
  Selected,
  UnSelected,
  Disable
}

@ExperimentalMaterialApi
@Composable
fun TypeClipList(modifier: Modifier = Modifier, onSelectedChange: (List<Type>) -> Unit) {
  val typeArray = Type.values().dropLast(1)
  val typeChipsStatus = remember {
    mutableListOf<ChipStatus>().apply {
      repeat(typeArray.size) {
        add(ChipStatus.UnSelected)
      }
    }.toMutableStateList()
  }

  FlowRow(
    modifier
      .fillMaxWidth()
      .padding(8.dp),
    mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
    lastLineMainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly,
    mainAxisSpacing = 8.dp,
    crossAxisSpacing = 8.dp
  ) {
    typeArray.forEachIndexed { index, it ->
      TypeChip(typeChipStatus = typeChipsStatus[index], typeId = it.typeId, onClick = {
        when (typeChipsStatus[index]) {
          ChipStatus.Selected -> typeChipsStatus[index] = ChipStatus.UnSelected
          ChipStatus.UnSelected -> typeChipsStatus[index] = ChipStatus.Selected
          ChipStatus.Disable -> {
          }
        }

        onSelectedChange(mutableListOf<Type>().apply {
          typeChipsStatus.forEachIndexed { index, chipStatus ->
            if (chipStatus == ChipStatus.Selected) {
              add(typeArray[index])
            }
          }
        })
      })
    }
  }
}

@ExperimentalMaterialApi
@Composable
fun SelectTwoTypeClipList(typeChipsStatus: MutableList<ChipStatus>, onSelectedChange: (List<Type>) -> Unit) {
  val typeArray = remember { Type.values().dropLast(1) }

  FlowRow(
    Modifier
      .fillMaxWidth()
      .padding(4.dp),
    mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
    lastLineMainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly,
    mainAxisSpacing = 4.dp,
    crossAxisSpacing = 4.dp
  ) {
    typeArray.forEachIndexed { index, it ->
      TypeChip(typeChipStatus = typeChipsStatus[index], typeId = it.typeId, onClick = {
        when (typeChipsStatus[index]) {
          ChipStatus.Selected -> {
            typeChipsStatus[index] = ChipStatus.UnSelected
            if (typeChipsStatus.filter { it == ChipStatus.Selected }.size == 1) {
              for (typeChipsIndex in typeChipsStatus.indices) {
                if (typeChipsStatus[typeChipsIndex] != ChipStatus.Selected) {
                  typeChipsStatus[typeChipsIndex] = ChipStatus.UnSelected
                }
              }
            }
          }
          ChipStatus.UnSelected -> {
            typeChipsStatus[index] = ChipStatus.Selected
            if (typeChipsStatus.filter { it == ChipStatus.Selected }.size >= 2) {
              for (typeChipsIndex in typeChipsStatus.indices) {
                if (typeChipsStatus[typeChipsIndex] != ChipStatus.Selected) {
                  typeChipsStatus[typeChipsIndex] = ChipStatus.Disable
                }
              }
            }
          }
          ChipStatus.Disable -> {
          }
        }
        onSelectedChange(mutableListOf<Type>().apply {
          typeChipsStatus.forEachIndexed { index, chipStatus ->
            if (chipStatus == ChipStatus.Selected) {
              add(typeArray[index])
            }
          }
        })
      })
    }
  }
}


@ExperimentalMaterialApi
@Composable
@Preview
fun ChipPreview() {
  var typeChipStatus by remember { mutableStateOf(ChipStatus.UnSelected) }
  PokeGTheme {
    Surface {
      TypeChip(typeChipStatus = typeChipStatus, typeId = 12, onClick = {
        when (typeChipStatus) {
          ChipStatus.Selected -> typeChipStatus = ChipStatus.UnSelected
          ChipStatus.UnSelected -> typeChipStatus = ChipStatus.Selected
          ChipStatus.Disable -> ChipStatus.Disable
        }
      })
    }
  }
}