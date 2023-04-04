package com.gawit.openweather.model

import kotlin.math.roundToInt

data class Weather(
    val visibility: Int,
    val timezone: Int,
    val main: Main,
    val clouds: Clouds,
    val sys: Sys,
    val dt: Int,
    val coord: Coord,
    val weather: List<WeatherItem>,
    val name: String,
    val cod: Int,
    val id: Int,
    val base: String,
    val wind: Wind
) {
    fun convertKelvinToCelsius(): Int {
        return (main.temp - 273.15).roundToInt()
    }
}

data class WeatherItem(
    val icon: String,
    val description: String,
    val main: String,
    val id: Int
)

data class Sys(
    val country: String,
    val sunrise: Int,
    val sunset: Int,
    val id: Int,
    val type: Int
)

data class Main(
    val temp: Double,
    val tempMin: Double,
    val humidity: Int,
    val pressure: Int,
    val feelsLike: Double,
    val tempMax: Double
)

data class Coord(
    val lon: Double,
    val lat: Double
)

data class Clouds(
    val all: Int
)

data class Wind(
    val deg: Int,
    val speed: Double,
    val gust: Double
)

