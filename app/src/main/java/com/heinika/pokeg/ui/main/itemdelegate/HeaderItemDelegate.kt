package com.heinika.pokeg.ui.main.itemdelegate

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate
import com.heinika.pokeg.ui.main.itemdelegate.model.Header
import com.heinika.pokeg.ui.main.layout.HeaderView

class HeaderItemDelegate : ItemViewDelegate<Header, HeaderItemDelegate.ViewHolder>() {

  override fun onBindViewHolder(holder: ViewHolder, item: Header) {

  }

  override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
    return ViewHolder(HeaderView(context))
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

  }
}