package com.gawit.openweather

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import com.gawit.openweather.model.*
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Test

@LargeTest
class WeatherFragmentTest {
    private lateinit var weather: Weather

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)

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
    fun displaySearchCity() {
        Espresso
            .onView(ViewMatchers.withHint("Search city"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun allTextViewDisplayed() {
        Espresso
            .onView(ViewMatchers.withId(R.id.txtCity))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))

        Espresso
            .onView(ViewMatchers.withId(R.id.txtMain))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))

        Espresso
            .onView(ViewMatchers.withId(R.id.txtTemp))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))

        Espresso
            .onView(ViewMatchers.withId(R.id.txtWind))
            .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())))
    }
}