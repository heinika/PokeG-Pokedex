package com.heinika.pokeg.ui.compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.heinika.pokeg.info.MoveVersion
import com.heinika.pokeg.info.Nature
import com.heinika.pokeg.info.Version

@ExperimentalMaterialApi
@Composable
fun SelectVersionDialog(
  moveVersionList: List<MoveVersion>,
  modifier: Modifier = Modifier,
  dialogState: Boolean = false,
  onDialogStateChange: ((Boolean) -> Unit)? = null,
  onDismissRequest: (() -> Unit)? = null,
  onVersionItemClick: (MoveVersion) -> Unit
) {
  if (dialogState) {
    AlertDialog(
      onDismissRequest = {
        onDialogStateChange?.invoke(false)
        onDismissRequest?.invoke()
      },
      shape = MaterialTheme.shapes.small,
      properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true),
      backgroundColor = Color.DarkGray,
      modifier = modifier,
      title = null,
      text = null,
      buttons = {
        LazyColumn(modifier = Modifier.padding(0.dp, 2.dp)) {
          items(moveVersionList) {
            Card(
              modifier = Modifier
                .width(250.dp)
                .height(48.dp)
                .padding(4.dp, 2.dp),
              shape = MaterialTheme.shapes.small,
              onClick = {
                onVersionItemClick(it)
              }
            ) {
              Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(id = it.stringId))
              }
            }
          }
        }
      }
    )
  }
}