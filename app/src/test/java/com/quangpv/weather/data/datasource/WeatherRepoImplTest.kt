package com.quangpv.weather.data.datasource

import com.quangpv.weather.data.helper.MockAsync
import com.quangpv.weather.data.localsource.WeatherLocalSource
import com.quangpv.weather.data.model.CityWeatherDTO
import com.quangpv.weather.data.model.WeatherDTO
import com.quangpv.weather.data.remotesource.WeatherApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

internal class WeatherRepoImplTest {
    private val localSource = mock<WeatherLocalSource>()
    private val remoteSource = mock<WeatherApi>()

    private val repo = WeatherRepoImpl(localSource, remoteSource)
    private val query = "k1"
    private val cnt = 7
    private val appId = "60c6fbeb4b93ac653c492ba806fc346d"

    @Test
    fun `do not to fetch from remote, if data from local source available`() {
        runBlocking {
            whenever(remoteSource.get(query,
                cnt,
                appId)).thenReturn(spy(MockAsync(CityWeatherDTO())))
            whenever(localSource.retrieveList(query)).thenReturn(emptyList())
            repo.search(query)

            verify(localSource, only()).retrieveList(query)
            verify(remoteSource.get(query, cnt, appId), never()).await()
        }
    }

    @Test
    fun `fetch from remote, if data from local source not available`() {
        runBlocking {
            whenever(
                remoteSource.get(query,
                    cnt,
                    appId)
            ).thenReturn(spy(MockAsync(CityWeatherDTO(list = listOf(WeatherDTO())))))

            whenever(localSource.retrieveList(query)).thenReturn(emptyList())
            val result = repo.search(query)
            assert(result.size == 1)

            verify(localSource, times(1)).retrieveList(query)
            verify(remoteSource.get(query, cnt, appId), only()).await()
        }
    }
}