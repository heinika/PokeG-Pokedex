package com.heinika.pokeg.info

import android.content.Context
import com.heinika.pokeg.R

enum class DexType{
  Global,
  HiSui;

  fun toDescString(context: Context) : String{
    return when(this){
      Global -> context.getString(R.string.global_pokedex)
      HiSui -> context.getString(R.string.hisui_pokedex)
    }
  }

  fun next(): DexType{
    return when(this){
      Global -> HiSui
      HiSui -> Global
    }
  }
}