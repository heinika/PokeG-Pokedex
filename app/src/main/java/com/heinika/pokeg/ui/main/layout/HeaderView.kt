package com.heinika.pokeg.ui.main.layout

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.marginTop
import com.heinika.pokeg.R
import com.heinika.pokeg.base.CustomLayout
import com.heinika.pokeg.utils.StatusBarHeight

@SuppressLint("ViewConstructor")
class HeaderView(context: Context, onSettingClick: () -> Unit) : CustomLayout(context) {
  private val titleLabel = AppCompatTextView(context).apply {
    text = "图鉴"
    textSize = 10.dp.toFloat()
    setPadding(0, StatusBarHeight.value + 16.dp, 0, 0)
    layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    setTextColor(R.color.white.resColor)
    this@HeaderView.addView(this)
  }

  private val settingImage = AppCompatImageButton(context).apply {
    layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    setImageResource(R.drawable.ic_menu)
    background = null
    setOnClickListener {
      onSettingClick()
    }
    this@HeaderView.addView(this)
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    titleLabel.autoMeasure()
    settingImage.autoMeasure()

    setMeasuredDimension(measuredWidth, 66.dp + StatusBarHeight.value)
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    settingImage.layout(12.dp, StatusBarHeight.value + 8.dp)
    titleLabel.let { it.layout(12.dp + settingImage.measuredWidth, it.marginTop) }
  }
}