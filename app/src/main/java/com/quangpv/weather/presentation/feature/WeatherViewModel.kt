package com.quangpv.weather.presentation.feature

import android.support.core.event.LiveDataStatusOwner
import android.support.core.event.WindowStatusOwner
import androidx.lifecycle.ViewModel
import com.quangpv.weather.domain.usecase.SearchWeatherCase
import com.quangpv.weather.presentation.extension.launch
import kotlinx.coroutines.Job

class WeatherViewModel(private val searchWeatherCase: SearchWeatherCase) : ViewModel(),
    WindowStatusOwner by LiveDataStatusOwner() {
    private var mSearchJob: Job? = null
    val weathers = searchWeatherCase.result

    fun search(searchKey: String) {
        mSearchJob?.cancel()
        mSearchJob = launch(error = error) { searchWeatherCase(searchKey) }
    }

}