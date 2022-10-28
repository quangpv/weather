package com.quangpv.weather.presentation.extension

import android.support.core.event.ErrorEvent
import android.support.core.event.LoadingEvent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


fun ViewModel.launch(
    loading: LoadingEvent? = null,
    error: ErrorEvent? = null,
    context: CoroutineContext = EmptyCoroutineContext,
    function: suspend CoroutineScope.() -> Unit,
): Job {
    val handler = CoroutineExceptionHandler { _, throwable ->
        if (throwable !is CancellationException) {
            throwable.printStackTrace()
            error?.post(throwable)
        }
    }
    return viewModelScope.launch(context = context + handler) {
        try {
            loading?.post(true)
            function()
        } finally {
            loading?.post(false)
        }
    }
}