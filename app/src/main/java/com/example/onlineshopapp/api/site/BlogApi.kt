package com.example.onlineshopapp.api.site


import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.site.Blog
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogApi {
    @GET("/api/blog")
     suspend fun getBlog(): ServiceResponse<Blog>

    @GET("/api/blog{id}")
    suspend fun getBlogById(@Path("id") id: Long):ServiceResponse<Blog>

}