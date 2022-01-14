package com.heinika.pokeg.module.main.layout

import android.content.Context
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import com.heinika.pokeg.R
import com.heinika.pokeg.base.CustomLayout

class BottomView(context: Context) : CustomLayout(context) {
  private val titleLabel = TextView(context).apply {
    text = "没有了"
    textAlignment = TEXT_ALIGNMENT_CENTER
    textSize = 10.dp.toFloat()
    setPadding(0, 60.dp, 0, 0)
    layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    setTextColor(R.color.white.resColor)
    this@BottomView.addView(this)
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    titleLabel.autoMeasure()

    setMeasuredDimension(measuredWidth, 282.dp)
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    titleLabel.layout(0,measuredHeight/2 + 100.dp)
  }
}