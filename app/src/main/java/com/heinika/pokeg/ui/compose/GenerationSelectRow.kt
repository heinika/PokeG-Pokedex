package com.heinika.pokeg.ui.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.heinika.pokeg.info.Generation
import com.heinika.pokeg.module.moves.compose.GenerationChip

@ExperimentalMaterialApi
@Composable
fun GenerationSelectRow(selectedList: List<Generation>, modifier: Modifier = Modifier, onSelectedChange: (List<Generation>) -> Unit) {
  val generationArray = Generation.values()
  val generationChipsStatus = remember {
    mutableListOf<ChipStatus>().apply {
      repeat(generationArray.size) { index ->
        if (selectedList.map { it.ordinal }.contains(index)) {
          add(ChipStatus.Selected)
        } else {
          add(ChipStatus.UnSelected)
        }
      }
    }.toMutableStateList()
  }

  FlowRow(
    modifier
      .fillMaxWidth(),
    mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween,
    lastLineMainAxisAlignment = FlowMainAxisAlignment.SpaceEvenly,
    mainAxisSpacing = 4.dp,
    crossAxisSpacing = 4.dp
  ) {
    generationArray.forEachIndexed { index, it ->
      GenerationChip(chipStatus = generationChipsStatus[index], generation = it, onClick = {
        when (generationChipsStatus[index]) {
          ChipStatus.Selected -> generationChipsStatus[index] = ChipStatus.UnSelected
          ChipStatus.UnSelected -> generationChipsStatus[index] = ChipStatus.Selected
          ChipStatus.Disable -> {}
        }

        onSelectedChange(mutableListOf<Generation>().apply {
          generationChipsStatus.forEachIndexed { index, chipStatus ->
            if (chipStatus == ChipStatus.Selected) {
              add(generationArray[index])
            }
          }
        })
      })
    }
  }
}