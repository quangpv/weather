package com.quangpv.weather.data.helper

import retrofit2.Response

interface ApiCallErrorHandler {
    fun onRequestError(throwable: Throwable): Throwable

    fun onResponseError(response: Response<out Any>): Throwable
}