package com.heinika.pokeg.ui.main.itemdelegate

import android.content.Context
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate
import com.heinika.pokeg.ui.main.itemdelegate.model.TagSelectItem
import com.heinika.pokeg.utils.dp
import com.heinika.pokeg.view.TagCheckBox

class TagItemDelegate :
  ItemViewDelegate<TagSelectItem, TagItemDelegate.ViewHolder>() {
  class ViewHolder(private val tagCheckBox: TagCheckBox) :
    RecyclerView.ViewHolder(tagCheckBox) {
    fun setData(item: TagSelectItem) {
      tagCheckBox.setBaseStatus(item.tag)
      tagCheckBox.setOnClickListener {
        item.onClick(item.tag, tagCheckBox.isChecked)
      }
    }
  }

  override fun onBindViewHolder(holder: ViewHolder, item: TagSelectItem) {
    holder.setData(item)
  }

  override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
    return ViewHolder(TagCheckBox(context).apply {
      layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, 30.dp)
      setPadding(6.dp, 0, 6.dp, 0)
    })
  }
}