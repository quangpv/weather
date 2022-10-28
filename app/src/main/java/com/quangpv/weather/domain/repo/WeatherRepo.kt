package com.quangpv.weather.domain.repo

import com.quangpv.weather.domain.model.dataobj.WeatherDO


interface WeatherRepo {
    suspend fun search(query: String): List<WeatherDO>
}