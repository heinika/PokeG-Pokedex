package com.heinika.pokeg.module.gameprops.props

import androidx.compose.ui.graphics.Color
import com.heinika.pokeg.info.Generation

sealed class GameProps(val nameResId: Int, val flavorResId: Int, val imageResId: Int)

class BerryProp(val generation: Generation, nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class ImportantProp(val gen: Generation, nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class Mail(val gen: Generation, nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class CarryProps(
  val cname: String,
  val gen: Generation,
  nameResId: Int,
  flavorResId: Int,
  imageResId: Int
) :
  GameProps(nameResId, flavorResId, imageResId)

class EvolutionProp(val color: Color, nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class Pokeball(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class Fossil(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class SwapProps(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class RecoveryProp(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class Apricorn(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class PreciousProp(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class BattleProp(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class RoToProp(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class CandyProp(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class FieldProp(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class FoodProp(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class ZProp(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)

class TCGProp(nameResId: Int, flavorResId: Int, imageResId: Int) :
  GameProps(nameResId, flavorResId, imageResId)





