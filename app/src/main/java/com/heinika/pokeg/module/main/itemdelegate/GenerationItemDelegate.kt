package com.heinika.pokeg.module.main.itemdelegate

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate
import com.heinika.pokeg.module.main.itemdelegate.model.GenerationsSelectItem
import com.heinika.pokeg.utils.dp
import com.heinika.pokeg.view.GenerationCheckBox

class GenerationItemDelegate :
  ItemViewDelegate<GenerationsSelectItem, GenerationItemDelegate.ViewHolder>() {
  class ViewHolder(private val generationCheckBox: GenerationCheckBox) :
    RecyclerView.ViewHolder(generationCheckBox) {
    fun setData(item: GenerationsSelectItem) {
      generationCheckBox.setBaseStatus(item.generation)
      generationCheckBox.setOnClickListener {
        item.onClick(item.generation, generationCheckBox.isChecked)
      }
    }
  }

  override fun onBindViewHolder(holder: ViewHolder, item: GenerationsSelectItem) {
    holder.setData(item)
  }

  override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
    return ViewHolder(GenerationCheckBox(context).apply {
      layoutParams = LinearLayout.LayoutParams(30.dp, 30.dp)
    })
  }
}