package com.example.onlineshopapp.viewModels.invoice

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.invoice.PaymentTransaction
import com.example.onlineshopapp.repositories.Transaction.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {
    fun goToPayment(
        data: PaymentTransaction, onResponse: (response: ServiceResponse<String>)
        -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.goToPayment(data)
            onResponse(response)
        }
    }
}