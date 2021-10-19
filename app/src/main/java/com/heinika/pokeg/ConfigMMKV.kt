package com.heinika.pokeg

import com.tencent.mmkv.MMKV

object ConfigMMKV {
  private var kv = MMKV.defaultMMKV()
  private const val DEFAULT_VERSION_KEY = "DEFAULT_VERSION_KEY"

  var defaultVersion: Int
    set(value) {
      kv.putInt(DEFAULT_VERSION_KEY, value)
    }
    get() {
      return kv.getInt(DEFAULT_VERSION_KEY, 20)
    }
}