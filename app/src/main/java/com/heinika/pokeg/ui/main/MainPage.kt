package com.heinika.pokeg.ui.main

import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import com.drakeet.multitype.MultiTypeAdapter
import com.heinika.pokeg.base.BasePage
import com.heinika.pokeg.ui.detail.DetailPage
import com.heinika.pokeg.ui.main.itemdelegate.HeaderItemDelegate
import com.heinika.pokeg.ui.main.itemdelegate.PokemonItemDelegate
import com.heinika.pokeg.ui.main.itemdelegate.model.Header
import com.heinika.pokeg.ui.main.layout.MainPageView
import com.heinika.pokeg.utils.AdapterDiffUtils
import com.heinika.pokeg.utils.PokemonRes
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
    content.addView(mainPageView)

    adapter.register(HeaderItemDelegate())
    adapter.register(PokemonItemDelegate(pokemonRes, onItemClick = { imageView, pokemon ->
      DetailPage(pokemonRes, activity, pokemon, imageView, pageStack).also {
        it.showPage()
      }
    }))

    mainPageView.recyclerView.adapter = adapter

    val header = Header("图鉴")
    activity.lifecycleScope.launch {
      mainViewModel.pokemonSortListStateFlow.collect { pokemonList ->
        val itemList = arrayListOf<Any>(header) + pokemonList
        val diffResult = DiffUtil.calculateDiff(AdapterDiffUtils(adapter.items, itemList), true)
        adapter.items = itemList
        diffResult.dispatchUpdatesTo(adapter)
      }
//        { pokemonList ->
//          val itemList = arrayListOf<Any>(header) + pokemonList
//          val diffResult = DiffUtil.calculateDiff(AdapterDiffUtils(adapter.items, itemList), true)
//          adapter.items = itemList
//          diffResult.dispatchUpdatesTo(adapter)
//        }
    }


    mainViewModel.toastMessage.observe(activity, { toastMessage ->
      Toast.makeText(activity, toastMessage, Toast.LENGTH_LONG).show()
    })

    mainViewModel.isLoading.observe(activity, { isLoading ->
      mainPageView.progressBar.isVisible = isLoading
    })

    mainPageView.setOnSearchClickListener{
      mainPageView.showSearchBar()
//      mainViewModel.filterType1 = PokemonProp.Type.DRAGON
//      mainViewModel.filterType2 = PokemonProp.Type.ICE
//      mainViewModel.startSortAndFilter()
    }
  }

  override fun onBackPressed() {
    Timber.i("onBackPressed ${mainPageView.canScrollUp()}")
    if (mainPageView.canScrollUp()) {
      mainPageView.scrollToTop()
    } else {
      activity.finish()
    }
  }
}