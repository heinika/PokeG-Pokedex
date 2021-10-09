package com.heinika.pokeg.ui.main

import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import com.drakeet.drawer.FullDraggableContainer
import com.drakeet.multitype.MultiTypeAdapter
import com.heinika.pokeg.base.BasePage
import com.heinika.pokeg.repository.res.PokemonRes
import com.heinika.pokeg.ui.detail.DetailPage
import com.heinika.pokeg.ui.main.itemdelegate.BottomItemDelegate
import com.heinika.pokeg.ui.main.itemdelegate.HeaderItemDelegate
import com.heinika.pokeg.ui.main.itemdelegate.PokemonItemDelegate
import com.heinika.pokeg.ui.main.itemdelegate.model.BottomItem
import com.heinika.pokeg.ui.main.itemdelegate.model.Header
import com.heinika.pokeg.ui.main.layout.LeftDrawerView
import com.heinika.pokeg.ui.main.layout.MainPageView
import com.heinika.pokeg.utils.AdapterDiffUtils
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*


@Suppress("UNCHECKED_CAST")
class MainPage(
  private val activity: AppCompatActivity,
  pageStack: Stack<BasePage>,
  private val pokemonRes: PokemonRes
) :
  BasePage(activity, pageStack) {
  private val mainViewModel: MainViewModel by activity.viewModels()

  private var adapter: MultiTypeAdapter = MultiTypeAdapter()

  private val mainPageView = MainPageView(activity)

  override fun showPage() {
    super.showPage()
    val drawerLayout = DrawerLayout(activity).apply {
      addView(FullDraggableContainer(activity).apply { addView(mainPageView) })
      addView(LeftDrawerView(activity).apply {
        layoutParams = DrawerLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT).apply {
          gravity = GravityCompat.START
          fitsSystemWindows = false
        }
      })
    }
    content.addView(drawerLayout)

    adapter.register(HeaderItemDelegate {
      drawerLayout.openDrawer(GravityCompat.START,true)
    })
    adapter.register(BottomItemDelegate())
    adapter.register(PokemonItemDelegate(pokemonRes, onItemClick = { imageView, pokemon ->
      DetailPage(pokemonRes, activity, pokemon, imageView, pageStack).also {
        it.showPage()
      }
    }))

    mainPageView.recyclerView.adapter = adapter
    mainPageView.onSelectedChange = {
      mainViewModel.filterTypeList = it
      mainViewModel.startSortAndFilter()
    }

    mainPageView.onSearchTextChange = { searchText ->
      Timber.i("onSearchTextChange $searchText")
      mainViewModel.setSearchText(searchText)
    }

    mainViewModel.searchText.observe(activity) { searchText ->
      Timber.i("observe $searchText")
    }

    val header = Header("图鉴")
    activity.lifecycleScope.launch {
      mainViewModel.pokemonSortListStateFlow.collect { pokemonList ->
        val itemList = arrayListOf<Any>(header) + pokemonList + BottomItem("没有了")
        val diffResult = DiffUtil.calculateDiff(AdapterDiffUtils(adapter.items, itemList), true)
        adapter.items = itemList
        diffResult.dispatchUpdatesTo(adapter)
      }
    }


    mainViewModel.toastMessage.observe(activity, { toastMessage ->
      Toast.makeText(activity, toastMessage, Toast.LENGTH_LONG).show()
    })

    mainViewModel.isLoading.observe(activity, { isLoading ->
      mainPageView.progressBar.isVisible = isLoading
    })

    mainPageView.setOnSearchClickListener {
      mainPageView.showSearchBar()
    }

    mainPageView.setOnFilterClickListener {
      mainPageView.showFilterListView()
      mainPageView.hideBottomFilterBar()
    }
  }

  override fun onBackPressed() {
    Timber.i("onBackPressed ${mainPageView.canScrollUp()}")
    if (mainPageView.canScrollUp()) {
      mainPageView.scrollToTop()
    } else {
      if (mainPageView.isShowFilterList) {
        mainPageView.hideTopFilterListView()
        mainPageView.showBottomFilterView()
      } else {
        activity.finish()
      }
    }
  }
}