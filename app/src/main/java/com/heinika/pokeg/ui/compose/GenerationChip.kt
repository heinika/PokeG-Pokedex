package com.heinika.pokeg.module.moves.compose

import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.info.Generation
import com.heinika.pokeg.ui.compose.ChipStatus
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.toGenerationColor


@ExperimentalMaterialApi
@Composable
fun GenerationChip(
  chipStatus: ChipStatus,
  modifier: Modifier = Modifier,
  generation: Generation,
  onClick: () -> Unit
) {
  val typeColor = generation.id.toGenerationColor
  BaseChip(chipStatus, typeColor, modifier.width(38.dp), onClick, generation.filterString)
}


@ExperimentalMaterialApi
@Composable
@Preview
fun GenerationChipPreview() {
  var typeChipStatus by remember { mutableStateOf(ChipStatus.UnSelected) }
  PokeGTheme {
    Surface {
      GenerationChip(chipStatus = typeChipStatus, generation = Generation.GenerationI, onClick = {
        when (typeChipStatus) {
          ChipStatus.Selected -> typeChipStatus = ChipStatus.UnSelected
          ChipStatus.UnSelected -> typeChipStatus = ChipStatus.Selected
          ChipStatus.Disable -> ChipStatus.Disable
        }
      })
    }
  }
}