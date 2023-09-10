package com.example.onlineshopapp.api.site

import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.site.Content
import retrofit2.http.GET
import retrofit2.http.Path


interface ContentApi {
    @GET("/api/content")
     suspend fun getContent(): ServiceResponse<Content>

    @GET("/api/content{title}")
    suspend fun getContentByTitle(@Path("title") id: String):ServiceResponse<Content>

}