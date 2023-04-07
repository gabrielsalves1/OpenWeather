package com.gawit.openweather.api

import com.gawit.openweather.model.City
import com.gawit.openweather.model.Weather
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org"

interface WeatherApi {
    @GET("/data/2.5/weather?")
    suspend fun getWeather(@Query("lat") lat: String, @Query("lon") lon: String, @Query("appid") appid: String): Response<Weather>

    @GET("/geo/1.0/direct?")
    suspend fun getCity(@Query("q") city: String, @Query("limit") limit: Int, @Query("appid") appid: String): Response<List<City>>

    object RetrofitInstance {
        val api : WeatherApi by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApi::class.java)
        }
    }
}