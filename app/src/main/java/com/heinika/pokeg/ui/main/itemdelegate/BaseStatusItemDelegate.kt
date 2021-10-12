package com.heinika.pokeg.ui.main.itemdelegate

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate
import com.heinika.pokeg.ui.main.itemdelegate.model.BaseStatusSelectItem
import com.heinika.pokeg.utils.dp
import com.heinika.pokeg.view.BaseStatusCheckBox

class BaseStatusItemDelegate :
  ItemViewDelegate<BaseStatusSelectItem, BaseStatusItemDelegate.ViewHolder>() {
  class ViewHolder(private val baseStatusCheckBox: BaseStatusCheckBox) :
    RecyclerView.ViewHolder(baseStatusCheckBox) {
    fun setData(item: BaseStatusSelectItem) {
      baseStatusCheckBox.setBaseStatus(item.baseStatus)
      baseStatusCheckBox.setOnClickListener {
        item.onClick(item.baseStatus, baseStatusCheckBox.isChecked)
      }
    }
  }

  override fun onBindViewHolder(holder: ViewHolder, item: BaseStatusSelectItem) {
    holder.setData(item)
  }

  override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
    return ViewHolder(BaseStatusCheckBox(context).apply {
      layoutParams = LinearLayout.LayoutParams(40.dp, 40.dp)
    })
  }
}