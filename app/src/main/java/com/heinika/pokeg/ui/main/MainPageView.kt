package com.heinika.pokeg.ui.main

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.text.method.PasswordTransformationMethod
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.marginEnd
import androidx.core.view.marginTop
import androidx.core.view.setPadding
import androidx.recyclerview.widget.GridLayoutManager
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
  }

  private val searchButton = Button(context).apply {
    layoutParams = LayoutParams(36.dp, 36.dp).apply {
      topMargin = 10.dp
      marginEnd = 12.dp
    }
    setPadding(0)
    text = "GO"
    setOnClickListener {
      val position = inputText.text.toString().toInt()
      (recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(position - 1, 0)
    }
    addView(this)
  }

  private val inputText = EditText(context).apply {
    layoutParams = LayoutParams(60.dp, 36.dp).apply {
      marginEnd = 56.dp
      topMargin = 10.dp
    }
    maxLines = 1
    inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_VARIATION_NORMAL
    setPadding(8.dp, 0, 8.dp, 0)
    gravity = Gravity.END or Gravity.CENTER_VERTICAL
    setText("150")
    setBackgroundResource(R.drawable.input_background)
    addView(this)
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
    searchButton.autoMeasure()
    inputText.autoMeasure()
    recyclerView.measure(
      defaultWidthMeasureSpec(),
      (height - toolbarTitle.measuredHeightWithMargins).toExactlyMeasureSpec()
    )
  }


  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    statusBarBackground.layout(0, 0)
    toolbarTitle.layout(0, statusBarBackground.bottom)
    searchButton.let { it.layout(it.marginEnd, statusBarBackground.bottom + it.marginTop, true) }
    inputText.let { it.layout(it.marginEnd, statusBarBackground.bottom + it.marginTop, true) }
    recyclerView.layout(0, toolbarTitle.bottom)
    progressBar.let {
      it.layout(
        width / 2 - it.measuredWidth / 2,
        height / 2 - it.measuredHeight / 2
      )
    }
  }

}