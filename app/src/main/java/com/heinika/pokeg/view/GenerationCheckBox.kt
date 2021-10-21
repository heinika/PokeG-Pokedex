package com.heinika.pokeg.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.widget.AppCompatCheckBox
import com.heinika.pokeg.R
import com.heinika.pokeg.utils.PokemonProp
import com.heinika.pokeg.utils.dp


@SuppressLint("ViewConstructor")
class GenerationCheckBox constructor(context: Context) : AppCompatCheckBox(context) {
  init {
    textAlignment = TEXT_ALIGNMENT_CENTER
    setTextColor(Color.WHITE)
    buttonDrawable = null
    val drawable = resources.getDrawable(R.drawable.check_box_basestatus_normal)
    setBackgroundDrawable(drawable)
    setOnCheckedChangeListener { _, isChecked ->
      if (isChecked) {
        (background as GradientDrawable).setStroke(2.dp, Color.WHITE)
      } else {
        (background as GradientDrawable).setStroke(0, Color.WHITE)
      }
    }
    (background as GradientDrawable).setStroke(0, Color.WHITE)
  }

  fun setBaseStatus(generation: PokemonProp.Generation){
    text = generation.filterString
    buttonDrawable = null
    val drawable = resources.getDrawable(R.drawable.check_box_basestatus_normal)
    drawable.setTint(generation.getColor(context))
    drawable.setTintMode(PorterDuff.Mode.ADD)
    setBackgroundDrawable(drawable)
  }
}