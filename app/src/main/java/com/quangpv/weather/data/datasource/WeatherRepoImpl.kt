package com.quangpv.weather.data.datasource

import com.quangpv.weather.data.localsource.WeatherLocalSource
import com.quangpv.weather.data.remotesource.WeatherApi
import com.quangpv.weather.domain.model.dataobj.WeatherDO
import com.quangpv.weather.domain.repo.WeatherRepo

class WeatherRepoImpl(
    private val localSource: WeatherLocalSource,
    private val remoteSource: WeatherApi,
) : WeatherRepo {

    override suspend fun search(query: String): List<WeatherDO> {
        val localList = localSource.retrieveList(query)
        if (localList.isNotEmpty()) {
            return localList
        }
        val remoteList = remoteSource.get(query).await().list.orEmpty()

        localSource.saveList(query, remoteList)
        return remoteList
    }

}