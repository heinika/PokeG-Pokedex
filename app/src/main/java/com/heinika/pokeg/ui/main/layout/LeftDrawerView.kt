package com.heinika.pokeg.ui.main.layout

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import com.heinika.pokeg.AboutActivity
import com.heinika.pokeg.ConfigMMKV
import com.heinika.pokeg.DonationActivity
import com.heinika.pokeg.R
import com.heinika.pokeg.utils.SystemBar

class LeftDrawerView(context: Context) : LinearLayout(context) {

  init {
    isClickable = true
    orientation = VERTICAL
    setBackgroundColor(context.getColor(R.color.black_70))
  }

  private val selectVersionButton = AppCompatButton(context).apply {
    text =
      "默认版本：${context.resources.getStringArray(R.array.versions)[ConfigMMKV.defaultVersion - 1]}"
    addView(this)
    (layoutParams as LayoutParams).topMargin = SystemBar.statusBarHeight
    background.setTint(context.getColor(R.color.md_amber_700))
    setTextColor(Color.WHITE)

    val selectVersionDialog = AlertDialog.Builder(context).setTitle("选择默认版本")
      .setItems(
        context.resources.getStringArray(R.array.versions)
      ) { dialog, index ->
        ConfigMMKV.defaultVersion = index + 1
        text =
          "默认版本：${context.resources.getStringArray(R.array.versions)[ConfigMMKV.defaultVersion - 1]}"
        dialog.dismiss()
      }.create()

    setOnClickListener {
      selectVersionDialog.show()
    }
  }

  private val aboutButton = AppCompatButton(context).apply {
    text = "关于"
    addView(this)
    background.setTint(context.getColor(R.color.colorPrimary))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, AboutActivity::class.java)) }
  }

  private val donationButton = AppCompatButton(context).apply {
    text = "捐赠"
    addView(this)
    background.setTint(context.getColor(R.color.md_green_200))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, DonationActivity::class.java)) }
  }


}