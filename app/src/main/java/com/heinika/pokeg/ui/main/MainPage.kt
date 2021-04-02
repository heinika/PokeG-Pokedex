package com.heinika.pokeg.ui.main

import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import com.drakeet.multitype.MultiTypeAdapter
import com.heinika.pokeg.base.BasePage
import com.heinika.pokeg.ui.detail.DetailPage
import com.heinika.pokeg.ui.itemdelegate.HeaderItemDelegate
import com.heinika.pokeg.ui.itemdelegate.PokemonItemDelegate
import com.heinika.pokeg.ui.itemdelegate.model.Header
import com.heinika.pokeg.ui.main.layout.MainPageView
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

    adapter.register(HeaderItemDelegate())
    adapter.register(PokemonItemDelegate(activity, mainViewModel, onItemClick = { imageView, pokemon ->
      DetailPage(activity, pokemon, imageView, pageStack).also {
        it.showPage()
      }
    }))

    mainPageView.recyclerView.adapter = adapter

    val header = Header("图鉴")
    mainViewModel.pokemonListLiveData.observe(activity, {
      val itemList = arrayListOf<Any>(header) + it
      val diffResult = DiffUtil.calculateDiff(AdapterDiffUtils(adapter.items, itemList), true)
      adapter.items = itemList
      diffResult.dispatchUpdatesTo(adapter)
    })

    mainViewModel.toastMessage.observe(activity, { toastMessage ->
      Toast.makeText(activity, toastMessage, Toast.LENGTH_LONG).show()
    })

    mainViewModel.isLoading.observe(activity, { isLoading ->
      mainPageView.progressBar.isVisible = isLoading
    })

  }

  override fun onBackPressed() {
    Timber.i("onBackPressed ${mainPageView.canScrollUp()}")
    if (mainPageView.canScrollUp()){
      mainPageView.scrollToTop()
    }else{
      activity.finish()
    }
  }

  private class AdapterDiffUtils(val oldList: List<Any>, val newList: List<Any>) : DiffUtil.Callback() {
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