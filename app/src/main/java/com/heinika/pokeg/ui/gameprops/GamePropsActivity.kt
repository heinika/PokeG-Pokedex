package com.heinika.pokeg.ui.gameprops

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.heinika.pokeg.R
import com.heinika.pokeg.ui.gameprops.compose.*
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.SystemBar
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@ExperimentalMaterialApi
class GamePropsActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    SystemBar.initStatusBarHeight(this)

    setContent {
      PokeGTheme {
        Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxHeight()) {
          Column(modifier = Modifier.padding(top = Dp(SystemBar.statusBarHeightDp))) {
            val tab1Title = stringResource(R.string.game_props_carry)
            val tab2Title = stringResource(R.string.game_props_evolution)
            val tab3Title = stringResource(R.string.game_props_pokeball)
            val tab4Title = stringResource(R.string.game_props_fossil)
            val tab5Title = stringResource(R.string.game_props_swap)
            val tab6Title = stringResource(R.string.game_props_apricorn)
            val tab7Title = stringResource(R.string.game_props_recovery)
            val tab8Title = stringResource(R.string.game_props_battle)
            val tab9Title = stringResource(R.string.game_props_mail)
            val tabTitles = listOf(tab1Title, tab2Title, tab3Title, tab4Title, tab5Title, tab6Title, tab7Title, tab8Title, tab9Title)

            val coroutineScope = rememberCoroutineScope()
            val pagerState = rememberPagerState()

            ScrollableTabRow(
              selectedTabIndex = pagerState.currentPage,
              backgroundColor = MaterialTheme.colors.background
            ) {
              tabTitles.forEachIndexed { index, title ->
                Tab(
                  text = { Text(title, fontSize = 16.sp, textAlign = TextAlign.Start, maxLines = 1, overflow = TextOverflow.Ellipsis) },
                  selected = pagerState.currentPage == index,
                  onClick = {
                    // Animate to the selected page when clicked
                    coroutineScope.launch {
                      pagerState.animateScrollToPage(index)
                    }
                  }
                )
              }
            }

            HorizontalPager(
              count = tabTitles.size, state = pagerState,
              modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),

              ) { pageIndex ->
              when (pageIndex) {
                0 -> CarryPropsPage()
                1 -> EvolutionPropsColumn()
                2 -> PokeballPropsColumn()
                3 -> FossilPropsColumn()
                4 -> SwapPropsColumn()
                5 -> ApricornPropsColumn()
                6 -> RecoveryPropsColumn()
                7 -> BattlePropsColumn()
                8 -> MailPropsPage()
                else -> {
                  Text(text = tabTitles[pageIndex])
                }
              }
            }
          }
        }
      }
    }
  }
}