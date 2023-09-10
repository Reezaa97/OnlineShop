package com.example.onlineshopapp.api.site

import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.site.Slider
import retrofit2.http.GET
import retrofit2.http.Path

interface SliderApi {

    @GET("/api/slider")
    suspend fun getSliders(): ServiceResponse<Slider>

    @GET("/api/slider/{id}")
    suspend fun getSliderById(@Path("id") id: Long): ServiceResponse<Slider>
}