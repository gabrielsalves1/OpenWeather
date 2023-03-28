package com.gawit.openweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gawit.openweather.api.WeatherApi
import com.gawit.openweather.model.Weather

class WeatherViewModel : ViewModel() {
    fun getWeather(lat: String, lon: String, appid: String): Weather? {
        return WeatherApi.RetrofitInstance.api.getWeather(lat, lon, appid)
            .execute().body()
    }
}