package com.gawit.openweather.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("city")
data class City(
	@PrimaryKey(autoGenerate = true)
	val id: Int,
	val country: String,
	val name: String,
	val lon: Double,
	val state: String,
	val lat: Double
)
