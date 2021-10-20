package com.heinika.pokeg.ui.main.layout

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.heinika.pokeg.R
import com.heinika.pokeg.base.CustomLayout
import com.heinika.pokeg.ui.main.itemdelegate.BaseStatusItemDelegate
import com.heinika.pokeg.ui.main.itemdelegate.GenerationItemDelegate
import com.heinika.pokeg.ui.main.itemdelegate.model.BaseStatusSelectItem
import com.heinika.pokeg.ui.main.itemdelegate.model.GenerationsSelectItem
import com.heinika.pokeg.utils.PokemonProp
import com.heinika.pokeg.utils.StatusBarHeight

class RightDrawerView(context: Context) : CustomLayout(context) {
  val typesFilterView = TypesFilterView(context).apply {
    layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    setPadding(0, StatusBarHeight.value, 0, 0)
    this@RightDrawerView.addView(this)
  }

  private val baseStatusTitle = TextView(context).apply {
    layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    text = "排序： 未选择"
    setTextColor(Color.WHITE)
    textSize = 20f
    setOnClickListener {
      onBaseStatusTitleClick?.invoke()
    }
    addView(this)
  }

  var onBaseStatusTitleClick: (() -> Unit)? = null

  private val baseStatusCheckedList = mutableListOf<PokemonProp.BaseStatus>()
  var onBaseStatusCheckedListChange: ((list: List<PokemonProp.BaseStatus>) -> Unit)? = null

  private val baseStatusFilterView = RecyclerView(context).apply {
    val list = mutableListOf<BaseStatusSelectItem>().apply {
      PokemonProp.BaseStatus.values().forEach {
        add(BaseStatusSelectItem(it) { baseStatus, isChecked ->
          if (isChecked) {
            baseStatusCheckedList.add(baseStatus)
          } else {
            baseStatusCheckedList.remove(baseStatus)
          }
          onBaseStatusCheckedListChange?.invoke(baseStatusCheckedList)
        })
      }
    }
    val multiTypeAdapter = MultiTypeAdapter(list).apply {
      register(BaseStatusItemDelegate())
    }

    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    adapter = multiTypeAdapter
    addItemDecoration(object : RecyclerView.ItemDecoration() {
      override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
      ) {
        outRect.set(0, 0, 8.dp, 0)
      }
    })

    this@RightDrawerView.addView(this)
  }

  private val generationsTitle = TextView(context).apply {
    layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    text = "世代：未选择"
    setTextColor(Color.WHITE)
    textSize = 20f
    setOnClickListener {
      onBaseStatusTitleClick?.invoke()
    }
    addView(this)
  }

  private val generationList = mutableListOf<PokemonProp.Generation>()
  var onGenerationListChange: ((list: List<PokemonProp.Generation>) -> Unit)? = null

  private val generationsFilterView = RecyclerView(context).apply {
    val list = mutableListOf<GenerationsSelectItem>().apply {
      PokemonProp.Generation.values().forEach {
        add(GenerationsSelectItem(it) { generation, isChecked ->
          if (isChecked) {
            generationList.add(generation)
          } else {
            generationList.remove(generation)
          }
          onGenerationListChange?.invoke(generationList)
        })
      }
    }
    val multiTypeAdapter = MultiTypeAdapter(list).apply {
      register(GenerationItemDelegate())
    }

    layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
    adapter = multiTypeAdapter
    addItemDecoration(object : RecyclerView.ItemDecoration() {
      override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
      ) {
        outRect.set(0, 0, 8.dp, 0)
      }
    })

    this@RightDrawerView.addView(this)
  }

  init {
    isClickable = true
    setBackgroundColor(context.getColor(R.color.background_56))
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    typesFilterView.autoMeasure()
    baseStatusTitle.autoMeasure()
    baseStatusFilterView.autoMeasure()
    generationsTitle.autoMeasure()
    generationsFilterView.autoMeasure()
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    typesFilterView.layout(0, 0)
    baseStatusTitle.layout(12.dp, typesFilterView.bottom + 8.dp)
    baseStatusFilterView.layout(12.dp, baseStatusTitle.bottom + 8.dp)
    generationsTitle.layout(12.dp, baseStatusFilterView.bottom + 8.dp)
    generationsFilterView.layout(12.dp, generationsTitle.bottom + 8.dp)
  }

  fun setBaseStatusTitleDataList(list: List<PokemonProp.BaseStatus>, isDesc: Boolean) {
    when {
      list.isEmpty() -> {
        baseStatusTitle.text = "${sortPriority(isDesc)}:未选择"
      }
      list.size == 6 -> {
        baseStatusTitle.text = "${sortPriority(isDesc)}:总能力值"
      }
      else -> {
        baseStatusTitle.text =
          "${sortPriority(isDesc)}:${list.map { it.getName(context) }.joinToString("+")}"
      }
    }
  }

  fun setGenerationTitleDataList(list: List<PokemonProp.Generation>) {
    when {
      list.isEmpty() -> {
        generationsTitle.text = "世代:未选择"
      }
      list.size == 6 -> {
        generationsTitle.text = "世代:所有世代"
      }
      else -> {
        generationsTitle.text =
          "世代:${list.map { it.filterString }.joinToString("+")}"
      }
    }

  }

  private fun sortPriority(isDesc: Boolean) = if (isDesc) "降序" else "升序"
}