package com.heinika.pokeg

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.heinika.pokeg.base.BasePage
import com.heinika.pokeg.ui.main.MainPage
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class PokeGActivity : AppCompatActivity() {

  private var pageStack: Stack<BasePage> = Stack()

  override fun onCreate(savedInstanceState: Bundle?) {
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    super.onCreate(savedInstanceState)
    MainPage(this,pageStack).showPage()
  }

  override fun onBackPressed() {
    if (pageStack.size > 1){
      pageStack.peek().exitPage()
    }else{
      super.onBackPressed()
    }
  }
}