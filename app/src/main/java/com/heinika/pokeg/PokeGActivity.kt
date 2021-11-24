package com.heinika.pokeg

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.heinika.pokeg.base.BasePage
import com.heinika.pokeg.ui.main.MainPage
import com.heinika.pokeg.repository.res.PokemonRes
import com.heinika.pokeg.utils.SystemBar
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class PokeGActivity : AppCompatActivity() {

  private var pageStack: Stack<BasePage> = Stack()

  @Inject
  lateinit var pokemonRes: PokemonRes

  override fun onCreate(savedInstanceState: Bundle?) {
    window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    super.onCreate(savedInstanceState)
    SystemBar.initStatusBarHeight(this)
    MainPage(this, pageStack, pokemonRes).showPage()
  }

  override fun onBackPressed() {
    pageStack.peek().onBackPressed()
  }
}