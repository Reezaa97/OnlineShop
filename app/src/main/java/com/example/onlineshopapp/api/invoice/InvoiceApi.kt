package com.example.onlineshopapp.api.invoice

import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.invoice.Invoice
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface InvoiceApi {

    @GET("/api/invoice")
    suspend fun addInvoice(
        @Body data: Invoice,
        @Header("Authorization") token: String
    ): ServiceResponse<Invoice>


    @GET("/api/invoice{id}")
    suspend fun getInvoiceById(
        @Path("id") id: Long,
        @Header("Authorization") token: String
        ): ServiceResponse<Invoice>

    @GET("/api/invoice/user{userId}")
    suspend fun getInvoiceByUserId(
        @Path("userId") id: Long,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageIndex") pageSize: Int,
        @Header("Authorization") token: String
    ): ServiceResponse<Invoice>
}