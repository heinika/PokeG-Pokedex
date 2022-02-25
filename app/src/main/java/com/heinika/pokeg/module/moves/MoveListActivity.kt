package com.heinika.pokeg.module.moves

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.heinika.pokeg.R
import com.heinika.pokeg.module.moves.compose.DrawerContent
import com.heinika.pokeg.module.moves.compose.MoveCard
import com.heinika.pokeg.ui.theme.PokeGTheme
import com.heinika.pokeg.utils.SystemBar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@ExperimentalMaterialApi

@AndroidEntryPoint
class MoveListActivity : ComponentActivity() {
  private val moveListViewModel: MoveListViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    SystemBar.initStatusBarHeight(this)

    setContent {
      PokeGTheme {
        ProvideWindowInsets {
          // A surface container using the 'background' color from the theme
          val systemUiController = rememberSystemUiController()
          systemUiController.setSystemBarsColor(Color.Transparent)
          systemUiController.setStatusBarColor(Color.Transparent)
          Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
          ) {
            val drawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)

            BottomDrawer(
              drawerContent = {
                DrawerContent(
                  onSelectedTypeChange = {
                    moveListViewModel.filterTypes(it)
                  },
                  onSelectedGenerationChange = {
                    moveListViewModel.filterGenerations(it)
                  },
                  onMoveOrderChange = {
                    moveListViewModel.changeSortOrder(it)
                  },
                  onTypeSortClick = { type, state ->
                    moveListViewModel.changSortState(type, state)
                  },
                  onDamageClassSelectChange = {
                    moveListViewModel.filterDamageClass(it)
                  }
                )
              },
              gesturesEnabled = drawerState.isOpen,
              drawerState = drawerState
            ) {
              val moves = remember { moveListViewModel.allMovesState }
              ConstraintLayout(modifier = Modifier.fillMaxSize()) {
                val (moveList, filterButton) = createRefs()

                val listState = rememberLazyListState()

                LazyColumn(Modifier.constrainAs(moveList) {
                  top.linkTo(parent.top)
                  start.linkTo(parent.start)
                }, state = listState) {
                  itemsIndexed(moves.value) { index, move ->
                    if (index == 0) {
                      Spacer(modifier = Modifier.padding(top = Dp(SystemBar.statusBarHeightDp)))
                      MoveCard(move, onClick = {})
                    } else {
                      MoveCard(move, onClick = {})
                    }
                  }
                }

                AnimatedVisibility(
                  visible = !listState.isScrollInProgress,
                  enter = fadeIn() + slideIn(initialOffset = {
                    IntOffset(
                      0,
                      SystemBar.statusBarHeightDp.toInt()
                    )
                  }),
                  exit = slideOut(targetOffset = {
                    IntOffset(
                      0,
                      SystemBar.statusBarHeightDp.toInt()
                    )
                  }) + fadeOut(),
                  modifier = Modifier.constrainAs(filterButton) {
                    bottom.linkTo(parent.bottom, Dp(SystemBar.navigationBarHeightDp))
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                  }) {
                  val scope = rememberCoroutineScope()
                  FloatingActionButton(
                    onClick = {
                      scope.launch {
                        if (drawerState.isOpen) {
                          drawerState.close()
                        } else {
                          drawerState.open()
                        }
                      }
                    }
                  ) {
                    Image(
                      painter = painterResource(id = R.drawable.ic_filter_list),
                      contentDescription = ""
                    )
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}

