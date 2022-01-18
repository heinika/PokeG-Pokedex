package com.heinika.pokeg.module.main.layout

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.heinika.pokeg.module.about.AboutActivity
import com.heinika.pokeg.ConfigMMKV
import com.heinika.pokeg.module.donation.DonationActivity
import com.heinika.pokeg.R
import com.heinika.pokeg.module.egg.EggActivity
import com.heinika.pokeg.module.gameprops.GamePropsActivity
import com.heinika.pokeg.module.moves.MoveListActivity
import com.heinika.pokeg.module.nature.NatureListActivity
import com.heinika.pokeg.module.team.TeamActivity
import com.heinika.pokeg.module.versions.VersionsActivity
import com.heinika.pokeg.utils.SystemBar

@ExperimentalAnimationApi
class LeftDrawerView(context: Context) : LinearLayout(context) {

  init {
    isClickable = true
    orientation = VERTICAL
    setBackgroundColor(context.getColor(R.color.black_70))
  }

  @SuppressLint("SetTextI18n")
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

  @ExperimentalFoundationApi
  @SuppressLint("SetTextI18n")
  private val versionsButton = AppCompatButton(context).apply {
    text = "版本列表"
    background.setTint(context.getColor(R.color.generation_2))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, VersionsActivity::class.java)) }
    addView(this)
  }

  @ExperimentalMaterialApi
  private val movesButton = AppCompatButton(context).apply {
    text = "技能列表（试用版）"
    background.setTint(context.getColor(R.color.md_blue_200))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, MoveListActivity::class.java)) }
    addView(this)
  }

  @ExperimentalCoilApi
  @ExperimentalMaterialApi
  private val teamsButton = AppCompatButton(context).apply {
    text = "我的队伍（开发中）"
    background.setTint(context.getColor(R.color.base_status_sdef))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, TeamActivity::class.java)) }
    addView(this)
  }

  @ExperimentalPagerApi
  @ExperimentalMaterialApi
  private val gamePropsButton = AppCompatButton(context).apply {
    text = "道具列表"
    background.setTint(context.getColor(R.color.base_status_sum))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, GamePropsActivity::class.java)) }
    addView(this)
  }

  @ExperimentalMaterialApi
  private val natureButton = AppCompatButton(context).apply {
    text = "性格列表"
    background.setTint(context.getColor(R.color.fighting))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, NatureListActivity::class.java)) }
    addView(this)
  }

  @ExperimentalMaterialApi
  private val eggButton = AppCompatButton(context).apply {
    text = "宝可梦培育（蛋）教学"
    background.setTint(context.getColor(R.color.md_green_100))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, EggActivity::class.java)) }
    addView(this)
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
    background.setTint(context.getColor(R.color.md_green_200))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, DonationActivity::class.java)) }
    addView(this)
  }


}