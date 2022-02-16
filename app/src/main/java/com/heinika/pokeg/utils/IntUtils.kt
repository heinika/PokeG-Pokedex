package com.heinika.pokeg.utils

val Int.toFormString: String
  get() = when(this){
    2 -> " 阿罗拉"
    3 -> " 伽勒尔"
    4 -> " 洗翠"
    5 -> " 白条纹"
    else -> ""
  }