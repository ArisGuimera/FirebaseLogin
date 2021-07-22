package com.aristidevs.nuwelogin

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import timber.log.Timber.DebugTree


@HiltAndroidApp
class NuweLoginApp:Application(){
    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }
}