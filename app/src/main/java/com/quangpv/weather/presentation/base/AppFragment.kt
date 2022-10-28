package com.quangpv.weather.presentation.base

import android.support.core.event.WindowStatusOwner
import android.support.core.extensions.LifecycleSubscriberExt
import android.support.viewmodel.ViewModelRegistrable
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.quangpv.weather.presentation.helper.ErrorHandlerOwner

abstract class AppFragment(contentLayoutId: Int) : Fragment(contentLayoutId),
    ViewModelRegistrable,
    LifecycleSubscriberExt,
    ErrorHandlerOwner {

    val self get() = this

    override fun onRegistryViewModel(viewModel: ViewModel) {
        if (viewModel is WindowStatusOwner) {
            viewModel.error.bind { errorHandler.handle(it) }
        }
    }
}