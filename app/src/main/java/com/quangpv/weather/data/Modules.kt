package com.quangpv.weather.data

import android.app.Application
import android.support.di.ShareScope
import android.support.di.module
import com.google.gson.GsonBuilder
import com.quangpv.weather.data.datasource.WeatherRepoImpl
import com.quangpv.weather.data.helper.AsyncAdapterFactory
import com.quangpv.weather.data.factory.TLSSocketFactory
import com.quangpv.weather.data.helper.ApiErrorHandlerImpl
import com.quangpv.weather.data.localsource.WeatherLocalSource
import com.quangpv.weather.data.localsource.WeatherLocalSourceImpl
import com.quangpv.weather.data.remotesource.WeatherApi
import com.quangpv.weather.data.remotesource.WeatherApiImpl
import com.quangpv.weather.domain.repo.WeatherRepo
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.*
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    single {
        val application: Application = get()
        val cacheDir = File(application.cacheDir, UUID.randomUUID().toString())
        val cache = Cache(cacheDir, 10485760L) // 10mb
        val tlsSocketFactory = TLSSocketFactory()
        OkHttpClient.Builder()
            .sslSocketFactory(tlsSocketFactory, tlsSocketFactory.systemDefaultTrustManager())
            .cache(cache)
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single<Converter.Factory> {
        GsonConverterFactory.create(
            GsonBuilder().create()
        )
    }

    factory<Retrofit.Builder> {
        Retrofit.Builder()
            .addConverterFactory(get())
            .addCallAdapterFactory(AsyncAdapterFactory(ApiErrorHandlerImpl()))
            .client(get())
    }

    single<Retrofit> {
        get<Retrofit.Builder>()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build()
    }
}

val weatherSourceModule = module {

    single<WeatherApi> { WeatherApiImpl(get()) }

    factory<WeatherLocalSource>(shareIn = ShareScope.Activity) {
        WeatherLocalSourceImpl()
    }
    factory<WeatherRepo>(shareIn = ShareScope.Activity) {
        WeatherRepoImpl(get(), get())
    }
}