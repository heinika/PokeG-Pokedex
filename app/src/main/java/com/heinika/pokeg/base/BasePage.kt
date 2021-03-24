package com.heinika.pokeg.base

import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import java.util.*

abstract class BasePage(activity: AppCompatActivity, val pageStack: Stack<BasePage>) {
  protected val content: FrameLayout = activity.window.decorView as FrameLayout

  open fun showPage(){
    pageStack.push(this)
  }

  open fun exitPage(){
    pageStack.pop()
  }
}