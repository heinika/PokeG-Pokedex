package com.heinika.pokeg.module.typedetail.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.heinika.pokeg.PokemonDataCache.pokemonList
import com.heinika.pokeg.R
import com.heinika.pokeg.info.Type
import com.heinika.pokeg.module.moves.compose.ChipStatus
import com.heinika.pokeg.module.moves.compose.SelectTwoTypeClipList
import com.heinika.pokeg.module.mypokemon.compose.PokemonCard
import com.heinika.pokeg.utils.SystemBar

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun TypeDetailScreen() {
  var curTypes by remember {
    mutableStateOf<List<Type>>(emptyList())
  }
  val typeChipsStatus = remember {
    mutableListOf<ChipStatus>().apply {
      repeat(Type.values().size) {
        add(ChipStatus.UnSelected)
      }
    }.toMutableStateList()
  }

  LazyColumn(modifier = Modifier.padding(top = Dp(SystemBar.statusBarHeightDp))) {
    item {
      SelectTwoTypeClipList(typeChipsStatus) {
        curTypes = it
      }
    }

    if (curTypes.isNotEmpty()) {
      item {
        TypeDetailTable(curTypes)
      }

      items(pokemonList.filter {
        it.types.contains(curTypes.first().typeId) && it.types.contains(curTypes.last().typeId)
      }) { pokemon ->
        PokemonCard(pokemon = pokemon, onclick = {

        })
      }

    }
  }
}

@Composable
private fun TypeDetailTable(curTypes: List<Type>) {
  Column {
    Row(
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Text(
        text = stringResource(id = R.string.as_the_defensed),
        modifier = Modifier.padding(4.dp),
        style = MaterialTheme.typography.h4
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
          style = MaterialTheme.typography.h4
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
  Row(
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
  ) {
    typesMap.keys.forEach {
      Text(
        it,
        Modifier.weight(1f),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h5
      )
    }
  }
  Row(
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
  ) {
    typesMap.values.forEach {
      TypesFilterColumn(Modifier.weight(1f), it)
    }
  }
}

@Composable
private fun TypeTable(typesMap: LinkedHashMap<String, List<Type>>) {
  Row(
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
  ) {
    typesMap.keys.forEach {
      Text(
        it,
        Modifier.weight(1f),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h5
      )
    }
  }
  Row(
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
  ) {
    typesMap.values.forEach {
      TypeFilterColumn(Modifier.weight(1f), it)
    }
  }
}

@Composable
private fun TypesFilterColumn(modifier: Modifier, typesList: List<List<Type>>) {
  Column(modifier) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
      if (typesList.isEmpty()) {
        EmptyText()
      } else {
        typesList.forEach {
          TypesCard(it)
        }
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
private fun TypeFilterColumn(modifier: Modifier, typeList: List<Type>) {
  Column(modifier) {
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
      if (typeList.isEmpty()) {
        EmptyText()
      } else {
        typeList.forEach {
          TypeCard(
            typeName = it.getName(LocalContext.current),
            modifier = Modifier.padding(4.dp),
            color = it.endColor
          )
        }
      }
    }
  }
}

@Composable
private fun TypeCard(modifier: Modifier = Modifier, typeName: String = "草", color: Color) {
  Box(
    modifier
      .fillMaxWidth()
      .height(20.dp)
      .clip(RoundedCornerShape(12.dp))
      .background(color)
  ) {
    Text(
      text = typeName,
      Modifier.align(Alignment.Center),
      color = Color.White,
      style = MaterialTheme.typography.body2
    )
  }
}

@Composable
private fun TypesCard(modifier: Modifier = Modifier, typeName: String = "草", colors: List<Color>) {
  Box(
    modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(12.dp))
      .background(brush = Brush.linearGradient(colors))
  ) {
    Text(
      text = typeName,
      Modifier.align(Alignment.Center),
      color = Color.White,
      style = MaterialTheme.typography.body2
    )
  }
}

@Composable
private fun EmptyText() {
  TypeCard(
    typeName = "None",
    modifier = Modifier.padding(4.dp),
    color = Color.Black
  )
}

@ExperimentalCoilApi
@ExperimentalMaterialApi
@Preview
@Composable
fun TypeDetailScreenPreView() {
  TypeDetailScreen()
}
