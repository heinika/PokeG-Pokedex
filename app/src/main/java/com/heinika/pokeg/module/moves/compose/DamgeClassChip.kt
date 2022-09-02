package com.heinika.pokeg.module.moves.compose

import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.info.MoveProp
import com.heinika.pokeg.ui.compose.BaseImageChip


@ExperimentalMaterialApi
@Composable
fun DamageClassChip(
  isSelected: Boolean,
  modifier: Modifier = Modifier,
  damageClass: MoveProp.DamageClass,
  onClick: (isSelected: Boolean) -> Unit
) {
  BaseImageChip(
    isSelected,
    damageClass.color,
    modifier.width(98.dp),
    onClick = {
      onClick(isSelected)
    },
    stringResource(id = damageClass.resStringId),
    painterResource(id = damageClass.resDrawableId)
  )
}


@ExperimentalMaterialApi
@Composable
fun DamageClassClipRow(
  modifier: Modifier,
  onDamageClassSelectChange: (MoveProp.DamageClass?) -> Unit
) {
  val isSelectedList = remember { mutableStateListOf(false, false, false) }
  Row(modifier = modifier) {
    MoveProp.DamageClass.values().forEach { damageClass ->
      DamageClassChip(damageClass = damageClass, isSelected = isSelectedList[damageClass.ordinal], onClick = {
        if (isSelectedList[damageClass.ordinal]){
          isSelectedList[damageClass.ordinal] = false
          onDamageClassSelectChange(null)
        }else{
          for (index in isSelectedList.indices){
            isSelectedList[index] = index == damageClass.ordinal
          }
          onDamageClassSelectChange(damageClass)
        }
      })
      Spacer(modifier = Modifier.width(8.dp))
    }
  }
}

@Preview
@ExperimentalMaterialApi
@Composable
fun DamageClassClipRowPreview() {
  DamageClassClipRow(onDamageClassSelectChange = {}, modifier = Modifier.padding(16.dp, 0.dp))
}


@ExperimentalMaterialApi
@Composable
@Preview(locale = "zh")
fun DamageClassChipPreview() {
  var isSelected by remember { mutableStateOf(false) }
  PokeGTheme {
    Surface {
      DamageClassChip(
        isSelected = isSelected,
        damageClass = MoveProp.DamageClass.Special,
        onClick = {
          isSelected = !isSelected
        })
    }
  }
}