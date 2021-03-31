package com.heinika.pokeg.ui.main

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heinika.pokeg.R
import com.heinika.pokeg.base.CustomLayout

class MainPageView(context: Context) : CustomLayout(context) {

  private val statusBarBackground = View(context).apply {
    layoutParams = LayoutParams(MATCH_PARENT, statusBarHeight)
    setBackgroundColor(R.color.colorPrimaryDark.resColor)
    addView(this)
  }

  private val toolbarTitle = TextView(context).apply {
    layoutParams = LayoutParams(MATCH_PARENT, 56.dp)
    text = R.string.app_name.resString
    setBackgroundColor(R.color.colorPrimary.resColor)
    setTextColor(R.color.white.resColor)
    textSize = 21f
    gravity = Gravity.CENTER_VERTICAL
    maxEms = 15
    setPadding(10.dp)
    setTypeface(typeface, Typeface.BOLD)
    addView(this)
    setOnClickListener {
      (recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(1000,0)
    }
  }

  val recyclerView = RecyclerView(context).apply {
    setBackgroundColor(Color.RED)
    layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    layoutManager = LinearLayoutManager(context)
    setBackgroundColor(R.color.background.resColor)
    this@MainPageView.addView(this)
  }

  val progressBar = ProgressBar(context).apply {
    layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    addView(this)
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    statusBarBackground.autoMeasure()
    toolbarTitle.autoMeasure()
    progressBar.autoMeasure()
    recyclerView.measure(
      defaultWidthMeasureSpec(),
      (height - toolbarTitle.measuredHeightWithMargins).toExactlyMeasureSpec()
    )
  }


  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    statusBarBackground.layout(0, 0)
    toolbarTitle.layout(0, statusBarBackground.bottom)
    recyclerView.layout(0, toolbarTitle.bottom)
    progressBar.let {
      it.layout(
        width / 2 - it.measuredWidth / 2,
        height / 2 - it.measuredHeight / 2
      )
    }
  }

}