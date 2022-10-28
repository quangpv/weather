package com.quangpv.weather.domain.model.dataobj

interface WeatherDO {
    val dt: Long?
    val tempEve: Double?
    val pressure: Long?
    val humidity: Long?
    val description: String?
}