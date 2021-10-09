package com.heinika.pokeg.ui.setting

import androidx.appcompat.app.AppCompatActivity
import com.heinika.pokeg.base.BasePage
import com.heinika.pokeg.databinding.PageSettingBinding
import java.util.*

class SettingPage(private val activity: AppCompatActivity, pageStack: Stack<BasePage>) :
  BasePage(activity, pageStack) {
  private val binding: PageSettingBinding = PageSettingBinding.inflate(activity.layoutInflater)

  override fun showPage() {
    super.showPage()
    content.addView(binding.root)
  }

  override fun onBackPressed() {
    super.onBackPressed()
    content.removeView(binding.root)
  }
}