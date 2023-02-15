package com.example.weatherapp.presentation.weather_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.viewbinding.ViewBinding
import coil.load
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentWeatherDetailBinding
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.presentation.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class WeatherDetailFragment : BindingFragment<FragmentWeatherDetailBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentWeatherDetailBinding::inflate

    val args: WeatherDetailFragmentArgs by navArgs()
    private val viewModel: WeatherDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getWeatherBy(args.dateArg)
        subscribeToStates()
    }

    private fun subscribeToStates() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when (state.isLoading) {
                    true -> binding.progressBar.visibility = View.VISIBLE
                    false -> binding.progressBar.visibility = View.GONE
                }

                when (state.weather != null) {
                    true -> populateViews(state.weather)
                }

                when (state.error.isNotEmpty()) {
                    true -> Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun populateViews(weather: Weather) {

        with(binding) {
            title.text = weather.title
            description.text = weather.description

            humidityValue.text = weather.humidity.toString() + "%"
            tempValue.text = weather.day.toString() + "°C"
            val simpleDate = SimpleDateFormat("hh:mm a")
            val timestamp = Timestamp(System.currentTimeMillis())

            val timeStampStr = simpleDate.format(timestamp)
            val dateSunset = simpleDate.format(weather.sunset * 1000L)
            val dateSunrise = simpleDate.format(weather.sunrise * 1000L)

            sunriseSunsetLabel.text = when {
                "AM" in timeStampStr  -> "sunset"
                "PM" in timeStampStr -> "sunrise"
                else -> "Normal"
            }
            sunriseSunsetValue.text = when {
                "AM" in timeStampStr -> dateSunset
                "PM" in timeStampStr -> dateSunrise
                else -> "Normal"
            }
            imageView2.load("https://openweathermap.org/img/w/${weather.icon}.png")
        }
    }
}