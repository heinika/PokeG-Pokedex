package com.heinika.pokeg.module.main

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DiffUtil
import com.drakeet.drawer.FullDraggableContainer
import com.drakeet.multitype.MultiTypeAdapter
import com.heinika.pokeg.ConfigMMKV.favoritePokemons
import com.heinika.pokeg.PokemonDataCache.pokemonList
import com.heinika.pokeg.base.BasePage
import com.heinika.pokeg.info.DexType
import com.heinika.pokeg.repository.res.PokemonRes
import com.heinika.pokeg.module.detail.DetailPage
import com.heinika.pokeg.module.main.itemdelegate.BottomItemDelegate
import com.heinika.pokeg.module.main.itemdelegate.HeaderItemDelegate
import com.heinika.pokeg.module.main.itemdelegate.PokemonItemDelegate
import com.heinika.pokeg.module.main.itemdelegate.model.BottomItem
import com.heinika.pokeg.module.main.itemdelegate.model.Header
import com.heinika.pokeg.module.main.layout.LeftDrawerView
import com.heinika.pokeg.module.main.layout.MainPageView
import com.heinika.pokeg.module.main.layout.RightDrawerView
import com.heinika.pokeg.utils.AdapterDiffUtils
import com.heinika.pokeg.utils.dp
import timber.log.Timber
import java.util.*


@Suppress("UNCHECKED_CAST")
@ExperimentalMaterialApi
@ExperimentalAnimationApi
class MainPage(
  private val activity: ComponentActivity,
  pageStack: Stack<BasePage>,
  private val pokemonRes: PokemonRes
) :
  BasePage(activity, pageStack) {
  private val mainViewModel: MainViewModel by activity.viewModels()

  private var adapter: MultiTypeAdapter = MultiTypeAdapter()

  private val mainPageView = MainPageView(activity)

  private val rightDrawerView = RightDrawerView(activity).apply {
    layoutParams = DrawerLayout.LayoutParams(316.dp, MATCH_PARENT).apply {
      gravity = GravityCompat.END
      fitsSystemWindows = true
    }
  }


  private val leftDrawerView = LeftDrawerView(activity).apply {
    layoutParams = DrawerLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT).apply {
      gravity = GravityCompat.START
      fitsSystemWindows = true
    }
  }

  private val drawerLayout = DrawerLayout(activity).apply {
    layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
    addView(FullDraggableContainer(activity).apply { addView(mainPageView) })
    addView(leftDrawerView)
    addView(rightDrawerView)
  }


  override fun showPage() {
    super.showPage()

    content.addView(drawerLayout)


//    content.addView(TypeClipListView(activity).apply {
//      ViewTreeLifecycleOwner.set(this, activity)
//    })

    adapter.register(HeaderItemDelegate(onSettingClick = {
      drawerLayout.openDrawer(GravityCompat.START, true)
    }, onLoopClick = {dexType ->
      mainViewModel.changDexType(dexType)
    }))
    adapter.register(BottomItemDelegate())
    adapter.register(PokemonItemDelegate(pokemonRes, onItemClick = { imageView, pokemon ->
      DetailPage(pokemonRes, activity, pokemon, imageView, pageStack).also {
        it.showPage()
      }
    }, onFavoriteClick = { pokemon, isChecked ->
      favoritePokemons = if (isChecked) {
        favoritePokemons + pokemon.id.toString()
      } else {
        favoritePokemons - pokemon.id.toString()
      }
    }))

    mainPageView.recyclerView.adapter = adapter
    rightDrawerView.typesFilterView.onSelectedChange = { list ->
      mainViewModel.filterTypeList = list.map { it.typeId }
    }

    rightDrawerView.onBaseStatusCheckedListChange = {
      mainViewModel.changeSortBaseStatusList(it)
    }
    rightDrawerView.onBaseStatusFilterViewClick = {
      mainViewModel.startSortAndFilter()
    }

    rightDrawerView.onBodyStatusSelectChange = {
      mainViewModel.changeBodyStatus(it)
    }
    rightDrawerView.onBodyStatusRadioButtonClick = {
      mainViewModel.startSortAndFilter()
    }

    rightDrawerView.onGenerationListChange = {
      mainViewModel.changeGenerations(it)
    }

    rightDrawerView.onTagCheckedListChange = {
      mainViewModel.changeTags(it)
    }

    mainPageView.onSearchTextChange = { searchText ->
      Timber.i("onSearchTextChange $searchText")
      mainViewModel.setSearchText(searchText)
    }

    mainViewModel.searchText.observe(activity) { searchText ->
      Timber.i("observe $searchText")
    }

    mainViewModel.filterGenerations.observe(activity) {
      rightDrawerView.setGenerationTitleDataList(it)
    }

    mainViewModel.sortBaseStatusList.observe(activity) {
      rightDrawerView.setBaseStatusTitleDataList(
        it,
        mainViewModel.selectedBodyStatus.value,
        isDesc = mainViewModel.isSortDesc.value!!
      )
    }

    mainViewModel.selectedBodyStatus.observe(activity) {
      rightDrawerView.setBodyStatus(it, isDesc = mainViewModel.isSortDesc.value!!)
    }

    mainViewModel.isSortDesc.observe(activity) {
      rightDrawerView.setBaseStatusTitleDataList(
        mainViewModel.sortBaseStatusList.value!!,
        mainViewModel.selectedBodyStatus.value,
        isDesc = it
      )
    }

    rightDrawerView.onBaseStatusTitleClick = {
      mainViewModel.changeSortOrder()
    }

    val header = Header("全国图鉴")

    mainViewModel.pokemonSortListLiveData.observe(activity) { pokemonList ->
      Timber.i("pokemonList size : ${pokemonList.size}")
      val itemList = arrayListOf<Any>(header) + pokemonList + BottomItem("没有了")
      val diffResult = DiffUtil.calculateDiff(AdapterDiffUtils(adapter.items, itemList), true)
      adapter.items = itemList
      diffResult.dispatchUpdatesTo(adapter)
    }

    mainViewModel.onRefreshFavorite = {
      adapter.notifyItemChanged(adapter.items.indexOf(it))
    }


    mainViewModel.toastMessage.observe(activity) { toastMessage ->
      Toast.makeText(activity, toastMessage, Toast.LENGTH_LONG).show()
    }

    mainViewModel.isLoading.observe(activity) { isLoading ->
      mainPageView.progressBar.isVisible = isLoading
    }

    mainPageView.setOnSearchClickListener {
      mainPageView.showSearchBar()
    }

    mainPageView.setOnTopButtonClickListener {
      mainPageView.scrollToTop()
    }

    mainPageView.setOnFilterClickListener {
      drawerLayout.openDrawer(GravityCompat.END, true)
    }
  }

  override fun onBackPressed() {
    Timber.i("onBackPressed ${mainPageView.canScrollUp()}")
    if (mainPageView.canScrollUp()) {
      mainPageView.scrollToTop()
    } else {
      if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
        drawerLayout.closeDrawer(GravityCompat.END, true)
        mainPageView.showBottomFilterView()
      } else {
        activity.finish()
      }
    }
  }
}