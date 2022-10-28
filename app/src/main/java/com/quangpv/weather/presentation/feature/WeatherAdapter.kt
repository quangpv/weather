package com.quangpv.weather.presentation.feature

import android.annotation.SuppressLint
import android.support.core.view.RecyclerAdapter
import android.support.core.view.RecyclerHolder
import android.support.core.view.bindingOf
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.quangpv.weather.databinding.ItemWeatherBinding
import com.quangpv.weather.domain.model.ui.IWeather

class WeatherAdapter : RecyclerAdapter<IWeather>() {

    private var mDecoration: RecyclerView.ItemDecoration? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        if (mDecoration == null) {
            mDecoration = DividerItemDecoration(recyclerView.context,
                RecyclerView.VERTICAL)
            recyclerView.addItemDecoration(mDecoration!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = parent.bindingOf(ItemWeatherBinding::inflate)

        return object : RecyclerHolder<IWeather>(binding) {

            @SuppressLint("SetTextI18n")
            override fun bind(item: IWeather) {
                super.bind(item)
                binding.txtAvgTemp.text = "Average Temperature: ${item.avgTemperature}"
                binding.txtDate.text = "Date: ${item.date}"
                binding.txtDescription.text = "Description: ${item.description}"
                binding.txtHumidity.text = "Humidity: ${item.humidity}"
                binding.txtPressure.text = "Pressure: ${item.pressure}"
            }
        }
    }

}