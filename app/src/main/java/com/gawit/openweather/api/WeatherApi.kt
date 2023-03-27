package com.gawit.openweather.api

import com.gawit.openweather.model.Weather
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather?")
    fun getWeather(@Query("lat") lat: String, @Query("lon") lon: String, @Query("appid") appid: String): Call<Weather>

    object RetrofitInstance {
        val api : WeatherApi by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApi::class.java)
        }
    }
}