package com.gawit.openweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gawit.openweather.api.WeatherApi
import com.gawit.openweather.model.Weather

class WeatherViewModel : ViewModel() {
    var weather = MutableLiveData<Weather?>()

    init {
        weather.value = getWeather()
    }

    private fun getWeather(): Weather? {
        return WeatherApi.RetrofitInstance.api.getWeather("-23.6927250675208", "-46.59553951531837", "92fb97c9f7251535cb9d3869bfa39f5a")
            .execute().body()
    }
}