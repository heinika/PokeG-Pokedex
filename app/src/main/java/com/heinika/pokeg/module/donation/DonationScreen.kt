package com.heinika.pokeg.module.donation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.R
import com.heinika.pokeg.utils.SystemBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonationScreen(onBack: () -> Unit) {
  Column(Modifier.background(MaterialTheme.colorScheme.background)) {
    SmallTopAppBar(
      navigationIcon = {
        IconButton(onClick = {
          onBack()
        }) {
          Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
        }
      },
      modifier = Modifier.padding(top = SystemBar.statusBarHeightDp.dp, start = 12.dp, end = 12.dp),
      title = { Text(text = stringResource(id = R.string.donation)) })
    Column(
      modifier = Modifier
        .fillMaxSize(),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.SpaceEvenly
    ) {
      Column {
        Text(text = "微信支付", color = MaterialTheme.colorScheme.onBackground)
        Image(painter = painterResource(id = R.drawable.wechat), contentDescription = "", modifier = Modifier.size(250.dp))
      }

      Column {
        Text(text = "支付宝", color = MaterialTheme.colorScheme.onBackground)
        Image(painter = painterResource(id = R.drawable.alipay), contentDescription = "", modifier = Modifier.size(250.dp))
      }
    }
  }
}

@Preview
@Composable
fun DonationScreenPreview() {
  DonationScreen({})
}