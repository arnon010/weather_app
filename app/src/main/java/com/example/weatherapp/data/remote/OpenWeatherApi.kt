package com.example.weatherapp.data.remote

import com.example.weatherapp.data.remote.dto.CityInfoDto
import retrofit2.http.GET

interface OpenWeatherApi {

    @GET("/data/2.5/forecast/daily?q=Bangkok&mode=json&units=metric&cnt=16&APPID=648a3aac37935e5b45e09727df728ac2")
//    @GET("/data/2.5/forecast/daily?q=Bangkok&units=metric&cnt=1&APPID=648a3aac37935e5b45e09727df728ac2")
//    @GET("/data/2.5/forecast/daily?q=Bangkok&APPID=648a3aac37935e5b45e09727df728ac2")
//    @GET("/data/2.5/weather?q=London&APPID=648a3aac37935e5b45e09727df728ac2")
    suspend fun getCityInfo(): CityInfoDto
}