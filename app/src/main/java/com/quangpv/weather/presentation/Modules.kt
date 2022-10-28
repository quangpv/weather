package com.quangpv.weather.presentation

import android.support.core.savedstate.SavedStateHandlerFactory
import android.support.di.LifecycleLookup
import android.support.di.ShareScope
import android.support.di.module

val appModule = module {

    factory(shareIn = ShareScope.FragmentOrActivity) {
        val owner = (this as LifecycleLookup).owner
        SavedStateHandlerFactory(owner).create()
    }
}