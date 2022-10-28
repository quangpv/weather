package com.quangpv.weather.presentation.widget

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context

object DialogFactory {
    fun createAlert(context: Context, message: String?): Dialog {
        return AlertDialog.Builder(context)
            .setTitle("Error")
            .setMessage(message ?: "Unknown")
            .create()
    }
}