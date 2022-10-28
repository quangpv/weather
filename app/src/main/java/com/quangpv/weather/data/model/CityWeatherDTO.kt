package com.quangpv.weather.data.model

import com.google.gson.annotations.SerializedName
import com.quangpv.weather.domain.model.dataobj.WeatherDO

data class CityWeatherDTO(
    val city: CityDTO? = null,
    val cod: String? = null,
    val message: Double? = null,
    val cnt: Long? = null,
    val list: List<WeatherDTO>? = null,
)

data class CityDTO(
    val id: Long? = null,
    val name: String? = null,
    val coord: CoordDTO? = null,
    val country: String? = null,
    val population: Long? = null,
    val timezone: Long? = null,
)

data class CoordDTO(
    val lon: Double? = null,
    val lat: Double? = null,
)

data class WeatherDTO(
    override val dt: Long? = null,
    val sunrise: Long? = null,
    val sunset: Long? = null,
    val temp: TempDTO? = null,

    @SerializedName("feels_like")
    val feelsLike: FeelsLikeDTO? = null,

    override val pressure: Long? = null,
    override val humidity: Long? = null,
    val weather: List<WeatherInfoDTO>? = null,
    val speed: Double? = null,
    val deg: Long? = null,
    val gust: Double? = null,
    val clouds: Long? = null,
    val pop: Double? = null,
    val rain: Double? = null,
) : WeatherDO {
    override val tempEve: Double?
        get() = temp?.eve
    override val description: String?
        get() = weather?.firstOrNull()?.description

}

data class FeelsLikeDTO(
    val day: Double? = null,
    val night: Double? = null,
    val eve: Double? = null,
    val morn: Double? = null,
)

data class TempDTO(
    val day: Double? = null,
    val min: Double? = null,
    val max: Double? = null,
    val night: Double? = null,
    val eve: Double? = null,
    val morn: Double? = null,
)

data class WeatherInfoDTO(
    val id: Long? = null,
    val main: String? = null,
    val description: String? = null,
    val icon: String? = null,
)
