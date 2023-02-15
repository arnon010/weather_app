package com.example.weatherapp.presentation.weather_detail

import com.example.weatherapp.domain.model.Weather

data class WeatherState(
    val isLoading: Boolean = false,
    val weather: Weather? = null,
    val error: String = ""
)
