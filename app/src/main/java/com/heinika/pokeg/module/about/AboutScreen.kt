package com.heinika.pokeg.module.about

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.BuildConfig
import com.heinika.pokeg.R
import com.heinika.pokeg.utils.SystemBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(onBack: () -> Unit) {
  Scaffold(
    topBar = {
      SmallTopAppBar(
        modifier = Modifier.padding(top = SystemBar.statusBarHeightDp.dp, start = 12.dp, end = 12.dp),
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
        },
        scrollBehavior = null
      )
    },
    content = { innerPadding ->

      Column(
        Modifier
          .fillMaxWidth()
          .padding(innerPadding)
          .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,

      ) {
        Image(
          painter = painterResource(id = R.drawable.ic_launcher_foreground),
          contentDescription = "",
          modifier = Modifier
            .size(188.dp)
            .padding(24.dp)
        )
        Text(text = "PokeG By heinika")
        Text(text = "v${BuildConfig.VERSION_NAME}")

        Card(
          modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
        ) {
          Text(text = "介绍与帮助", modifier = Modifier.padding(12.dp))
          Text(text = stringResource(id = R.string.app_desc), modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp))
        }

        val context = LocalContext.current
        Card(
          modifier = Modifier
            .fillMaxWidth()
            .height(108.dp)
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
          onClick = {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://weibo.com/u/6222257860")
            try {
              context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
              e.printStackTrace()
            }
          }
        ) {
          Row {
            Image(painter = painterResource(id = R.drawable.about_avatar), contentDescription = "")
            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
              Text(text = "乌班图说", modifier = Modifier.padding(12.dp), style = MaterialTheme.typography.titleMedium)
              Text(
                text = "Developer & 好奇猫",
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                style = MaterialTheme.typography.bodyMedium
              )
            }
          }
        }

        Card(
          modifier = Modifier
            .fillMaxWidth()
            .height(108.dp)
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
          onClick = {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://heinika.github.io")
            try {
              context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
              e.printStackTrace()
            }
          }
        ) {
          Row {
            Image(painter = painterResource(id = R.drawable.chenlijin), contentDescription = "")
            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
              Text(text = "Developer", modifier = Modifier.padding(12.dp), style = MaterialTheme.typography.titleMedium)
              Text(
                text = "Heinika",
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                style = MaterialTheme.typography.bodyMedium
              )
            }
          }
        }

        Card(
          modifier = Modifier
            .fillMaxWidth()
            .height(108.dp)
            .padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
        ) {
          Row {
            Image(painter = painterResource(id = R.drawable.kabi), contentDescription = "")
            Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
              Text(text = "卡比兽", modifier = Modifier.padding(12.dp), style = MaterialTheme.typography.titleMedium)
              Text(
                text = "陪睡员",
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
                style = MaterialTheme.typography.bodyMedium
              )
            }
          }
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