package com.example.onlineshopapp.viewModels.product

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.product.ProductCategory
import com.example.onlineshopapp.repositories.product.ProductCategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductCategoryViewModel @Inject constructor(
    private val repository: ProductCategoryRepository
) : ViewModel() {

    var dataList = mutableStateOf<List<ProductCategory>>(listOf())
    var isLoading = mutableStateOf(true)

    init {
        getCategories { response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

    fun getCategories(onResponse: (response: ServiceResponse<ProductCategory>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getCategory()
            onResponse(response)
        }
    }

    fun getCategoryById(
        id: Long,
        onResponse: (response: ServiceResponse<ProductCategory>) -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.getCategoryById(id)
            onResponse(response)
        }
    }
}