package com.quangpv.weather.presentation.feature

import android.os.Bundle
import android.support.core.view.viewBinding
import android.support.viewmodel.viewModel
import android.view.View
import com.quangpv.weather.R
import com.quangpv.weather.databinding.FragmentWeatherBinding
import com.quangpv.weather.presentation.base.AppFragment
import com.quangpv.weather.presentation.extension.hideKeyboard

class WeatherFragment : AppFragment(R.layout.fragment_weather) {
    private val viewModel by viewModel<WeatherViewModel>()
    private val binding by viewBinding(FragmentWeatherBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = WeatherAdapter()

        viewModel.weathers.bind {
            adapter.submit(it)
        }

        binding.btnSearch.setOnClickListener {
            viewModel.search(binding.edtSearch.text.toString())
            binding.edtSearch.hideKeyboard()
        }
        binding.rvList.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.search(binding.edtSearch.text.toString())
    }
}
