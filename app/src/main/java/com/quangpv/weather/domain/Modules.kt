package com.quangpv.weather.domain

import android.support.di.module
import com.quangpv.weather.domain.model.factory.WeatherFactory
import com.quangpv.weather.domain.usecase.SearchWeatherCase
import com.quangpv.weather.domain.v1.SearchWeatherCaseImpl
import com.quangpv.weather.domain.v1.WeatherFactoryImpl

val domainModule = module {
    modules(v1Module)
}

private val v1Module = module {
    factory<WeatherFactory> { WeatherFactoryImpl(get()) }
    factory<SearchWeatherCase> { SearchWeatherCaseImpl(get(), get()) }
}