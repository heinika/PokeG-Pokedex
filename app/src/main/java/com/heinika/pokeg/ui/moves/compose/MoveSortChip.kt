package com.heinika.pokeg.ui.moves.compose


import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.MoveProp
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