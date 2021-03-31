package com.heinika.pokeg

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.IO_PARALLELISM_PROPERTY_NAME
import timber.log.Timber
import timber.log.Timber.DebugTree


@HiltAndroidApp
class PokeGApp : Application()