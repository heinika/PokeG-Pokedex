package com.heinika.pokeg.module.moves.compose


import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.info.MoveProp
import com.heinika.pokeg.ui.compose.MoveSortChip
import com.heinika.pokeg.ui.compose.SortChipStatus
import com.heinika.pokeg.ui.theme.PokeGTheme
import org.burnoutcrew.reorderable.*

typealias OnTypeSortClick = (MoveProp.SortType, SortChipStatus) -> Unit
typealias OnMoveOrderChange = (List<MoveProp.SortType>) -> Unit


@ExperimentalMaterialApi
@Composable
fun ReorderableList(
  modifier: Modifier = Modifier,
  onTypeSortClick: OnTypeSortClick,
  onMoveOrderChange: OnMoveOrderChange
) {
  val state = rememberReorderState()
  val moveSortTypes by remember { mutableStateOf(MoveProp.SortType.values().toMutableList()) }
  Row(modifier, verticalAlignment = Alignment.CenterVertically) {
    Text(text = "排序顺序：")
    LazyRow(
      state = state.listState,
      modifier = Modifier.reorderable(
        state,
        { from, to ->
          moveSortTypes.move(from.index, to.index)
          onMoveOrderChange(moveSortTypes)
        },
        orientation = Orientation.Horizontal
      )
    ) {
      items(moveSortTypes, { it }) { sortType ->
        MoveSortChip(sortType, modifier = Modifier
          .draggedItem(state.offsetByKey(sortType), orientation = Orientation.Horizontal)
          .detectReorderAfterLongPress(state), onTypeSortClick = { type, state ->
          onTypeSortClick(type, state)
        })
        Spacer(modifier = Modifier.width(8.dp))
      }
    }
  }
}


@ExperimentalMaterialApi
@Composable
@Preview
fun ReorderableListPreview() {
  PokeGTheme {
    Surface {
      ReorderableList(onTypeSortClick = { _, _ -> }, onMoveOrderChange = {})
    }
  }
}