package com.quangpv.weather.domain.v1

import android.support.core.livedata.post
import androidx.lifecycle.MutableLiveData
import com.quangpv.weather.domain.model.factory.WeatherFactory
import com.quangpv.weather.domain.model.ui.IWeather
import com.quangpv.weather.domain.repo.WeatherRepo
import com.quangpv.weather.domain.usecase.SearchWeatherCase
import com.quangpv.weather.shared.exception.SearchQueryException

class SearchWeatherCaseImpl(
    private val factory: WeatherFactory,
    private val weatherRepo: WeatherRepo,
) : SearchWeatherCase {
    override val result = MutableLiveData<List<IWeather>>()

    override suspend fun invoke(query: String) {
        if (query.length < 3 && query.isNotEmpty()) {
            throw SearchQueryException()
        }
        val searchKey = query.lowercase().ifBlank { "saigon" }
        val data = weatherRepo.search(searchKey)
        val weathers = data.map { factory.create(it) }
        result.post(weathers)
    }
}