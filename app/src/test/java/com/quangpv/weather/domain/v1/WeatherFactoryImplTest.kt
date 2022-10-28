package com.quangpv.weather.domain.v1

import com.quangpv.weather.domain.helper.TextFormatter
import com.quangpv.weather.domain.model.dataobj.WeatherDO
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.spy
import org.mockito.kotlin.whenever

internal class WeatherFactoryImplTest {
    private val textFormatter = spy<TextFormatter>()
    private val factory = WeatherFactoryImpl(textFormatter)

    @Test
    fun `happy case`() {
        val weatherDTO = mock<WeatherDO>()
        val weather = factory.create(weatherDTO)

        whenever(textFormatter.formatDate(any())).thenReturn("Date")
        whenever(textFormatter.formatTemp(any())).thenReturn("Temp")
        whenever(weatherDTO.pressure).thenReturn(2)
        whenever(weatherDTO.humidity).thenReturn(2)
        whenever(weatherDTO.description).thenReturn("Desc")

        assert(weather.date == "Date")
        assert(weather.avgTemperature == "Temp")
        assert(weather.pressure == "2")
        assert(weather.humidity == "2%")
        assert(weather.description == "Desc")
    }
}