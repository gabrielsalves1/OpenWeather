package com.gawit.openweather.api

import com.gawit.openweather.model.City
import com.gawit.openweather.model.Weather
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/weather?")
    fun getWeather(@Query("lat") lat: String, @Query("lon") lon: String, @Query("appid") appid: String): Call<Weather>

    @GET("/geo/1.0/direct?")
    fun getCity(@Query("q") city: String, @Query("limit") limit: Int, @Query("appid") appid: String): Call<List<City>>

    object RetrofitInstance {
        val api : WeatherApi by lazy {
            Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApi::class.java)
        }
    }
}