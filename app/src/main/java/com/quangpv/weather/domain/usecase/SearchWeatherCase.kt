package com.quangpv.weather.domain.usecase

import androidx.lifecycle.LiveData
import com.quangpv.weather.domain.model.ui.IWeather

interface SearchWeatherCase {
    val result: LiveData<List<IWeather>>
    suspend operator fun invoke(query: String)
}