package com.example.onlineshopapp.viewModels.product

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.product.Product
import com.example.onlineshopapp.repositories.product.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {

    var dataList = mutableStateOf<List<Product>>(listOf())
    var isLoading = mutableStateOf(true)
    var data = mutableStateOf<Product?>(null)

    init {
        getProducts(0, 6) { response ->
            isLoading.value = false
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
        }
    }

    fun getProducts(
        pageIndex: Int,
        pageSize: Int,
        onResponse: (response: ServiceResponse<Product>) -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.getProducts(pageIndex, pageSize)
            onResponse(response)
        }
    } fun getProductsByCategoryId(
        categoryId:Long,
        pageIndex: Int,
        pageSize: Int,
        onResponse: (response: ServiceResponse<Product>) -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.getProductsByCategoryId(categoryId,pageIndex, pageSize)
            onResponse(response)
        }
    }

    fun getProductById(id: Long, onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getProductById(id)
            onResponse(response)
        }
    }

    fun getNewProducts(onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getNewProducts()
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
            onResponse(response)
        }
    }

    fun getPopularProducts(onResponse: (response: ServiceResponse<Product>) -> Unit) {
        viewModelScope.launch {
            var response = repository.getPopularProducts()
            if (response.status == "OK") {
                dataList.value = response.data!!
            }
            onResponse(response)
        }
    }
}
