package com.gawit.openweather.ui.weather

import androidx.lifecycle.ViewModel
import com.gawit.openweather.api.WeatherApi
import com.gawit.openweather.model.City
import com.gawit.openweather.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherViewModel : ViewModel() {
    suspend fun getWeather(lat: String, lon: String, appid: String): Weather? {
        return withContext(Dispatchers.IO) {
            try {
                WeatherApi.RetrofitInstance.api.getWeather(lat, lon, appid).body()
            } catch (cause: Throwable) {
                throw Error("Fail to get weather api: ${cause.message}")
            }
        }
    }

    suspend fun getCity(city: String, limit: Int, appid: String): List<City>? {
        return withContext(Dispatchers.IO) {
            try {
                WeatherApi.RetrofitInstance.api.getCity(city, limit, appid).body()
            } catch (cause: Throwable) {
                throw Error("Fail to get weather api: ${cause.message}")
            }
        }
    }
}