package com.example.onlineshopapp.repositories.invoice

import com.example.onlineshopapp.api.invoice.InvoiceApi
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.invoice.Invoice
import com.example.onlineshopapp.repositories.base.BaseRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class InvoiceRepository @Inject constructor(val api: InvoiceApi) : BaseRepository() {

    suspend fun addInvoice(data: Invoice, token: String): ServiceResponse<Invoice> {

        return try {
            api.addInvoice(data, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "Exception", message = e.message)
        }
    }

    suspend fun getInvoiceByUserId(
        userId: Long,
        pageIndex: Int,
        pageSize: Int,
        token: String
    ): ServiceResponse<Invoice> {

        return try {
            api.getInvoiceByUserId(userId, pageIndex, pageSize, prepareToken(token))
        } catch (e: Exception) {
            ServiceResponse(status = "Exception", message = e.message)
        }
    }

    suspend fun getInvoicesById(id: Long, token: String): ServiceResponse<Invoice> {
        var fixedToken = prepareToken(token)
        return try {
            api.getInvoiceById(id, fixedToken)
        } catch (e: Exception) {
            ServiceResponse(status = "Exception", message = e.message)
        }
    }


}