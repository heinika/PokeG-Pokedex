package com.heinika.pokeg.ui.moves.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.PokemonProp
import com.heinika.pokeg.utils.toGenerationColor


@ExperimentalMaterialApi
@Composable
fun GenerationChip(
  chipStatus: ChipStatus,
  modifier: Modifier = Modifier,
  generation: PokemonProp.Generation,
  onClick: () -> Unit
) {
  val typeColor = generation.id.toGenerationColor
  BaseChip(chipStatus, typeColor, modifier.width(38.dp), onClick, generation.filterString)
}

@ExperimentalMaterialApi
@Composable
fun GenerationClipList(onSelectedChange:(List<PokemonProp.Generation>)->Unit) {
  val generationArray = PokemonProp.Generation.values()
  val generationChipsStatus = remember {
    mutableListOf<ChipStatus>().apply {
      repeat(generationArray.size) {
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
    mainAxisSpacing = 4.dp,
    crossAxisSpacing = 4.dp
  ) {
    generationArray.forEachIndexed { index, it ->
      GenerationChip(chipStatus = generationChipsStatus[index], generation = it, onClick = {
        when (generationChipsStatus[index]) {
          ChipStatus.Selected -> generationChipsStatus[index] = ChipStatus.UnSelected
          ChipStatus.UnSelected ->generationChipsStatus[index] = ChipStatus.Selected
          ChipStatus.Disable -> {}
        }

        onSelectedChange(mutableListOf<PokemonProp.Generation>().apply {
          generationChipsStatus.forEachIndexed { index, chipStatus ->
            if (chipStatus == ChipStatus.Selected){
              add(generationArray[index])
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
fun GenerationChipPreview() {
  var typeChipStatus by remember { mutableStateOf(ChipStatus.UnSelected) }
  PokeGTheme {
    Surface {
      GenerationChip(chipStatus = typeChipStatus, generation = PokemonProp.Generation.GenerationI, onClick = {
        when (typeChipStatus) {
          ChipStatus.Selected -> typeChipStatus = ChipStatus.UnSelected
          ChipStatus.UnSelected -> typeChipStatus = ChipStatus.Selected
          ChipStatus.Disable -> ChipStatus.Disable
        }
      })
    }
  }
}