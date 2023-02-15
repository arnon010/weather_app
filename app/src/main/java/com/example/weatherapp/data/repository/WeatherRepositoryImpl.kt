package com.example.weatherapp.data.repository

import com.example.weatherapp.data.remote.OpenWeatherApi
import com.example.weatherapp.data.remote.dto.WeatherDto
import com.example.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: OpenWeatherApi
): WeatherRepository {

    override suspend fun getWeatherList(): List<WeatherDto> {
        return api.getCityInfo().list
    }
}