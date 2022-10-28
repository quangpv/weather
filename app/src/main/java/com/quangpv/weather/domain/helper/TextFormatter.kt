package com.quangpv.weather.domain.helper

import android.support.di.Inject
import android.support.di.ShareScope
import com.quangpv.weather.shared.testing.OpenForTesting
import java.text.SimpleDateFormat
import java.util.*

@OpenForTesting
@Inject(ShareScope.Singleton)
class TextFormatter {

    fun formatDate(timestamp: Long): String {
        return SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault()).format(Date(timestamp))
    }

    fun formatTemp(tempEve: Double): String {
        if (tempEve == 0.0) return "0"
        val degreesCelsius = tempEve - 273.15
        val round = String.format("%.2f", degreesCelsius)
        return "$round \u2103"
    }
}