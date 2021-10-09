package com.heinika.pokeg.ui.main.layout

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import com.heinika.pokeg.R
import com.heinika.pokeg.base.CustomLayout
import com.heinika.pokeg.utils.StatusBarHeight

class RightDrawerView(context: Context) : CustomLayout(context) {
  val filterListView = FilterListView(context).apply {
    layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    setPadding(0, StatusBarHeight.value, 0, 0)
    this@RightDrawerView.addView(this)
  }

  init {
    isClickable = true
    setBackgroundColor(context.getColor(R.color.background_56))
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    filterListView.autoMeasure()
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    filterListView.layout(0,0)
  }
}