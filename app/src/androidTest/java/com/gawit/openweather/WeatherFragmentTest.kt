package com.gawit.openweather

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.junit.Test

class WeatherFragmentTest {
    @Test
    fun displaySearchCity() {
        ActivityScenario.launch(MainActivity::class.java)
        Espresso
            .onView(ViewMatchers.withHint("Search city"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}