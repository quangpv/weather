package com.quangpv.weather.data.remotesource

import android.support.di.Injectable
import com.quangpv.weather.data.helper.Async
import com.quangpv.weather.data.model.CityWeatherDTO
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi : Injectable {
    @GET("forecast/daily")
    fun get(
        @Query("q") searchKey: String,
        @Query("cnt") cnt: Int = 7,
        @Query("appid") appId: String = "60c6fbeb4b93ac653c492ba806fc346d",
    ): Async<CityWeatherDTO>
}

class WeatherApiImpl(private val retrofit: Retrofit) : WeatherApi by retrofit.create()