package com.heinika.pokeg.info

enum class DexType{
  Global,
  HiSui;

  fun toDescString() : String{
    return when(this){
      Global -> "全国图鉴"
      HiSui -> "阿尔宙斯图鉴"
    }
  }

  fun next(): DexType{
    return when(this){
      Global -> HiSui
      HiSui -> Global
    }
  }
}