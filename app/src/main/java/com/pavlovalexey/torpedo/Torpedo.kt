package com.pavlovalexey.torpedo
// модуль Koin
import android.app.Application
import com.pavlovalexey.torpedo.di.gameModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Torpedo : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Torpedo)
            modules(gameModule)
        }
    }
}