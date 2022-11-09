package com.heinika.pokeg.module.ability

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.heinika.pokeg.info.Ability
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.SystemBar
import com.heinika.pokeg.utils.formatId


@Composable
fun AbilitiesColumn(onItemClick: (abilityId: Int) -> Unit) {
  LazyColumn {
    item {
      Spacer(modifier = Modifier.height(SystemBar.statusBarHeightDp.dp))
    }
    items(Ability.values()) {
      AbilityCard(ability = it, onItemClick)
    }
  }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AbilityCard(ability: Ability, onItemClick: (abilityId: Int) -> Unit) {
  Card(
    onClick = { onItemClick(ability.id) },
    modifier = Modifier
      .fillMaxWidth()
      .padding(12.dp)
  ) {
    Column(Modifier.padding(12.dp)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = stringResource(id = ability.nameResId), style = MaterialTheme.typography.titleMedium, modifier = Modifier.weight(1f))
        Text(text = ability.id.formatId, style = MaterialTheme.typography.titleSmall)
      }
      Text(text = stringResource(id = ability.flavorResId), style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(top = 12.dp))
    }
  }
}

@Preview(locale = "zh")
@Composable
fun AbilityCardPreView() {
  PokeGTheme() {
    AbilityCard(ability = Ability.Ability1, onItemClick = {})
  }
}

@Preview(locale = "zh")
@Composable
fun AbilitiesColumnPreView() {
  PokeGTheme() {
    AbilitiesColumn({})
  }
}