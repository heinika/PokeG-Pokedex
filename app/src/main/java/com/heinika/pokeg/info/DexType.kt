package com.heinika.pokeg.info

import androidx.annotation.StringRes
import com.heinika.pokeg.R

enum class DexType(@StringRes val stringId:Int){
  Global(R.string.global_pokedex),
  ZhuZi(R.string.global_zhuzi),
  HiSui(R.string.hisui_pokedex);

  fun next(): DexType{
    return when(this){
      Global -> ZhuZi
      ZhuZi -> HiSui
      HiSui -> Global
    }
  }
}