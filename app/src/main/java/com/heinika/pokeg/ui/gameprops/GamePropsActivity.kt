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
import androidx.compose.ui.Alignment
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
import com.heinika.pokeg.ui.gameprops.props.CandyProp
import com.heinika.pokeg.ui.gameprops.props.TCGProp
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
          val carry = 0
          val evolution = 1
          val pokeball = 2
          val berry = 3
          val important = 4
          val precious = 5
          val candy = 6
          val swap = 7
          val recovery = 8
          val battle = 9
          val food = 10
          val z = 11
          val mail = 12
          val apricorn = 13
          val tcg = 14
          val roto = 15
          Column(modifier = Modifier.padding(top = Dp(SystemBar.statusBarHeightDp))) {
            val tab1 = stringResource(R.string.game_props_carry)
            val tab2 = stringResource(R.string.game_props_evolution)
            val tab3 = stringResource(R.string.game_props_pokeball)
            val tab4 = stringResource(R.string.game_props_berry)
            val tab5 = stringResource(R.string.game_props_important)
            val tab6 = stringResource(R.string.game_props_candy)
            val tab7 = stringResource(R.string.game_props_precious)
            val tab8 = stringResource(R.string.game_props_swap)
            val tab9 = stringResource(R.string.game_props_recovery)
            val tab10 = stringResource(R.string.game_props_battle)
            val tab11 = stringResource(R.string.game_props_food)
            val tab12 = stringResource(R.string.game_props_z)
            val tab13 = stringResource(R.string.game_props_mail)
            val tab14 = stringResource(R.string.game_props_apricorn)
            val tab15 = stringResource(R.string.game_props_tcg)
            val tab16 = stringResource(R.string.game_props_roto)
            val tabTitles =
              listOf(tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8, tab9, tab10, tab11, tab12, tab13, tab14, tab15,tab16)

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
                .fillMaxWidth()
              ) { pageIndex ->
              when (pageIndex) {
                carry -> CarryPropsPage()
                evolution -> EvolutionPropsColumn()
                pokeball -> PokeballPropsColumn()
                berry -> BerryPropsPage()
                important -> ImportantPropsPage()
                precious -> PreciousPropsPage()
                candy -> CandyPropsColumn()
                swap -> SwapPropsColumn()
                recovery -> RecoveryPropsColumn()
                battle -> BattlePropsColumn()
                food -> FoodPropsColumn()
                z -> ZPropsPage()
                mail -> MailPropsPage()
                apricorn -> ApricornPropsColumn()
                tcg -> TCGPropsColumn()
                roto -> RotoPropsColumn()
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