package com.heinika.pokeg.base

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

abstract class CustomLayout(context: Context) : ViewGroup(context) {

  protected val Number.dp: Int
    get() = (this.toInt() * resources.displayMetrics.density + 0.5f).toInt()

  protected val Number.dpf: Float
    get() = (this.toFloat() * resources.displayMetrics.density)

  protected val Int.resString: CharSequence
    get() = this@CustomLayout.resources.getString(this)

  protected val Int.resColor: Int
    get() = this@CustomLayout.resources.getColor(this)

  protected val View.measuredWidthWithMargins: Int
    get() = this.measuredWidth + this.marginLeft + this.marginRight

  protected val View.measuredHeightWithMargins: Int
    get() = this.measuredHeight + this.marginTop + this.marginBottom

  protected fun View.autoMeasure() {
    measure(this.defaultWidthMeasureSpec(), this.defaultHeightMeasureSpec())
  }

  protected fun View.layout(x: Int, y: Int, fromRight: Boolean = false) {
    if (!fromRight) {
      layout(x, y, x + this.measuredWidth, y + this.measuredHeight)
    } else {
      layout(this@CustomLayout.width - x - measuredWidth, y)
    }
  }

  protected fun Int.toExactlyMeasureSpec(): Int {
    return MeasureSpec.makeMeasureSpec(this, MeasureSpec.EXACTLY)
  }

  protected fun Int.toAtMostMeasureSpec(): Int {
    return MeasureSpec.makeMeasureSpec(this, MeasureSpec.AT_MOST)
  }

  protected fun View.defaultWidthMeasureSpec(): Int {
    return when (layoutParams.width) {
      MATCH_PARENT -> this@CustomLayout.measuredWidth.toExactlyMeasureSpec()
      WRAP_CONTENT -> WRAP_CONTENT.toAtMostMeasureSpec()
      0 -> throw IllegalAccessException("Need special treatment for $this")
      else -> layoutParams.width.toExactlyMeasureSpec()
    }
  }

  protected fun View.defaultHeightMeasureSpec(): Int {
    return when (layoutParams.height) {
      MATCH_PARENT -> this@CustomLayout.measuredHeight.toExactlyMeasureSpec()
      WRAP_CONTENT -> WRAP_CONTENT.toAtMostMeasureSpec()
      0 -> throw IllegalAccessException("Need special treatment for $this")
      else -> layoutParams.height.toExactlyMeasureSpec()
    }
  }

  protected fun Context.toTheme(themeResId: Int) = ContextThemeWrapper(this, themeResId)

  protected class LayoutParams(width: Int, height: Int) : MarginLayoutParams(width, height)
}