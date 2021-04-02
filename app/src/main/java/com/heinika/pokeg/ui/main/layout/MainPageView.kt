package com.heinika.pokeg.ui.main.layout

import android.content.Context
import android.graphics.Color
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ProgressBar
import androidx.core.view.marginBottom
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.heinika.pokeg.R
import com.heinika.pokeg.base.CustomLayout
import timber.log.Timber

class MainPageView(context: Context) : CustomLayout(context) {


  val recyclerView = RecyclerView(context).apply {
    setBackgroundColor(Color.RED)
    layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
    layoutManager = LinearLayoutManager(context)
    setBackgroundColor(R.color.background.resColor)

    post{
      addOnScrollListener(object : RecyclerView.OnScrollListener() {
        var hideSearchAnimator= SpringAnimation(floatButton, DynamicAnimation.TRANSLATION_Y, 70.dp.toFloat())
        var showSearchAnimator= SpringAnimation(floatButton, DynamicAnimation.TRANSLATION_Y, 0f)


        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
          super.onScrollStateChanged(recyclerView, newState)
          Timber.i("$scrollState")
          if (scrollState == SCROLL_STATE_IDLE) {
            showSearchAnimator.start()
          }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
          super.onScrolled(recyclerView, dx, dy)
          if (scrollState != SCROLL_STATE_IDLE) {
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

  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    progressBar.autoMeasure()
    recyclerView.autoMeasure()
    floatButton.autoMeasure()

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
  }

}