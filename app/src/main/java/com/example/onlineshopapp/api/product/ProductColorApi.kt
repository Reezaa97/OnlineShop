package com.example.onlineshopapp.api.product


import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.product.ProductColor
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductColorApi {
    @GET("/api/color")
     suspend fun getColor(): ServiceResponse<ProductColor>

    @GET("/api/color{id}")
    suspend fun getColorById(@Path("id") id: Long):ServiceResponse<ProductColor>

}