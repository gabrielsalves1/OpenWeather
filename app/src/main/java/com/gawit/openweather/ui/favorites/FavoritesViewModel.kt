package com.gawit.openweather.ui.favorites

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.gawit.openweather.database.OpenWeatherDatabase
import com.gawit.openweather.model.City
import com.gawit.openweather.repository.CityRepository

class FavoritesViewModel(application: Application) : AndroidViewModel(application) {
    val findAll: LiveData<List<City>>
    private val repository: CityRepository

    init {
        val cityDao = OpenWeatherDatabase.getDatabase(application).cityDao()
        repository = CityRepository(cityDao)
        findAll = repository.findAll
    }
}