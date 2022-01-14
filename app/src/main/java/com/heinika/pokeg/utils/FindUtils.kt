package com.heinika.pokeg.utils

import android.content.Context
import com.heinika.pokeg.module.gameprops.props.*
import timber.log.Timber

fun findCarryByName(name: String, context: Context): CarryProps? {
  Timber.i("findCarryByName: $name")
  return when {
    carryIIPropsList.any { context.getString(it.nameResId) == name } -> carryIIPropsList.first { context.getString(it.nameResId) == name }
    carryIIIPropsList.any { context.getString(it.nameResId) == name } -> carryIIIPropsList.first { context.getString(it.nameResId) == name }
    carryIVPropsList.any { context.getString(it.nameResId) == name } -> carryIVPropsList.first { context.getString(it.nameResId) == name }
    carryVPropsList.any { context.getString(it.nameResId) == name } -> carryVPropsList.first { context.getString(it.nameResId) == name }
    carryVIPropsList.any { context.getString(it.nameResId) == name } -> carryVIPropsList.first { context.getString(it.nameResId) == name }
    carryVIIPropsList.any { context.getString(it.nameResId) == name } -> carryVIIPropsList.first { context.getString(it.nameResId) == name }
    carryVIIIPropsList.any { context.getString(it.nameResId) == name } -> carryVIIIPropsList.first { context.getString(it.nameResId) == name }
    else -> null
  }
}