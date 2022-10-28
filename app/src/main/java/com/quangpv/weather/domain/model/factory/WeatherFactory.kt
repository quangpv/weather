package com.quangpv.weather.domain.model.factory

import com.quangpv.weather.domain.model.dataobj.WeatherDO
import com.quangpv.weather.domain.model.ui.IWeather

interface WeatherFactory {
    fun create(it: WeatherDO): IWeather
}