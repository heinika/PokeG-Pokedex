package com.heinika.pokeg.ui.main.layout

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView

class LeftDrawerView(context: Context) : LinearLayout(context) {

  init {
    isClickable = true
    addView(TextView(context).apply {
      text = "TEST"
    })
  }
}