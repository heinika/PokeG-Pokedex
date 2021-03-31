package com.heinika.pokeg.init

import android.content.Context
import androidx.startup.Initializer
import com.heinika.pokeg.BuildConfig
import timber.log.Timber

@Suppress("unused")
class TimberInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun dependencies() = emptyList<Class<out Initializer<*>>>()
}