package com.gawit.openweather.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gawit.openweather.R
import com.gawit.openweather.adapter.CityAdapter
import com.gawit.openweather.adapter.ViewPageAdapter
import com.gawit.openweather.databinding.FragmentFavoritesBinding
import com.gawit.openweather.model.City
import com.gawit.openweather.ui.weather.WeatherFragment

class FavoritesFragment : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[FavoritesViewModel::class.java]

        setAdapter()

        return binding.root
    }

    private fun setAdapter() {
        binding.rcCities.layoutManager = LinearLayoutManager(requireContext())
        binding.rcCities.setHasFixedSize(true)
        viewModel.findAll.observe(viewLifecycleOwner) { listCity ->
            val adapter = CityAdapter(requireContext(), listCity)
            binding.rcCities.adapter = adapter

            adapter.onItemClick = {
                parentFragmentManager.beginTransaction().addToBackStack("favoriteWeather").add(R.id.frame_layout, WeatherFragment().apply {
                    arguments = Bundle().apply {
                        putInt("id", it.id)
                    }
                }).commit()
            }
        }
    }
}