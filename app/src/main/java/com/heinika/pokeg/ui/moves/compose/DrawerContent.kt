package com.heinika.pokeg.ui.moves.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.Generation
import com.heinika.pokeg.utils.MoveProp
import com.heinika.pokeg.utils.PokemonProp
import com.heinika.pokeg.utils.SystemBar

@ExperimentalMaterialApi
@Composable
fun DrawerContent(
  onSelectedTypeChange: (List<PokemonProp.Type>) -> Unit,
  onSelectedGenerationChange: (List<Generation>) -> Unit,
  onTypeSortClick: OnTypeSortClick,
  onMoveOrderChange: OnMoveOrderChange,
  onDamageClassSelectChange: (MoveProp.DamageClass?) -> Unit
) {
  Spacer(modifier = Modifier.height(16.dp))
  TypeClipList(modifier = Modifier.padding(8.dp, 0.dp), onSelectedChange = {
    onSelectedTypeChange(it)
  })

  Spacer(modifier = Modifier.height(16.dp))
  GenerationClipList(modifier = Modifier.padding(8.dp, 0.dp), onSelectedChange = {
    onSelectedGenerationChange(it)
  })

  Spacer(modifier = Modifier.height(16.dp))
  DamageClassClipRow(modifier = Modifier.padding(16.dp, 0.dp),onDamageClassSelectChange = {
    onDamageClassSelectChange(it)
  })

  Spacer(modifier = Modifier.height(16.dp))
  ReorderableList(
    modifier = Modifier.padding(16.dp, 0.dp),
    onTypeSortClick = onTypeSortClick,
    onMoveOrderChange = onMoveOrderChange
  )

  Spacer(modifier = Modifier.height(Dp(SystemBar.statusBarHeightDp)))
}


@ExperimentalMaterialApi
@Composable
@Preview
fun DrawerContentPreview() {
  PokeGTheme() {
    Surface() {
      Column() {
        DrawerContent(
          onSelectedTypeChange = {},
          onSelectedGenerationChange = {},
          onMoveOrderChange = {},
          onTypeSortClick = { _, _ -> },
          onDamageClassSelectChange = {}
        )
      }
    }
  }
}