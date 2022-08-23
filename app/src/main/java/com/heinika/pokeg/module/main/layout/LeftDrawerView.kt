@file:Suppress("unused")

package com.heinika.pokeg.module.main.layout

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.pager.ExperimentalPagerApi
import com.heinika.pokeg.ConfigMMKV
import com.heinika.pokeg.R
import com.heinika.pokeg.module.about.AboutActivity
import com.heinika.pokeg.module.donation.DonationActivity
import com.heinika.pokeg.module.egg.EggActivity
import com.heinika.pokeg.module.gameprops.GamePropsActivity
import com.heinika.pokeg.module.moves.MoveListActivity
import com.heinika.pokeg.module.mypokemon.MyPokemonActivity
import com.heinika.pokeg.module.nature.NatureListActivity
import com.heinika.pokeg.module.team.TeamActivity
import com.heinika.pokeg.module.typedetail.TypeDetailActivity
import com.heinika.pokeg.module.versions.VersionsActivity
import com.heinika.pokeg.utils.SystemBar
import com.heinika.pokeg.utils.isGooglePlayFlavor


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
      context.resources.getString(
        R.string.default_version,
        context.resources.getStringArray(R.array.versions)[ConfigMMKV.defaultVersion - 1]
      )
    addView(this)
    (layoutParams as LayoutParams).topMargin = SystemBar.statusBarHeight
    background.setTint(context.getColor(R.color.md_amber_700))
    setTextColor(Color.WHITE)

    val selectVersionDialog = AlertDialog.Builder(context).setTitle("选择默认版本")
      .setItems(
        context.resources.getStringArray(R.array.versions)
      ) { dialog, index ->
        ConfigMMKV.defaultVersion = index + 1
        text = context.resources.getString(
          R.string.default_version,
          context.resources.getStringArray(R.array.versions)[ConfigMMKV.defaultVersion - 1]
        )
        dialog.dismiss()
      }.create()

    setOnClickListener {
      selectVersionDialog.show()
    }
  }

  
  @SuppressLint("SetTextI18n")
  private val versionsButton = AppCompatButton(context).apply {
    text = context.resources.getString(R.string.version_list)
    background.setTint(context.getColor(R.color.generation_2))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, VersionsActivity::class.java)) }
    addView(this)
  }


  @ExperimentalCoilApi
  @ExperimentalMaterialApi
  private val typeDetailButton = AppCompatButton(context).apply {
    text = context.resources.getString(R.string.type_detail)
    background.setTint(context.getColor(R.color.yellow))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, TypeDetailActivity::class.java)) }
    addView(this)
  }

  @ExperimentalMaterialApi
  private val movesButton = AppCompatButton(context).apply {
    text = context.resources.getString(R.string.move_list)
    background.setTint(context.getColor(R.color.md_blue_200))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, MoveListActivity::class.java)) }
    addView(this)
  }

  
  @ExperimentalCoilApi
  @ExperimentalMaterialApi
  private val myPokemonButton = AppCompatButton(context).apply {
    text = context.resources.getString(R.string.my_pokemon)
    background.setTint(context.getColor(R.color.base_status_sdef))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, MyPokemonActivity::class.java)) }
    addView(this)
  }

  
  @ExperimentalCoilApi
  @ExperimentalMaterialApi
  private val teamsButton = AppCompatButton(context).apply {
    text = context.resources.getString(R.string.team)
    background.setTint(context.getColor(R.color.base_status_sdef))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, TeamActivity::class.java)) }
    addView(this)
  }

  @ExperimentalPagerApi
  @ExperimentalMaterialApi
  private val gamePropsButton = AppCompatButton(context).apply {
    text = context.resources.getString(R.string.props_list)
    background.setTint(context.getColor(R.color.base_status_sum))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, GamePropsActivity::class.java)) }
    addView(this)
  }

  @ExperimentalMaterialApi
  private val natureButton = AppCompatButton(context).apply {
    text = context.resources.getString(R.string.nature_list)
    background.setTint(context.getColor(R.color.fighting))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, NatureListActivity::class.java)) }
    addView(this)
  }

  @ExperimentalMaterialApi
  private val eggButton = AppCompatButton(context).apply {
    text = context.resources.getString(R.string.egg_tutorial)
    background.setTint(context.getColor(R.color.md_green_100))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, EggActivity::class.java)) }
    addView(this)
  }

  private val aboutButton = AppCompatButton(context).apply {
    text = context.resources.getString(R.string.about)
    addView(this)
    background.setTint(context.getColor(R.color.colorPrimary))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, AboutActivity::class.java)) }
  }

  private val donationButton = AppCompatButton(context).apply {
    text = context.resources.getString(R.string.donation)
    background.setTint(context.getColor(R.color.md_green_200))
    setTextColor(Color.WHITE)

    setOnClickListener { context.startActivity(Intent(context, DonationActivity::class.java)) }
    if (!isGooglePlayFlavor()) {
      addView(this)
    }
  }

}