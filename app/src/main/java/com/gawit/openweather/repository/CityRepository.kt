package com.gawit.openweather.repository

import androidx.lifecycle.LiveData
import com.gawit.openweather.dao.CityDao
import com.gawit.openweather.model.City

class CityRepository(
    private val cityDao: CityDao
) {
    val findAll: LiveData<List<City>> = cityDao.findAll()

    suspend fun insert(city: City) {
        cityDao.insert(city)
    }

    fun findById(id: Int): LiveData<City> {
        return cityDao.findById(id)
    }
}