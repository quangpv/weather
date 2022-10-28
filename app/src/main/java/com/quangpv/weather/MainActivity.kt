package com.quangpv.weather

import android.support.navigation.Navigator
import com.quangpv.weather.presentation.base.NavigationActivity
import com.quangpv.weather.presentation.feature.WeatherFragment

class MainActivity : NavigationActivity() {
    override fun startNavigate(navigator: Navigator) {
        navigator.navigate(WeatherFragment::class)
    }
}