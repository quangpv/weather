package com.quangpv.weather.domain.model.ui

interface IWeather {
    val date: String
    val avgTemperature: String
    val pressure: String
    val humidity: String
    val description: String
}