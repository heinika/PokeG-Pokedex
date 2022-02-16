package com.heinika.pokeg.module.main.layout

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.marginTop
import com.heinika.pokeg.R
import com.heinika.pokeg.base.CustomLayout
import com.heinika.pokeg.curDexType
import com.heinika.pokeg.info.DexType
import com.heinika.pokeg.utils.SystemBar

@SuppressLint("ViewConstructor")
class HeaderView(context: Context, onSettingClick: () -> Unit, onLoopClick: (type: DexType) -> Unit) :
  CustomLayout(context) {

  private val titleLabel = AppCompatTextView(context).apply {
    text = curDexType.toDescString()
    textSize = 10.dp.toFloat()
    setPadding(0, SystemBar.statusBarHeight + 16.dp, 0, 0)
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

  private val loopImage = AppCompatImageButton(context).apply {
    layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    setImageResource(R.drawable.ic_loop_24)
    background = null
    setOnClickListener {
      curDexType = curDexType.next()
      titleLabel.text = curDexType.toDescString()
      onLoopClick(curDexType)
    }
    this@HeaderView.addView(this)
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)

    titleLabel.autoMeasure()
    settingImage.autoMeasure()
    loopImage.autoMeasure()

    setMeasuredDimension(measuredWidth, 66.dp + SystemBar.statusBarHeight)
  }

  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    settingImage.layout(12.dp, SystemBar.statusBarHeight + 8.dp)
    titleLabel.let { it.layout(12.dp + settingImage.measuredWidth, it.marginTop) }
    loopImage.let { it.layout(measuredWidth - it.measuredWidth - 12.dp,settingImage.top + 8.dp) }
  }
}