package com.gawit.openweather.ui.weather

import androidx.lifecycle.ViewModel
import com.gawit.openweather.api.WeatherApi
import com.gawit.openweather.model.City
import com.gawit.openweather.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel() {
    fun getWeather(lat: String, lon: String, appid: String): Weather? {
        return WeatherApi.RetrofitInstance.api.getWeather(lat, lon, appid)
            .execute().body()
    }

    fun getCity(city: String, limit: Int, appid: String): List<City>? {
        return WeatherApi.RetrofitInstance.api.getCity(city, limit, appid)
            .execute().body()
    }
}