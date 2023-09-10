package com.example.onlineshopapp.viewModels.site

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.site.Slider
import com.example.onlineshopapp.repositories.site.SliderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class SliderViewModel @Inject constructor(
    private val repository: SliderRepository
) : ViewModel() {

    var dataList = mutableStateOf<List<Slider>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getSliders { response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

    fun getSliders(onResponse: (response: ServiceResponse<Slider>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getSliders()
            onResponse(response)
        }
    }

    fun getSliderById(id: Long, onResponse: (response: ServiceResponse<Slider>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getSliderById(id)
            onResponse(response)
        }
    }
}