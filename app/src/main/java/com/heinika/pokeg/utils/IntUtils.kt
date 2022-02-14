package com.heinika.pokeg.utils

val Int.toFormString: String
  get() = when(this){
    2 -> " 阿罗拉的样子"
    3 -> " 伽勒尔的样子"
    4 -> " 洗翠的样子"
    5 -> " 白条纹的样子"
    else -> ""
  }