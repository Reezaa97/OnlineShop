package com.example.onlineshopapp.api.cusomer


import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.cusomer.UserVm
import com.example.onlineshopapp.models.cusomer.Users
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface UsersApi {
    @GET("/api/users")
    suspend fun getUserInfo(@Header("Authorization") token: String): ServiceResponse<Users>

    @PUT("/api/users/changePassword")
    suspend fun changePassword(
        @Body users: UserVm,
        @Header("Authorization") token: String
    ): ServiceResponse<Users>

    @POST("/api/users/login")
    suspend fun login(
        @Body users: UserVm
    ): ServiceResponse<UserVm>

    @POST("/api/users/register")
    suspend fun register(
        @Body users: UserVm
    ): ServiceResponse<Users>

    @PUT("/api/users/update")
    suspend fun update(
        @Body users: UserVm,
        @Header("Authorization") token: String
    ): ServiceResponse<Users>
}