package com.heinika.pokeg.ui.main.itemdelegate

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate
import com.heinika.pokeg.ui.main.itemdelegate.model.BottomItem
import com.heinika.pokeg.ui.main.layout.BottomView

class BottomItemDelegate : ItemViewDelegate<BottomItem, BottomItemDelegate.ViewHolder>() {

  override fun onBindViewHolder(holder: ViewHolder, item: BottomItem) {

  }

  override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
    return ViewHolder(BottomView(context))
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}