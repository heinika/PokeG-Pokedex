package com.heinika.pokeg.ui.main

import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.drakeet.multitype.MultiTypeAdapter
import com.heinika.pokeg.base.BasePage
import com.heinika.pokeg.ui.detail.DetailPage
import com.heinika.pokeg.ui.itemdelegate.PokemonItemDelegate
import com.heinika.pokeg.utils.RecyclerViewPaginator
import timber.log.Timber
import java.util.*

class MainPage(private val activity: AppCompatActivity, pageStack: Stack<BasePage>) :
  BasePage(activity, pageStack) {
  private val mainViewModel: MainViewModel by activity.viewModels()

  private var adapter: MultiTypeAdapter = MultiTypeAdapter()

  private val mainPageView = MainPageView(activity)

  override fun showPage() {
    super.showPage()
    content.addView(mainPageView)

    adapter.register(PokemonItemDelegate(onItemClick = { imageView, pokemon ->
      DetailPage(activity, pokemon, imageView,pageStack).also {
        it.showPage()
      }
    }))
    val layoutManager = GridLayoutManager(activity, 2)
    mainPageView.recyclerView.let {
      it.layoutManager = layoutManager
      it.adapter = adapter
    }

    mainViewModel.pokemonListLiveData.observe(activity, {
      Timber.d("${it.size}")
      adapter.items = it
      adapter.notifyItemRangeChanged(0, it.size - 1)
    })

    mainViewModel.toastMessage.observe(activity, { toastMessage ->
      Toast.makeText(activity, toastMessage, Toast.LENGTH_LONG).show()
    })

    mainViewModel.isLoading.observe(activity, { isLoading ->
      mainPageView.progressBar.isVisible = isLoading
    })

    RecyclerViewPaginator(
      recyclerView = mainPageView.recyclerView,
      isLoading = { if (mainViewModel.isLoading.value == null) true else mainViewModel.isLoading.value!! },
      loadMore = { mainViewModel.fetchNextPokemonList() },
      onLast = { false }
    ).run {
      threshold = 8
    }
  }
}