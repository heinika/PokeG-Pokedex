package com.heinika.pokeg.ui.main.layout

import android.content.Context
import android.graphics.Color
import android.text.InputFilter
import android.text.InputType
import android.view.Gravity
import android.view.KeyEvent
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.heinika.pokeg.R
import com.heinika.pokeg.base.CustomLayout

class MainPageView(context: Context) : CustomLayout(context) {

  val recyclerView = RecyclerView(context).apply {
    setBackgroundColor(Color.RED)
    layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
    layoutManager = LinearLayoutManager(context)
    setBackgroundColor(R.color.background.resColor)
    this@MainPageView.addView(this)
  }

  val progressBar = ProgressBar(context).apply {
    layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    addView(this)
  }

  val floatButton = FloatingActionButton(context).apply {
    layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
      bottomMargin = 8.dp
    }
    setImageResource(R.drawable.search)
    addView(this)
  }

  private val searchEditText = object : AppCompatEditText(context) {
    override fun onKeyPreIme(keyCode: Int, event: KeyEvent?): Boolean {
      if (keyCode == KeyEvent.KEYCODE_BACK) {
        recyclerView.translationY = 0f
        symbolTextView.translationY = 0f
        translationY = 0f
      }
      return super.onKeyPreIme(keyCode, event)
    }
  }.apply {
    layoutParams = LayoutParams(MATCH_PARENT, 54.dp).apply {
      marginStart = 12.dp
      marginEnd = 12.dp
    }

    setPadding(42.dp, 0, 0, 0)
    textSize = 36f
    setTextColor(R.color.colorPrimary.resColor)
    inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
    setSingleLine()
    filters = arrayOf(InputFilter.LengthFilter(5))
    imeOptions = EditorInfo.IME_ACTION_DONE
    setBackgroundResource(R.drawable.input_background)
    addView(this)
  }

  private val symbolTextView = AppCompatTextView(context).apply {
    layoutParams = LayoutParams(WRAP_CONTENT, 54.dp)
    setTextColor(R.color.colorPrimary.resColor)
    text = "#"
    gravity = Gravity.CENTER
    textSize = 36f
    setPadding(12.dp, 0, 0, 0)
    addView(this)
  }

  init {
    setBackgroundColor(R.color.background.resColor)
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    progressBar.autoMeasure()
    recyclerView.autoMeasure()
    floatButton.autoMeasure()
    searchEditText.let { it.measure((measuredWidth - it.marginStart - it.marginEnd).toExactlyMeasureSpec(), it.defaultHeightMeasureSpec()) }
    symbolTextView.autoMeasure()

    setMeasuredDimension(measuredWidth, measuredHeight)
  }


  override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
    recyclerView.layout(0, 0)
    progressBar.let {
      it.layout(
        width / 2 - it.measuredWidth / 2,
        height / 2 - it.measuredHeight / 2
      )
    }
    floatButton.let { it.layout(width / 2 - it.measuredWidth / 2, height - it.measuredHeight - it.marginBottom) }
    searchEditText.let { it.layout(it.marginStart, -it.measuredHeight - 12.dp) }
    symbolTextView.layout(searchEditText.left, searchEditText.top)
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    post {
      recyclerView.let {
        it.addOnScrollListener(object : RecyclerView.OnScrollListener() {
          var hideSearchAnimator = SpringAnimation(floatButton, DynamicAnimation.TRANSLATION_Y, 70.dp.toFloat())
          var showSearchAnimator = SpringAnimation(floatButton, DynamicAnimation.TRANSLATION_Y, 0f)


          override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (it.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
              showSearchAnimator.start()
            }
          }

          override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (it.scrollState != RecyclerView.SCROLL_STATE_IDLE) {
              if (dy > 0) {
                if (!hideSearchAnimator.isRunning && floatButton.translationY == 0f) {
                  hideSearchAnimator.start()
                }
              } else {
                if (!showSearchAnimator.isRunning) {
                  showSearchAnimator.start()
                }
              }
            }
          }
        })
      }

      searchEditText.let {
        it.setOnEditorActionListener { _, actionId, _ ->
          return@setOnEditorActionListener when (actionId) {
            EditorInfo.IME_ACTION_DONE -> {
              if (it.text.toString().isNotEmpty()) {
                when (val id = it.text.toString().toInt()) {
                  in 0..898 -> toSearchPosition(id)
                  in 899..10000 -> toSearchPosition(899)
                  in 1001..10220 -> toSearchPosition(898 + id - 10000)
                  else -> toSearchPosition(898 + 220)
                }
              } else {
                hideSearchBar()
              }
              true
            }
            else -> false
          }
        }
      }
    }
  }

  fun setOnSearchClickListener(onClickListener: OnClickListener){
    floatButton.setOnClickListener(onClickListener)
  }

  fun showSearchBar() {
    recyclerView.translationY = 120.dp.toFloat()
    symbolTextView.translationY = 120.dp.toFloat()
    searchEditText.translationY = 120.dp.toFloat()
    searchEditText.requestFocus()
    val imm: InputMethodManager = searchEditText.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(searchEditText, 0)
  }

  private fun toSearchPosition(position: Int) {
    (recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(position, 48.dp)
    hideSearchBar()
    searchEditText.setText("")
  }

  private fun hideSearchBar() {
    recyclerView.translationY = 0f
    symbolTextView.translationY = 0f
    searchEditText.translationY = 0f
    val imm: InputMethodManager = searchEditText.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (imm.isActive) {
      imm.hideSoftInputFromWindow(searchEditText.windowToken, 0)
    }
  }

  fun canScrollUp(): Boolean{
    return (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() != 0
  }

  fun scrollToTop(){
    (recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(0, 0)
  }
}