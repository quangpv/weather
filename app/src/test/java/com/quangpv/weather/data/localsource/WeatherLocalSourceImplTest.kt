package com.quangpv.weather.data.localsource

import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

internal class WeatherLocalSourceImplTest {
    private val time = mock<CurrentTimeRetrievable>()
    private val source = WeatherLocalSourceImpl(time)

    @Test
    fun `the all data expired should be removed from cache before save a new list`() {
        whenever(time.currentTimeMillis()).thenReturn(1)

        source.saveList("test", listOf(mock(), mock()))
        source.saveList("test1", listOf(mock(), mock()))

        whenever(time.currentTimeMillis()).thenReturn(1L + WeatherLocalSourceImpl.TIME_TO_LIVE)
        source.saveList("test2", listOf(mock(), mock()))

        assert(source.retrieveList("test").isEmpty())
        assert(source.retrieveList("test1").isEmpty())
        assert(source.retrieveList("test2").size == 2)
    }

    @Test
    fun `if save a not empty list, the data retrieved is not empty`() {
        whenever(time.currentTimeMillis()).thenReturn(1)
        source.saveList("test", listOf(mock()))
        assert(source.retrieveList("test").size == 1)
    }
}