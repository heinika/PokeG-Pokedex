package com.heinika.pokeg.ui.main.layout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.MultiTypeAdapter
import com.heinika.pokeg.R
import com.heinika.pokeg.base.CustomLayout
import com.heinika.pokeg.ui.main.itemdelegate.BaseStatusItemDelegate
import com.heinika.pokeg.ui.main.itemdelegate.GenerationItemDelegate
import com.heinika.pokeg.ui.main.itemdelegate.TagItemDelegate
import com.heinika.pokeg.ui.main.itemdelegate.model.BaseStatusSelectItem
import com.heinika.pokeg.ui.main.itemdelegate.model.GenerationsSelectItem
import com.heinika.pokeg.ui.main.itemdelegate.model.TagSelectItem
import com.heinika.pokeg.utils.PokemonProp
import com.heinika.pokeg.utils.StatusBarHeight
import com.heinika.pokeg.view.BaseStatusCheckBox
import com.heinika.pokeg.view.BodyRadioButton

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
  var onBaseStatusFilterViewClick: (() -> Unit)? = null

  private val tagCheckedList = mutableListOf<PokemonProp.Tag>()
  var onTagCheckedListChange: ((list: List<PokemonProp.Tag>) -> Unit)? = null
  var onTagFilterViewClick: (() -> Unit)? = null

  private val baseStatusFilterView: RecyclerView = RecyclerView(context).apply {
    val list = mutableListOf<BaseStatusSelectItem>().apply {
      PokemonProp.BaseStatus.values().forEach { eachBaseStatus ->
        add(BaseStatusSelectItem(eachBaseStatus) { baseStatus, isChecked ->
          if (isChecked) {
            baseStatusCheckedList.add(baseStatus)
          } else {
            baseStatusCheckedList.remove(baseStatus)
          }
          clearBodyRadioButtonSelected()
          onBaseStatusCheckedListChange?.invoke(baseStatusCheckedList)
          onBaseStatusFilterViewClick?.invoke()
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

  private fun clearBodyRadioButtonSelected() {
    onBodyStatusSelectChange?.invoke(null)
    bodyRadioGroup.clearCheck()
  }

  var onBodyStatusSelectChange: ((bodyStatus: PokemonProp.BodyStatus?) -> Unit)? = null
  var onBodyStatusRadioButtonClick: (() -> Unit)? = null

  private val bodyRadioGroup: RadioGroup = RadioGroup(context).apply {
    layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    orientation = RadioGroup.HORIZONTAL
    PokemonProp.BodyStatus.values().forEach { bodyStatus ->
      addView(BodyRadioButton(context, bodyStatus).apply {
        setOnClickListener {
          clearBaseStatusSelect()
          onBodyStatusSelectChange?.invoke(bodyStatus)
          onBodyStatusRadioButtonClick?.invoke()
        }
      }, LayoutParams(60.dp, 40.dp).apply {
        marginEnd = 8.dp
      })
    }

    this@RightDrawerView.addView(this)
  }

  private fun clearBaseStatusSelect() {
    baseStatusCheckedList.clear()
    baseStatusFilterView.children.forEach { baseStatusCheckBox ->
      if (baseStatusCheckBox is BaseStatusCheckBox) {
        baseStatusCheckBox.isChecked = false
      }
    }
    onBaseStatusCheckedListChange?.invoke(baseStatusCheckedList)
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

  private val tagTitle = TextView(context).apply {
    layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    text = "只显示符合标签的"
    setTextColor(Color.WHITE)
    textSize = 20f
    setOnClickListener {
      onBaseStatusTitleClick?.invoke()
    }
    addView(this)
  }

  private val tagFilterView: RecyclerView = RecyclerView(context).apply {
    val list = mutableListOf<TagSelectItem>().apply {
      PokemonProp.Tag.values().forEach { eachTag ->
        add(TagSelectItem(eachTag) { tag, isChecked ->
          if (isChecked) {
            tagCheckedList.add(tag)
          } else {
            tagCheckedList.remove(tag)
          }

          onTagCheckedListChange?.invoke(tagCheckedList)
          onTagFilterViewClick?.invoke()
        })
      }
    }
    val multiTypeAdapter = MultiTypeAdapter(list).apply {
      register(TagItemDelegate())
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
    bodyRadioGroup.autoMeasure()
    generationsTitle.autoMeasure()
    generationsFilterView.autoMeasure()
    tagTitle.autoMeasure()
    tagFilterView.autoMeasure()
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    typesFilterView.layout(0, 0)
    baseStatusTitle.layout(12.dp, typesFilterView.bottom + 8.dp)
    baseStatusFilterView.layout(12.dp, baseStatusTitle.bottom + 8.dp)
    bodyRadioGroup.layout(12.dp, baseStatusFilterView.bottom + 8.dp)
    generationsTitle.layout(12.dp, bodyRadioGroup.bottom + 8.dp)
    generationsFilterView.layout(12.dp, generationsTitle.bottom + 8.dp)
    tagTitle.layout(12.dp, generationsFilterView.bottom + 8.dp)
    tagFilterView.layout(12.dp, tagTitle.bottom + 8.dp)
  }

  @SuppressLint("SetTextI18n")
  fun setBaseStatusTitleDataList(
    list: List<PokemonProp.BaseStatus>,
    bodyStatus: PokemonProp.BodyStatus?,
    isDesc: Boolean
  ) {
    when {
      list.isEmpty() -> {
        if (bodyStatus == null) {
          baseStatusTitle.text = context.getString(R.string.base_status_unselected,sortPriority(isDesc))
        } else {
          setBodyStatus(bodyStatus, isDesc)
        }
      }
      list.size == 6 -> {
        baseStatusTitle.text = context.getString(R.string.all_base_status_selected,sortPriority(isDesc))
      }
      else -> {
        baseStatusTitle.text =
          "${sortPriority(isDesc)}:${list.map { it.getName(context) }.joinToString("+")}"
      }
    }
  }

  @SuppressLint("SetTextI18n")
  fun setBodyStatus(bodyStatus: PokemonProp.BodyStatus?, isDesc: Boolean) {
    bodyStatus?.let {
      baseStatusTitle.text = "${sortPriority(isDesc)}:${bodyStatus.getName(context)}"
    }
  }

  @SuppressLint("SetTextI18n")
  fun setGenerationTitleDataList(list: List<PokemonProp.Generation>) {
    when {
      list.isEmpty() -> {
        generationsTitle.text = context.getString(R.string.genneration_unselected)
      }
      list.size == 8 -> {
        generationsTitle.text = context.getString(R.string.genneration_all_selected)
      }
      else -> {
        generationsTitle.text =
          "${context.getString(R.string.genneration)}:${list.map { it.filterString }.joinToString("+")}"
      }
    }

  }

  private fun sortPriority(isDesc: Boolean) = if (isDesc) context.getString(R.string.Descending) else context.getString(R.string.Ascending)
}