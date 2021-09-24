package com.yazantarifi.palex

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication

class AppInstance: MultiDexApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}