package com.heinika.pokeg.module.team.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.flowlayout.FlowColumn

@Composable
fun AddTeamDialog() {
  var teamName by remember {
    mutableStateOf("")
  }
  AlertDialog(onDismissRequest = { /*TODO*/ }, buttons = {
    Column {
      TextField(value = teamName, onValueChange = {teamName = it})
      FlowColumn {

      }
    }
  })
}


@Preview
@Composable
fun AddTeamDialogPreview() {
  AddTeamDialog()
}