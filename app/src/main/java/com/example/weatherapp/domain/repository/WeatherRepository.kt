package com.example.weatherapp.domain.repository

import com.example.weatherapp.data.remote.dto.WeatherDto

interface WeatherRepository {
    // TODO: Add caching

    suspend fun getWeatherList(): List<WeatherDto>
}