package com.quangpv.weather.domain.v1

import com.quangpv.weather.domain.model.factory.WeatherFactory
import com.quangpv.weather.domain.model.ui.IWeather
import com.quangpv.weather.domain.repo.WeatherRepo
import com.quangpv.weather.instrument.LiveDataRule
import com.quangpv.weather.shared.exception.SearchQueryException
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class SearchWeatherCaseImplTest {
    @get:Rule
    val liveDataRule = LiveDataRule()

    private val factory = mock<WeatherFactory>()
    private val repo = mock<WeatherRepo>()
    private val case = SearchWeatherCaseImpl(factory, repo)

    @Before
    fun setup() {
        whenever(factory.create(any())).thenReturn(mock())
        runBlocking {
            whenever(repo.search(any())).thenReturn(listOf(mock(), mock()))
        }
    }

    @Test
    fun `just accept query has length great than 3 and query is empty`() {
        Assert.assertThrows(SearchQueryException::class.java) {
            runBlocking { case("a") }
        }
        Assert.assertThrows(SearchQueryException::class.java) {
            runBlocking { case("ab") }
        }
        runBlocking {
            case("")
            case("abc")
        }
    }

    @Test
    fun `default search key should be saigon if blank`() {
        runBlocking {
            val queryCaptor = argumentCaptor<String>()
            whenever(repo.search(queryCaptor.capture())).thenReturn(listOf(mock(), mock()))
            case("")

            assert(queryCaptor.lastValue == "saigon")
        }
    }

    @Test
    fun `search should have data if query is valid`() {
        runBlocking {
            whenever(repo.search("")).thenReturn(listOf(mock(), mock()))
            case("")
            assert(case.result.value!!.size == 2)
        }
    }


}