package com.example.onlineshopapp.repositories.Transaction

import com.example.onlineshopapp.api.invoice.TransactionApi
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.invoice.PaymentTransaction
import com.example.onlineshopapp.models.invoice.Transaction
import com.example.onlineshopapp.repositories.base.BaseRepository
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class TransactionRepository @Inject constructor(val api: TransactionApi) : BaseRepository() {

    suspend fun goToPayment(data: PaymentTransaction): ServiceResponse<String> {

        return try {
            api.goToPayment(data)
        } catch (e: Exception) {
            ServiceResponse(status = "Exception", message = e.message)
        }
    }

}