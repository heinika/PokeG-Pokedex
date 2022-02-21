package com.heinika.pokeg.module.mypokemon.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.heinika.pokeg.R
import com.heinika.pokeg.model.Pokemon

@Composable
fun AppDialog(
  modifier: Modifier = Modifier,
  dialogState: Boolean = false,
  onDialogPositiveButtonClicked: (() -> Unit)? = null,
  onDialogStateChange: ((Boolean) -> Unit)? = null,
  onDismissRequest: (() -> Unit)? = null,
  pokemonList: List<Pokemon>
) {
  val textPaddingAll = 24.dp
  val buttonPaddingAll = 8.dp
  val dialogShape = RoundedCornerShape(16.dp)

  if (dialogState) {
    AlertDialog(
      onDismissRequest = {
        onDialogStateChange?.invoke(false)
        onDismissRequest?.invoke()
      },
      title = null,
      text = null,
      buttons = {

        Column {
          TextField(modifier = Modifier
            .background(
              Color.White
            )
            .fillMaxWidth(),leadingIcon = {
            Image(imageVector = Icons.Default.Search, contentDescription = "")
          }, value = "梦幻",
            colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
            onValueChange = {})

          LazyColumn{
            items(pokemonList) {

            }
          }

          Image(
            painter = painterResource(R.drawable.meotwo),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
          )

          Divider(color = MaterialTheme.colors.onSurface, thickness = 1.dp)

          Row(
            modifier = Modifier.padding(all = buttonPaddingAll),
            horizontalArrangement = Arrangement.Center
          ) {
            TextButton(
              modifier = Modifier.fillMaxWidth(),
              onClick = {
                onDialogStateChange?.invoke(false)
                onDialogPositiveButtonClicked?.invoke()
              }
            ) {
              Text(text = stringResource(R.string.ok), color = MaterialTheme.colors.onSurface)
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

@Preview
@Composable
fun AppDialogPreview() {
//  AppDialog(dialogState = true)
}