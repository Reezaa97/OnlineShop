package com.example.onlineshopapp.viewModels.invoice

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshopapp.models.ServiceResponse
import com.example.onlineshopapp.models.invoice.Invoice
import com.example.onlineshopapp.repositories.invoice.InvoiceRepository
import com.example.onlineshopapp.utils.ThisApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class InvoiceItemViewModel @Inject constructor(
    private val repository: InvoiceRepository
) : ViewModel() {

    var token: String = ThisApp.token
    var invoiceId: Long = ThisApp.invoiceId

    var data = mutableStateOf<Invoice?>(null)
    var isLoading = mutableStateOf(true)

    init {
        getInvoicesById(invoiceId) { response ->
            isLoading.value = false
            if (response.status == "OK") {
                data.value = response.data!![0]
            }
        }
    }


    fun getInvoicesById(
        id: Long, onResponse: (response: ServiceResponse<Invoice>) -> Unit
    ) {
        viewModelScope.launch {
            var response = repository.getInvoicesById(id, token)
            onResponse(response)
        }
    }


}