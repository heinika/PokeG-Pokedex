package com.heinika.pokeg.init

import android.content.Context
import androidx.startup.Initializer
import com.heinika.pokeg.BuildConfig
import com.tencent.mmkv.MMKV
import timber.log.Timber
import java.io.FileOutputStream

@Suppress("unused")
class PokeGInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        val file = context.getDatabasePath("PokeG.db")
        if (!file.exists()){
            context.assets.open("PokeG_Base.db").copyTo(FileOutputStream(context.getDatabasePath("PokeG.db")))
        }
        MMKV.initialize(context)
    }

    override fun dependencies() = emptyList<Class<out Initializer<*>>>()
}