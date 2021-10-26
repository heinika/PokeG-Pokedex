package com.heinika.pokeg.ui.main.layout

import android.content.Context
import android.graphics.Color
import android.text.InputFilter
import android.view.Gravity
import android.view.KeyEvent
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
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
import com.heinika.pokeg.utils.StatusBarHeight

class MainPageView(context: Context) : CustomLayout(context) {

  var onSearchTextChange: ((CharSequence?) -> Unit)? = null

  val recyclerView = RecyclerView(context).apply {
    layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
    layoutManager = LinearLayoutManager(context)
    setBackgroundColor(R.color.background.resColor)
    this@MainPageView.addView(this)
  }

  val progressBar = ProgressBar(context).apply {
    layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
    addView(this)
  }

  val searchButton = FloatingActionButton(context).apply {
    layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
      bottomMargin = 8.dp
    }
    setImageResource(R.drawable.ic_search)
    setColorFilter(Color.WHITE)
    addView(this)
  }

  private val toTopButton = FloatingActionButton(context).apply {
    layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
      bottomMargin = 8.dp
    }
    setImageResource(R.drawable.ic_arror_top)
    setColorFilter(Color.WHITE)
    addView(this)
  }

  private val filterListButton = FloatingActionButton(context).apply {
    layoutParams = LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
      bottomMargin = 8.dp
    }
    setImageResource(R.drawable.ic_filter_list)
    setColorFilter(Color.WHITE)
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

    override fun onTextChanged(
      text: CharSequence?,
      start: Int,
      lengthBefore: Int,
      lengthAfter: Int
    ) {
      super.onTextChanged(text, start, lengthBefore, lengthAfter)
      onSearchTextChange?.invoke(text)
    }
  }.apply {
    layoutParams = LayoutParams(MATCH_PARENT, 54.dp).apply {
      marginStart = 12.dp
      marginEnd = 12.dp
    }

    setPadding(42.dp, 0, 0, 0)
    textSize = 36f
    setTextColor(R.color.colorPrimary.resColor)
    setSingleLine()
    filters = arrayOf(InputFilter.LengthFilter(5))
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

  private val hideToTopButtonAnimator = SpringAnimation(
    toTopButton,
    DynamicAnimation.TRANSLATION_Y,
    70.dp.toFloat()
  )

  private val hideFilterListAnimator = SpringAnimation(
    filterListButton,
    DynamicAnimation.TRANSLATION_Y,
    70.dp.toFloat()
  )

  private val hideSearchAnimator = SpringAnimation(
    searchButton,
    DynamicAnimation.TRANSLATION_Y,
    70.dp.toFloat()
  )

  private val showToTopButtonAnimator =
    SpringAnimation(toTopButton, DynamicAnimation.TRANSLATION_Y, 0f)

  private val showSearchAnimator =
    SpringAnimation(searchButton, DynamicAnimation.TRANSLATION_Y, 0f)

  private val showFilterListAnimator =
    SpringAnimation(filterListButton, DynamicAnimation.TRANSLATION_Y, 0f)



  init {
    setBackgroundColor(R.color.background.resColor)
  }

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    progressBar.autoMeasure()
    recyclerView.autoMeasure()
    toTopButton.autoMeasure()
    searchButton.autoMeasure()
    filterListButton.autoMeasure()
    searchEditText.let {
      it.measure(
        (measuredWidth - it.marginStart - it.marginEnd).toExactlyMeasureSpec(),
        it.defaultHeightMeasureSpec()
      )
    }
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
    toTopButton.let {
      it.layout(
        width / 2 - it.measuredWidth / 2 - 92.dp,
        height - it.measuredHeight - it.marginBottom
      )
    }
    searchButton.let {
      it.layout(
        width / 2 - it.measuredWidth / 2,
        height - it.measuredHeight - it.marginBottom
      )
    }
    filterListButton.let {
      it.layout(
        width / 2 - it.measuredWidth / 2 + 92.dp,
        height - it.measuredHeight - it.marginBottom
      )
    }
    searchEditText.let { it.layout(it.marginStart, -it.measuredHeight - StatusBarHeight.value) }
    symbolTextView.layout(searchEditText.left, searchEditText.top)
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    post {
      recyclerView.let {
        it.addOnScrollListener(object : RecyclerView.OnScrollListener() {

          override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (it.scrollState == RecyclerView.SCROLL_STATE_IDLE) {
              showBottomFilterView()
            }
          }

          override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (it.scrollState != RecyclerView.SCROLL_STATE_IDLE) {
              if (dy > 0) {
                if (!hideSearchAnimator.isRunning && searchButton.translationY == 0f) {
                  hideBottomFilterBar()
                }
              } else {
                if (!showSearchAnimator.isRunning) {
                  showBottomFilterView()
                }
              }
            }
          }
        })
      }

    }
  }

  fun hideBottomFilterBar() {
    hideSearchAnimator.start()
    hideFilterListAnimator.start()
    hideToTopButtonAnimator.start()
  }

  fun setOnTopButtonClickListener(onClickListener: OnClickListener) {
    toTopButton.setOnClickListener(onClickListener)
  }

  fun setOnSearchClickListener(onClickListener: OnClickListener) {
    searchButton.setOnClickListener(onClickListener)
  }

  fun setOnFilterClickListener(onClickListener: OnClickListener) {
    filterListButton.setOnClickListener(onClickListener)
  }

  fun showSearchBar() {
    searchEditText.translationY = 120.dp.toFloat() + StatusBarHeight.value
    symbolTextView.translationY = 120.dp.toFloat() + StatusBarHeight.value
    recyclerView.translationY = 120.dp.toFloat()
    searchEditText.requestFocus()
    val imm: InputMethodManager =
      searchEditText.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.showSoftInput(searchEditText, 0)
  }

  fun hideTopFilterListView() {
    recyclerView.translationY = 0f
  }

  fun showBottomFilterView(){
    showSearchAnimator.start()
    showFilterListAnimator.start()
    showToTopButtonAnimator.start()
  }

  fun canScrollUp(): Boolean {
    return (recyclerView.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition() != 0
  }

  fun scrollToTop() {
    (recyclerView.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(0, 0)
  }
}