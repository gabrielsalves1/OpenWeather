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
import com.gawit.openweather.model.City
import com.gawit.openweather.model.Weather
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.*

class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherBinding
    private lateinit var viewModel: WeatherViewModel
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var city = ArrayList<City>()

    companion object {
        fun newInstance() = WeatherFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[WeatherViewModel::class.java]

        autocompleteCity()

        getLocationAndBinding()

        searchCity()

        addFavoriteCity()

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        getLocationAndBinding()
    }

    private fun autocompleteCity() {
        val countries: Array<out String> = resources.getStringArray(R.array.countries_array)

        ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, countries).also { adapter ->
            binding.editTxtCity.setAdapter(adapter)
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun searchCity() {
        binding.editTxtCity.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                GlobalScope.launch(Dispatchers.Main) {
                    city.add(viewModel.getCity(binding.editTxtCity.text.toString().replace(" ", "+"), 1, "92fb97c9f7251535cb9d3869bfa39f5a")!![0])

                    bindingWeather(viewModel.getWeather(
                        city[0].lat.toString(), city[0].lon.toString(), "92fb97c9f7251535cb9d3869bfa39f5a")!!
                    )

                    binding.btnFavorite.visibility = View.VISIBLE
                }

                true
            } else {
                false
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getLocationAndBinding() {
        if (binding.editTxtCity.text.isBlank()) {
            fusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(requireContext())
            val task = fusedLocationProviderClient.lastLocation

            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                    101
                )
                return
            }

            task.addOnSuccessListener { location ->
                if (location != null) {
                    GlobalScope.launch(Dispatchers.Main) {
                        bindingWeather(
                            viewModel.getWeather(
                                location.latitude.toString(),
                                location.longitude.toString(),
                                "92fb97c9f7251535cb9d3869bfa39f5a"
                            )!!
                        )
                    }
                }
            }
        }
    }

    private fun bindingWeather(weather: Weather) {
        binding.txtMain.text = weather.weather[0].main
        binding.txtCity.text = weather.name
        binding.txtTemp.text = weather.convertKelvinToCelsius().toString()
        binding.txtWind.text = weather.wind.speed.toString()
        binding.icWeather.visibility = View.VISIBLE
        binding.txtWind.visibility = View.VISIBLE
        binding.spinner.visibility = View.GONE
    }

    private fun addFavoriteCity() {
        binding.btnFavorite.setOnClickListener {
            viewModel.insert(city[0])
        }
    }

}