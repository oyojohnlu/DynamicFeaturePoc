package com.oyo.mobile.dynamicfeaturepoc

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import com.oyo.mobile.dynamicfeaturepoc.di.featureModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        init()
    }

    private fun init() {
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(featureModules)
        }
        SplitCompat.install(this)
    }
}