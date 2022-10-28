package com.quangpv.weather.data.helper

import com.google.gson.Gson
import com.quangpv.weather.shared.exception.ApiRequestException
import com.quangpv.weather.shared.exception.InternalServerException
import com.quangpv.weather.shared.exception.ParameterInvalidException
import com.quangpv.weather.shared.exception.UnAuthorException
import retrofit2.Response

class ApiErrorHandlerImpl : DefaultApiErrorHandler() {
    private val parser = Gson()
    override fun onResponseError(response: Response<out Any>): Throwable {
        val errorBody = response.errorBody()?.string()
            ?: response.message()
        val errorCode = response.code()

        val message = errorBody.toString()
        return when {
            errorCode == 401 -> UnAuthorException(message)
            errorCode in 400..499 -> ApiRequestException(parser.fromJson(message,
                ApiError::class.java).message)
            errorCode >= 500 -> InternalServerException()
            else -> ParameterInvalidException(message)
        }
    }
}

class ApiError(
    val message: String,
)