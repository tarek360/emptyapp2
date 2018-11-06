package co.louga.presentation

import android.support.multidex.MultiDexApplication
import co.louga.data.net.Backend
import co.louga.presentation.di.*
import org.koin.android.ext.android.startKoin

open class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initComponent()
        Backend.instance.start(this)
    }

    fun initComponent() {
        startKoin(this, allModules)
    }
}
