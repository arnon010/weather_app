package com.example.weatherapp.presentation.weather_list

import com.example.weatherapp.domain.model.Weather

data class WeatherListState(
    val isLoading: Boolean = false,
    val weatherList: List<Weather> = emptyList(),
    val error: String = ""
)
