package com.example.weatherapp.data.repository

import com.example.weatherapp.data.remote.dto.WeatherDto
import com.example.weatherapp.domain.repository.WeatherRepository
import java.io.IOException

class FakeWeatherRepository : WeatherRepository {

    private var weatherListDto = listOf<WeatherDto>()
    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }
    override suspend fun getWeatherList(): List<WeatherDto> {
        if (shouldReturnNetworkError) {
            throw IOException()
        } else {
            return weatherListDto
        }
    }

    fun initList(weatherListDto: List<WeatherDto>) {
        this.weatherListDto = weatherListDto
    }
}