package com.heinika.pokeg.module.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.R
import com.heinika.pokeg.utils.SystemBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(onBack: () -> Unit) {
  Scaffold(
    topBar = {
      CenterAlignedTopAppBar(
        modifier = Modifier.padding(top = SystemBar.statusBarHeightDp.dp),
        title = {
          Text(
            stringResource(id = R.string.app_name),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        },
        navigationIcon = {
          IconButton(onClick = { onBack() }) {
            Icon(
              imageVector = Icons.Filled.ArrowBack,
              contentDescription = "Localized description"
            )
          }
        },
        actions = {
          IconButton(onClick = { /* doSomething() */ }) {
            Icon(
              imageVector = Icons.Filled.Favorite,
              contentDescription = "Localized description"
            )
          }
        }
      )
    },
    content = { innerPadding ->
      LazyColumn(
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        val list = (0..75).map { it.toString() }
        items(count = list.size) {
          Text(
            text = list[it],
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 16.dp)
          )
        }
      }
    }
  )
}

@Preview
@Composable
fun AboutPreview() {
  AboutScreen(onBack = {})
}