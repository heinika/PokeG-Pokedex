package com.heinika.pokeg.module.main.itemdelegate

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.drakeet.multitype.ItemViewDelegate
import com.heinika.pokeg.info.DexType
import com.heinika.pokeg.module.main.itemdelegate.model.Header
import com.heinika.pokeg.module.main.layout.HeaderView

class HeaderItemDelegate(private val onSettingClick: () -> Unit,private val onLoopClick:(dexType:DexType) ->Unit) : ItemViewDelegate<Header, HeaderItemDelegate.ViewHolder>() {

  override fun onBindViewHolder(holder: ViewHolder, item: Header) {}

  override fun onCreateViewHolder(context: Context, parent: ViewGroup): ViewHolder {
    return ViewHolder(HeaderView(context,onSettingClick, onLoopClick))
  }

  class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val headerView = itemView as HeaderView
  }
}