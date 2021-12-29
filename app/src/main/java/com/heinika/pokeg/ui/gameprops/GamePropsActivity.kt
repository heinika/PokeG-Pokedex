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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.heinika.pokeg.R
import com.heinika.pokeg.ui.gameprops.compose.EvolutionPropsColumn
import com.heinika.pokeg.ui.gameprops.compose.FossilPropsColumn
import com.heinika.pokeg.ui.gameprops.compose.PokeballPropsColumn
import com.heinika.pokeg.ui.gameprops.compose.SwapPropsColumn
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
            val tab1Title = stringResource(R.string.game_props_evolution)
            val tab2Title = stringResource(R.string.game_props_pokeball)
            val tab3Title = stringResource(R.string.game_props_fossil)
            val tab4Title = stringResource(R.string.game_props_swap)
            val tabTitles = listOf(tab1Title, tab2Title, tab3Title,tab4Title)

            val coroutineScope = rememberCoroutineScope()
            val pagerState = rememberPagerState()

            TabRow(
              selectedTabIndex = pagerState.currentPage,
              backgroundColor = MaterialTheme.colors.background
            ) {
              tabTitles.forEachIndexed { index, title ->
                Tab(
                  text = { Text(title, fontSize = 18.sp, textAlign = TextAlign.Start) },
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
                0 -> EvolutionPropsColumn()
                1 -> PokeballPropsColumn()
                2 -> FossilPropsColumn()
                3 -> SwapPropsColumn()
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