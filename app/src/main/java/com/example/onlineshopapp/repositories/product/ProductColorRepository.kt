package com.example.onlineshopapp.repositories.product

import com.example.onlineshopapp.api.product.ProductColorApi
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.product.ProductColor
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
@ActivityScoped
class ProductColorRepository @Inject constructor(val api: ProductColorApi) {
    suspend fun getProductColors(): ServiceResponse<ProductColor> {

        return try {api.getColor()
        }catch (e:Exception){
            ServiceResponse(status = "Exception", message = e.message)
        }
    }

        suspend fun getProductColorsById(id :Long): ServiceResponse<ProductColor> {

            return try {api.getColorById(id)
            }catch (e:Exception){
                ServiceResponse(status = "Exception", message = e.message)
            }
        }
}