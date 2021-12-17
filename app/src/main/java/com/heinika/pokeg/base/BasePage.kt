package com.heinika.pokeg.base

import android.app.Activity
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import java.util.*

abstract class BasePage(activity: Activity, val pageStack: Stack<BasePage>) {
  protected val content: FrameLayout = activity.window.decorView as FrameLayout

  open fun showPage(){
    pageStack.push(this)
  }

  open fun onBackPressed(){
    pageStack.pop()
  }
}