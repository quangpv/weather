package com.quangpv.weather.presentation.helper

import com.quangpv.weather.presentation.widget.ErrorDialogOwner

interface ErrorHandler {
    fun handle(e: Throwable)
}

class ErrorHandlerImpl(
    private val errorDialogOwner: ErrorDialogOwner,
) : ErrorHandler {
    override fun handle(e: Throwable) {
        errorDialogOwner.errorDialog.show(e)
    }
}