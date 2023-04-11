package com.gawit.openweather.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gawit.openweather.model.City

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(city: City)

    @Query("SELECT * FROM city ORDER BY id ASC")
    fun findAll(): LiveData<List<City>>
}