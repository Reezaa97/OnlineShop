package com.example.onlineshopapp.repositories.product

import com.example.onlineshopapp.api.product.ProductCategoryApi
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.product.ProductCategory
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
@ActivityScoped
class ProductCategoryRepository @Inject constructor(private val api: ProductCategoryApi) {

    suspend fun getCategory(): ServiceResponse<ProductCategory> {
        return try {
            api.getCategory()
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }

    suspend fun getCategoryById(id: Long): ServiceResponse<ProductCategory> {
        return try {
            api.getCategoryById(id)
        } catch (e: Exception) {
            ServiceResponse(status = "EXCEPTION", message = e.message)
        }
    }
}