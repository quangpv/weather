package com.quangpv.weather

import android.app.Application
import android.support.di.dependencies
import com.quangpv.weather.data.networkModule
import com.quangpv.weather.data.weatherSourceModule
import com.quangpv.weather.domain.domainModule
import com.quangpv.weather.presentation.appModule

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        dependencies {
            modules(appModule)

            modules(domainModule)

            modules(networkModule)
            modules(weatherSourceModule)
        }
    }
}