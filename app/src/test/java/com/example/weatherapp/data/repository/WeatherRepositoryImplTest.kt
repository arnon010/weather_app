package com.example.weatherapp.data.repository

import com.example.weatherapp.data.remote.FakeOpenWeatherApi
import com.example.weatherapp.generateCityInfoDto
import kotlinx.coroutines.test.runBlockingTest
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Test

class WeatherRepositoryImplTest {

    private lateinit var weatherRepositoryImpl: WeatherRepositoryImpl
    private lateinit var fakeOpenWeatherApi: FakeOpenWeatherApi

    @Before
    fun setUp() {
        fakeOpenWeatherApi = FakeOpenWeatherApi()
        weatherRepositoryImpl = WeatherRepositoryImpl(fakeOpenWeatherApi)
    }

    @Test
    fun `Should return CityInfoDto successfully`() = runBlockingTest {
        val cityInfoDto = generateCityInfoDto()
        val expectedResult = cityInfoDto.list

        fakeOpenWeatherApi.initCityInfoDto(cityInfoDto)

        val result = weatherRepositoryImpl.getWeatherList()

        result.`should equal`(expectedResult)
    }
}