package com.quangpv.weather.domain.helper

import org.junit.Test
import java.util.*

internal class TextFormatterTest {
    private val formatter = TextFormatter()

    @Test
    fun `date formatted should be EEE, dd MMM yyyy`() {
        val date = Calendar.getInstance().apply {
            set(Calendar.YEAR, 2022)
            set(Calendar.MONTH, 1)
            set(Calendar.DATE, 1)
        }
        println(formatter.formatDate(date.timeInMillis))
    }

    @Test
    fun temperature() {
        println(formatter.formatTemp(3.0))
    }
}