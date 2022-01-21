package com.heinika.pokeg.utils

import com.heinika.pokeg.module.gameprops.props.*
import timber.log.Timber

fun findCarryByCName(name: String): CarryProps {
  Timber.i("findCarryByName: $name")
  return when {
    carryIIPropsList.any { it.cname == name } -> carryIIPropsList.first { it.cname == name }
    carryIIIPropsList.any { it.cname == name } -> carryIIIPropsList.first { it.cname == name }
    carryIVPropsList.any { it.cname == name } -> carryIVPropsList.first { it.cname == name }
    carryVPropsList.any { it.cname == name } -> carryVPropsList.first { it.cname == name }
    carryVIPropsList.any { it.cname == name } -> carryVIPropsList.first { it.cname == name }
    carryVIIPropsList.any { it.cname == name } -> carryVIIPropsList.first { it.cname == name }
    carryVIIIPropsList.any { it.cname == name } -> carryVIIIPropsList.first { it.cname == name }
    else -> carryIIPropsList[0]
  }
}