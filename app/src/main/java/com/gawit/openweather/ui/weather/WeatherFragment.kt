package com.gawit.openweather.ui.weather

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.gawit.openweather.R
import com.gawit.openweather.databinding.FragmentWeatherBinding
import com.gawit.openweather.model.Weather
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlin.math.roundToInt

class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var viewModel: WeatherViewModel
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    companion object {
        fun newInstance() = WeatherFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        val countries: Array<out String> = resources.getStringArray(R.array.countries_array)

        ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, countries).also { adapter ->
            binding.editTxtCity.setAdapter(adapter)
        }

        if (binding.editTxtCity.text.isBlank()) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(requireContext())
            getLocation()
        }

        binding.editTxtCity.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val city = viewModel.getCity(binding.editTxtCity.text.toString().replace(" ", "+"), 1, "92fb97c9f7251535cb9d3869bfa39f5a")!!

                bindingWeather(viewModel.getWeather(
                    city[0].lat.toString(), city[0].lon.toString(), "92fb97c9f7251535cb9d3869bfa39f5a")!!
                )
                true
            } else {
                false
            }
        }

        return binding.root
    }

    private fun getLocation() {
        val task = fusedLocationProviderClient.lastLocation

        if (ActivityCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(requireContext(),
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 101)
            return
        }

        task.addOnSuccessListener { location ->
            if (location != null) {
                bindingWeather(viewModel.getWeather(location.latitude.toString(), location.longitude.toString(), "92fb97c9f7251535cb9d3869bfa39f5a")!!)
            }
        }
    }

    private fun bindingWeather(weather: Weather) {
        binding.txtMain.text = weather.weather[0]?.main
        binding.txtCity.text = weather.name
        binding.txtTemp.text = convertKelvinToCelsius(weather.main.temp)
        binding.txtWind.text = weather.wind.speed.toString()
        binding.icWeather.visibility = View.VISIBLE
        binding.txtWind.visibility = View.VISIBLE
        binding.spinner.visibility = View.GONE
    }

    private fun convertKelvinToCelsius(tempKelvin: Double): String {
        return "${(tempKelvin - 273.15).roundToInt()}º"
    }
}