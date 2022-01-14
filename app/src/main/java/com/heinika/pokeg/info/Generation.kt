package com.heinika.pokeg.info

import android.content.Context
import com.heinika.pokeg.R

enum class Generation(
  val id: Int,
  val resId: Int,
  val resColor: Int,
  val filterString: String
) {
  GenerationI(1, R.string.generation_i, R.color.generation_1, "I"),
  GenerationII(2, R.string.generation_ii, R.color.generation_2, "II"),
  GenerationIII(3, R.string.generation_iii, R.color.generation_3, "III"),
  GenerationIV(4, R.string.generation_iv, R.color.generation_4, "IV"),
  GenerationV(5, R.string.generation_v, R.color.generation_5, "V"),
  GenerationVI(6, R.string.generation_vi, R.color.generation_6, "VI"),
  GenerationVII(7, R.string.generation_vii, R.color.generation_7, "VII"),
  GenerationVIII(8, R.string.generation_viii, R.color.generation_8, "VIII");

  fun getName(context: Context): CharSequence {
    return context.getString(resId)
  }

  fun getColor(context: Context): Int {
    return context.getColor(resColor)
  }
}