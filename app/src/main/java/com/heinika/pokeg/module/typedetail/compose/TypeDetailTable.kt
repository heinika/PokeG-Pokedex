package com.heinika.pokeg.module.typedetail.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.R
import com.heinika.pokeg.info.Type

@Composable
fun TypeDetailTable(curTypes: List<Type>) {
  Column {
    Row(
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = stringResource(id = R.string.as_the_defensed),
        modifier = Modifier.padding(4.dp),
        style = MaterialTheme.typography.titleSmall
      )
      TypesCard(curTypes)
    }

    DefenseTable(curTypes)

    curTypes.forEach {
      Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = stringResource(id = R.string.as_the_attack),
          modifier = Modifier.padding(4.dp),
          style = MaterialTheme.typography.titleSmall
        )
        TypeCard(
          typeName = it.getName(LocalContext.current),
          modifier = Modifier.padding(4.dp),
          color = it.endColor
        )
      }
      AttackTable(it)
    }
  }
}


@Composable
fun DefenseTable(typeList: List<Type>) {
  if (typeList.size == 1) {
    val curType = typeList[0]
    val typesMap = linkedMapOf(
      "2x" to curType.defenseWeakList,
      "1/2x" to curType.defenseUpList,
      "0x" to curType.defenseZeroList,
    )

    TypeTable(typesMap)
  } else {
    val type1 = typeList[0]
    val type2 = typeList[1]
    val x4List = type1.defenseWeakList.filter { type2.defenseWeakList.contains(it) }
    val x025List = type1.defenseUpList.filter { type2.defenseUpList.contains(it) }
    val x0List = type1.defenseZeroList + type2.defenseZeroList

    val x2List = type1.defenseWeakList.filter {
      !type2.defenseWeakList.contains(it) && !type2.defenseUpList.contains(it) &&
          !type2.defenseZeroList.contains(it)
    } + type2.defenseWeakList.filter {
      !type1.defenseWeakList.contains(it) && !type1.defenseUpList.contains(it) &&
          !type1.defenseZeroList.contains(it)
    }
    val x05List = type1.defenseUpList.filter {
      !type2.defenseWeakList.contains(it) && !type2.defenseUpList.contains(it) &&
          !type2.defenseZeroList.contains(it)
    } + type2.defenseUpList.filter {
      !type1.defenseWeakList.contains(it) && !type1.defenseUpList.contains(it) &&
          !type1.defenseZeroList.contains(it)
    }

    val typesMap = linkedMapOf(
      "4x" to x4List,
      "2x" to x2List,
      "1/2x" to x05List,
      "1/4x" to x025List,
      "0x" to x0List,
    )

    TypeTable(typesMap)
  }
}

@Composable
private fun AttackTable(curType: Type) {
  val x4List = arrayListOf<List<Type>>().apply {
    val end = curType.attackDoubleList.size - 1
    for (i in curType.attackDoubleList.indices) {
      if (i < end) {
        for (j in i + 1..end) {
          add(listOf(curType.attackDoubleList[i], curType.attackDoubleList[j]))
        }
      }
    }
  }

  val x025List = arrayListOf<List<Type>>().apply {
    val end = curType.attackHalfList.size - 1
    for (i in curType.attackHalfList.indices) {
      if (i < end) {
        for (j in i + 1..end) {
          add(listOf(curType.attackHalfList[i], curType.attackHalfList[j]))
        }
      }
    }
  }

  val typesMap = linkedMapOf(
    "4x" to x4List,
    "2x" to curType.attackDoubleList.map { listOf(it) },
    "1/2x" to curType.attackHalfList.map { listOf(it) },
    "1/4x" to x025List,
    "0x" to curType.attackZeroList.map { listOf(it) },
  )

  TypesTable(typesMap)
}

@Composable
private fun TypesTable(typesMap: LinkedHashMap<String, List<List<Type>>>) {
  Column(Modifier.fillMaxWidth()) {
    typesMap.entries.forEach {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          it.key,
          Modifier.weight(2f),
          textAlign = TextAlign.Center,
          style = MaterialTheme.typography.titleSmall
        )

        TypesFilterRow(
          Modifier
            .weight(8f)
            .height(if (it.value.size <= 6) 30.dp else 60.dp), it.value
        )
      }
    }
  }
}

@Composable
private fun TypeTable(typesMap: LinkedHashMap<String, List<Type>>) {
  Column(Modifier.fillMaxWidth()) {
    typesMap.entries.forEach {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
          it.key,
          Modifier.weight(2f),
          textAlign = TextAlign.Center,
          style = MaterialTheme.typography.titleSmall
        )

        TypeFilterGrid(
          Modifier
            .weight(8f)
            .height(if (it.value.size <= 6) 30.dp else 60.dp), it.value
        )
      }
    }
  }
}

@Composable
private fun TypesFilterRow(modifier: Modifier, typesList: List<List<Type>>) {
  Box(modifier) {
    if (typesList.isEmpty()) {
      EmptyText(modifier = Modifier.align(Alignment.CenterStart))
    } else {
      if (typesList.size <= 6) {
        LazyRow(modifier = Modifier.align(Alignment.CenterStart), verticalAlignment = Alignment.CenterVertically) {
          items(typesList) {
            TypesCard(it)
          }
        }
      } else {
        LazyHorizontalGrid(
          modifier = Modifier.align(Alignment.CenterStart),
          rows = GridCells.Fixed(2),
          verticalArrangement = Arrangement.Center,
          horizontalArrangement = Arrangement.Center,
          content = {
            items(typesList) {
              TypesCard(it)
            }
          })
      }
    }
  }
}

@Composable
private fun TypesCard(it: List<Type>) {
  if (it.size == 1) {
    val curType = it.first()
    TypeCard(
      typeName = curType.getName(LocalContext.current),
      modifier = Modifier.padding(4.dp),
      color = curType.endColor
    )
  } else {
    val curType1 = it.first()
    val curType2 = it.last()
    TypesCard(
      typeName = curType1.getName(LocalContext.current) + "/" + curType2.getName(
        LocalContext.current
      ),
      modifier = Modifier.padding(4.dp),
      colors = listOf(curType1.endColor, curType2.endColor)
    )
  }
}

@Composable
private fun TypeFilterGrid(modifier: Modifier, typeList: List<Type>) {
  Box(modifier = modifier) {
    if (typeList.isEmpty()) {
      EmptyText(modifier = Modifier.align(Alignment.CenterStart))
    } else {
      if (typeList.size <= 6) {
        LazyRow(modifier = Modifier.align(Alignment.CenterStart)) {
          items(typeList) {
            TypeCard(
              typeName = it.getName(LocalContext.current),
              modifier = Modifier.padding(4.dp),
              color = it.endColor
            )
          }
        }
      } else {
        LazyHorizontalGrid(
          modifier = Modifier.align(Alignment.CenterStart),
          rows = GridCells.Fixed(2),
          verticalArrangement = Arrangement.Center,
          horizontalArrangement = Arrangement.Center,
          content = {
            items(typeList) {
              TypeCard(
                typeName = it.getName(LocalContext.current),
                modifier = Modifier.padding(4.dp),
                color = it.endColor
              )
            }
          })
      }
    }
  }
}

@Composable
private fun TypesCard(modifier: Modifier = Modifier, typeName: String = "草", colors: List<Color>) {
  Box(
    modifier
      .width(60.dp)
      .clip(RoundedCornerShape(12.dp))
      .background(brush = Brush.linearGradient(colors))
  ) {
    Text(
      text = typeName,
      Modifier.align(Alignment.Center),
      color = Color.White,
      style = MaterialTheme.typography.bodyMedium
    )
  }
}

@Composable
private fun EmptyText(modifier: Modifier = Modifier) {
  TypeCard(
    typeName = stringResource(id = R.string.none),
    modifier = modifier.padding(4.dp),
    color = Color.Black
  )
}

@Composable
private fun TypeCard(modifier: Modifier = Modifier, typeName: String = "草", color: Color) {
  Box(
    modifier
      .width(42.dp)
      .height(20.dp)
      .clip(RoundedCornerShape(12.dp))
      .background(color)
  ) {
    Text(
      text = typeName,
      Modifier.align(Alignment.Center),
      color = Color.White,
      style = MaterialTheme.typography.bodyMedium
    )
  }
}