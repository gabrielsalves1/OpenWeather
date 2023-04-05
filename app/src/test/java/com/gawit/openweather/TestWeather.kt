package com.gawit.openweather

import com.gawit.openweather.model.*
import org.junit.Test

import org.junit.Assert
import org.junit.Before

class TestWeather {
    private lateinit var weather: Weather

    @Before
    fun setup() {
        weather = Weather(
            visibility=10000,
            timezone=-25200,
            main= Main(
                temp=277.62,
                tempMin=0.0,
                humidity=81,
                pressure=1023,
                feelsLike=0.0,
                tempMax=0.0),
            clouds= Clouds(
                all=0),
            sys= Sys(
                country="US",
                sunrise=1680616164,
                sunset=1680661974,
                id=2010364,
                type=2),
            dt=1680620016,
            coord= Coord(
                lon=-122.084,
                lat=37.422),
            weather= listOf(
                WeatherItem(
                    icon="01d",
                    description="clear sky",
                    main="Clear",
                    id=800)
            ),
            name="Mountain View",
            cod=200,
            id=5375480,
            base="stations",
            wind= Wind(
                deg=267,
                speed=0.89,
                gust=1.34)
        )
    }

    @Test
    fun `Validate some model and some saved values mock`() {
        Assert.assertEquals("Clear", weather.weather[0].main)
        Assert.assertEquals(277.62, weather.main.temp, weather.main.temp)
    }

    @Test
    fun `Validate convert temp`() {
        Assert.assertEquals(4, weather.convertKelvinToCelsius())
    }
}