package com.gawit.openweather.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gawit.openweather.ui.favorites.FavoritesFragment
import com.gawit.openweather.ui.weather.WeatherFragment

class ViewPageAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> WeatherFragment()
            1 -> FavoritesFragment()
            else -> WeatherFragment()
        }
    }
}