package com.heinika.pokeg.module.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.info.*
import com.heinika.pokeg.ui.compose.*
import com.heinika.pokeg.ui.theme.Red200
import com.heinika.pokeg.ui.theme.grassColor
import com.heinika.pokeg.utils.SystemBar

@ExperimentalMaterialApi
@Composable
fun HomeBottomDrawer(
  types: List<Type>,
  generations: List<Generation>,
  baseStatusList: List<BaseStatus>,
  isBaseStatusDesc: Boolean,
  selectedBodyStatus: BodyStatus?,
  selectedTags: List<Tag>,
  onTypeSelectedChange: (List<Type>) -> Unit,
  onSelectedGenerationChange: (List<Generation>) -> Unit,
  onStatusChipClick: (BaseStatus) -> Unit,
  onBodyStatusChipClick: (BodyStatus) -> Unit,
  onTagClick: (Tag) -> Unit,
  onBaseStatusSumClick: () -> Unit,
  onBaseStatusAscClick: () -> Unit,
  onBaseStatusDescClick: () -> Unit
) {
  val typeChipStatusList = remember {
    mutableStateListOf<ChipStatus>().apply {
      Type.values().dropLast(1).forEach {
        add(if (types.contains(it)) ChipStatus.Selected else ChipStatus.UnSelected)
      }
    }
  }
  SelectTwoTypeClipList(typeChipsStatus = typeChipStatusList, onSelectedChange = { onTypeSelectedChange(it) })

  GenerationSelectRow(generations, modifier = Modifier.padding(8.dp, 0.dp), onSelectedChange = {
    onSelectedGenerationChange(it)
  })

  @Suppress("SimplifiableCallChain") val descText =
    when {
      BaseStatus.values().size == baseStatusList.size -> "排序:总和"
      selectedBodyStatus != null -> "排序:${stringResource(id = selectedBodyStatus.resId)}"
      else -> "排序:${baseStatusList.map { stringResource(id = it.resId) }.joinToString("+")}"
    }

  SortSelectRow(descText, isBaseStatusDesc, onAscClick = onBaseStatusAscClick, onDescClick = onBaseStatusDescClick)
  BaseStatusSelectedRow(baseStatusList, onStatusChipClick, onBaseStatusSumClick)
  BodyStatusSelectedRow(selectedBodyStatus, onBodyStatusChipClick)

  Text("标签", Modifier.padding(16.dp, 0.dp))
  TagSelectedRow(selectedTags, onTagClick = onTagClick)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BaseStatusSelectedRow(selectedList: List<BaseStatus>, onBaseStatusChipClick: (BaseStatus) -> Unit, onBaseStatusSumClick: () -> Unit) {
  LazyRow(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
    items(BaseStatus.values()) { baseStatus ->
      BaseStatusChip(isSelected = selectedList.contains(baseStatus), baseStatus = baseStatus, onClick = {
        onBaseStatusChipClick(it)
      })
    }
    item {
      BaseChip(
        chipStatus = selectedList.size == BaseStatus.values().size,
        color = grassColor,
        modifier = Modifier,
        onClick = { onBaseStatusSumClick() },
        text = "总和"
      )
    }
  }
}

@Composable
fun TagSelectedRow(selectedTagList: List<Tag>, onTagClick: (Tag) -> Unit) {
  LazyVerticalGrid(
    columns = GridCells.Adaptive(86.dp),
    modifier = Modifier
      .fillMaxSize()
      .padding(bottom = SystemBar.navigationBarHeightDp.dp),
    horizontalArrangement = Arrangement.SpaceEvenly
  ) {
    items(Tag.values()) { tag ->
      TagChip(isSelected = selectedTagList.contains(tag),
        modifier = Modifier.padding(3.dp, 0.dp),
        tag = tag,
        onClick = {
          onTagClick(it)
        })
    }
  }
}

@Composable
fun BodyStatusSelectedRow(selectedBodyStatus: BodyStatus?, onBodyStatusChipClick: (BodyStatus) -> Unit) {
  LazyRow {
    items(BodyStatus.values()) { bodyStatus ->
      BodyStatusChip(
        modifier = Modifier
          .padding(12.dp, 0.dp)
          .width(68.dp),
        isSelected = selectedBodyStatus == bodyStatus,
        bodyStatus = bodyStatus,
        onClick = { onBodyStatusChipClick(bodyStatus) })
    }
  }
}

@ExperimentalMaterialApi
@Composable
fun SortSelectRow(text: String, isDesc: Boolean, onDescClick: () -> Unit, onAscClick: () -> Unit) {
  Row(modifier = Modifier.padding(8.dp, 0.dp), verticalAlignment = Alignment.CenterVertically) {
    Text(text, Modifier.padding(8.dp, 0.dp))
    BaseChip(
      chipStatus = isDesc,
      color = grassColor,
      modifier = Modifier.padding(end = 8.dp).width(80.dp),
      onClick = { onDescClick() },
      text = "降序"
    )
    BaseChip(
      chipStatus = !isDesc,
      color = Red200,
      modifier = Modifier.width(80.dp),
      onClick = { onAscClick() },
      text = "升序"
    )
  }
}