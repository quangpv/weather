package com.quangpv.weather.domain.v1

import com.quangpv.weather.domain.helper.TextFormatter
import com.quangpv.weather.domain.model.factory.WeatherFactory
import com.quangpv.weather.domain.model.dataobj.WeatherDO
import com.quangpv.weather.domain.model.ui.IWeather

class WeatherFactoryImpl(private val textFormatter: TextFormatter) : WeatherFactory {
    override fun create(it: WeatherDO): IWeather {
        return object : IWeather {
            override val date: String
                get() = textFormatter.formatDate((it.dt ?: 0) * 1000)
            override val avgTemperature: String
                get() = textFormatter.formatTemp(it.tempEve ?: 0.0)
            override val pressure: String get() = (it.pressure ?: 0).toString()
            override val humidity: String get() = "${it.humidity ?: 0}%"
            override val description: String get() = it.description.orEmpty()
        }
    }
}