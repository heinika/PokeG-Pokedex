package com.heinika.pokeg.ui.detail.itemdelegate.model

data class MoveItem(
  val id: Int,
  val methodId: Int,
  val name: String,
  val level: Int,
  var type: String,
  var typeColor: Int,
  var damageClass: String,
  var pp: String,
  var power: String,
  var accuracy: String
)