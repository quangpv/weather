package com.quangpv.weather.presentation.helper

import android.support.core.view.ViewScopeOwner
import com.quangpv.weather.presentation.widget.ErrorDialogOwner

interface ErrorHandlerOwner : ViewScopeOwner, ErrorDialogOwner {
    val errorHandler: ErrorHandler
        get() = viewScope.getOr("app:error:handler") {
            ErrorHandlerImpl(this)
        }
}