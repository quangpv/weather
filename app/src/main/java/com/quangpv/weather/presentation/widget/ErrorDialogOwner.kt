package com.quangpv.weather.presentation.widget

import android.support.core.view.ViewScopeOwner

interface ErrorDialogOwner : ViewScopeOwner {
    val errorDialog: IErrorDialog
        get() = viewScope.getOr("error:dialog") {
            object : IErrorDialog {
                override fun show(error: Throwable) {
                    DialogFactory.createAlert(viewScope.context, error.message).show()
                }
            }
        }
}

interface IErrorDialog {
    fun show(error: Throwable)
}