package com.heinika.pokeg.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.widget.RadioGroup
import com.heinika.pokeg.R
import com.heinika.pokeg.utils.dp

class MoveMethodRadioButton @JvmOverloads constructor(
  context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatRadioButton(context, attrs, defStyleAttr) {

  init {
    setTextColor(Color.WHITE)
    textSize = 15f
    layoutParams = RadioGroup.LayoutParams(0, 24.dp).apply {
      weight = 1f
    }
    gravity = Gravity.CENTER
    setBackgroundResource(R.drawable.selector_radio_button)
    setTextColor(resources.getColorStateList(R.color.selector_radio_button_text_color, null))
  }
}