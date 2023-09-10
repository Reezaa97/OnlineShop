package com.example.onlineshopapp.repositories.site

import com.example.onlineshopapp.api.site.SliderApi
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.site.Slider
import dagger.hilt.android.scopes.ActivityScoped

import javax.inject.Inject

@ActivityScoped
class SliderRepository @Inject constructor(private val api: SliderApi) {

    suspend fun getSliders(): ServiceResponse<Slider> {
        return try {
            api.getSliders()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getSliderById(id: Long): ServiceResponse<Slider> {
        return try {
            api.getSliderById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}