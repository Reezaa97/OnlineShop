package com.example.onlineshopapp.viewModels.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.product.ProductColor
import com.example.onlineshopapp.repositories.product.ProductColorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductColorViewModel @Inject constructor(
    private val repository: ProductColorRepository
) : ViewModel() {
    fun getProductColors(onResponse: (response: ServiceResponse<ProductColor>)
    -> Unit) {
        viewModelScope.launch {
            var response = repository.getProductColors()
            onResponse(response)
        }
    }

    fun getProductColorsById(id :Long,onResponse: (response: ServiceResponse<ProductColor>)
    -> Unit) {
        viewModelScope.launch {
            var response = repository.getProductColorsById(id)
            onResponse(response)
        }
    }
}