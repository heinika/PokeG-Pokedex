package com.heinika.pokeg.module.mypokemon.compose

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import coil.annotation.ExperimentalCoilApi
import com.heinika.pokeg.info.Nature
import com.heinika.pokeg.module.nature.compose.NatureCard

@ExperimentalMaterialApi
@ExperimentalCoilApi
@Composable
fun SelectNatureDialog(
  modifier: Modifier = Modifier,
  dialogState: Boolean = false,
  onDialogStateChange: ((Boolean) -> Unit)? = null,
  onDismissRequest: (() -> Unit)? = null,
  onNatureItemClick: (Nature) -> Unit
) {
  val dialogShape = RoundedCornerShape(16.dp)

  if (dialogState) {
    AlertDialog(
      onDismissRequest = {
        onDialogStateChange?.invoke(false)
        onDismissRequest?.invoke()
      },
      backgroundColor = Color.DarkGray,
      title = null,
      text = null,
      buttons = {
        LazyColumn {
          items(Nature.values()) {
            NatureCard(it) {
              onNatureItemClick(it)
            }
          }
        }
      },
      properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
      modifier = modifier,
      shape = dialogShape
    )
  }
}


