package com.heinika.pokeg.ui.main.layout

import android.content.Context
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import com.heinika.pokeg.R
import com.heinika.pokeg.base.CustomLayout
import com.heinika.pokeg.utils.StatusBarHeight

class HeaderView(context: Context) : CustomLayout(context) {
  private val titleLabel = TextView(context).apply {
    text = "全国图鉴"
    textSize = 10.dp.toFloat()
    setPadding(24.dp, StatusBarHeight.value, 0, 0)
    layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    setTextColor(R.color.white.resColor)
    this@HeaderView.addView(this)
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    titleLabel.autoMeasure()

    setMeasuredDimension(measuredWidth, 82.dp)
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    titleLabel.let { it.layout(it.marginStart, it.marginTop) }
  }
}