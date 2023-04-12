package com.gawit.openweather.ui.weather

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gawit.openweather.api.WeatherApi
import com.gawit.openweather.database.OpenWeatherDatabase
import com.gawit.openweather.model.City
import com.gawit.openweather.model.Weather
import com.gawit.openweather.repository.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: CityRepository

    init {
        val cityDao = OpenWeatherDatabase.getDatabase(application).cityDao()
        repository = CityRepository(cityDao)
    }

    fun insert(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(city)
        }
    }

    fun findById(id: Int): LiveData<City> {
        return repository.findById(id)
    }

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