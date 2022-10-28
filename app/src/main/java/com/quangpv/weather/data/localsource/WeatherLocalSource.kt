package com.quangpv.weather.data.localsource

import com.quangpv.weather.domain.model.dataobj.WeatherDO

interface WeatherLocalSource {
    fun retrieveList(query: String): List<WeatherDO>
    fun saveList(query: String, list: List<WeatherDO>)
}

interface CurrentTimeRetrievable {
    fun currentTimeMillis(): Long
}

class CurrentTimeRetrievableImpl : CurrentTimeRetrievable {
    override fun currentTimeMillis(): Long {
        return System.currentTimeMillis()
    }

}

class WeatherLocalSourceImpl(
    private val time: CurrentTimeRetrievable = CurrentTimeRetrievableImpl(),
) : WeatherLocalSource {
    companion object {
        const val TIME_TO_LIVE = 30000
    }

    private var mListMap = hashMapOf<String, List<WeatherDO>>()
    private var mLastSavedTimeMap = hashMapOf<String, Long>()

    override fun retrieveList(query: String): List<WeatherDO> {
        return synchronized(this) {
            val data = mListMap[query] ?: return@synchronized emptyList()
            val lastSavedTime = mLastSavedTimeMap[query] ?: return@synchronized emptyList()
            val timeNow = time.currentTimeMillis()

            if (!isExpired(timeNow, lastSavedTime)) {
                return@synchronized data
            }

            mListMap.remove(query)
            mLastSavedTimeMap.remove(query)
            return emptyList()
        }
    }

    override fun saveList(query: String, list: List<WeatherDO>) {
        synchronized(this) {
            val now = time.currentTimeMillis()
            checkout(now)
            mListMap[query] = list
            mLastSavedTimeMap[query] = now
        }
    }

    private fun checkout(now: Long) {
        val removes = arrayListOf<String>()
        mLastSavedTimeMap.forEach { (key, value) ->
            if (isExpired(now, value)) removes.add(key)
        }

        removes.forEach {
            mLastSavedTimeMap.remove(it)
            mListMap.remove(it)
        }
    }

    private fun isExpired(timeNow: Long, lastSavedTime: Long): Boolean {
        return timeNow - lastSavedTime >= TIME_TO_LIVE
    }
}