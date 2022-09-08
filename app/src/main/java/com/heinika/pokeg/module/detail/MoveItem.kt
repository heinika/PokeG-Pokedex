package com.heinika.pokeg.module.detail

data class MoveItem(
  val id: Int,
  val methodId: Int,
  val name: String,
  val level: Int,
  var type: String,
  var typeColor: Int,
  var damageClass: String,
  var pp: Int,
  var power: Int,
  var accuracy: Int
)