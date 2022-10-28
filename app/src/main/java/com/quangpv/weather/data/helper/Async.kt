package com.quangpv.weather.data.helper

import com.quangpv.weather.shared.exception.ServerResponseNullException

interface Async<R> {
    suspend fun awaitNullable(): R?
    suspend fun clone(): Async<R>
    suspend fun await(): R {
        return awaitNullable() ?: throw ServerResponseNullException()
    }
}