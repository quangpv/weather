package com.quangpv.weather.presentation.base

import android.os.Bundle
import android.support.navigation.Navigator
import android.support.navigation.findNavigator
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import com.quangpv.weather.R

abstract class NavigationActivity : AppCompatActivity(R.layout.activity_navigation) {

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onNavCreate(savedInstanceState)
        if (savedInstanceState == null) {
            startNavigate(findNavigator())
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (!findNavigator().navigateUp()) finish()
            }
        })
    }

    open fun onNavCreate(savedInstanceState: Bundle?) {}

    abstract fun startNavigate(navigator: Navigator)

}