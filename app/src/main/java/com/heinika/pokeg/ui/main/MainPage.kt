package com.heinika.pokeg.ui.main

import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import com.drakeet.multitype.MultiTypeAdapter
import com.heinika.pokeg.base.BasePage
import com.heinika.pokeg.model.Pokemon
import com.heinika.pokeg.ui.detail.DetailPage
import com.heinika.pokeg.ui.itemdelegate.PokemonItemDelegate
import com.heinika.pokeg.utils.RecyclerViewPaginator
import timber.log.Timber
import java.util.*


@Suppress("UNCHECKED_CAST")
class MainPage(private val activity: AppCompatActivity, pageStack: Stack<BasePage>) :
  BasePage(activity, pageStack) {
  private val mainViewModel: MainViewModel by activity.viewModels()

  private var adapter: MultiTypeAdapter = MultiTypeAdapter()

  private val mainPageView = MainPageView(activity)

  override fun showPage() {
    super.showPage()
    content.addView(mainPageView)

    adapter.register(PokemonItemDelegate(onItemClick = { imageView, pokemon ->
      DetailPage(activity, pokemon, imageView, pageStack).also {
        it.showPage()
      }
    }))

    mainPageView.recyclerView.adapter = adapter

    mainViewModel.pokemonListLiveData.observe(activity, {
      val diffResult = DiffUtil.calculateDiff(AdapterDiffUtils(adapter.items as List<Pokemon>,it),true)
      adapter.items = it
      diffResult.dispatchUpdatesTo(adapter)
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

  private class AdapterDiffUtils(val oldList:List<Pokemon>,val newList: List<Pokemon>): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return oldList[oldItemPosition]::class == newList[newItemPosition]::class
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return oldList[oldItemPosition] == newList[newItemPosition]
    }

  }
}